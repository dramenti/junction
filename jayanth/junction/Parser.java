package jayanth.junction;
import java.io.Reader;
import java.io.IOException;
import java.util.LinkedList;

public class Parser {
    private Lexer lexer;
    Token token;

    public Parser(Reader in) throws IOException {
        this.lexer = new Lexer(in);
        token = lexer.nextToken();
    }

    public AST_Node parse() throws IOException, JunctionParserException {
        return parseExpression();
    }

    private boolean expect(TokenType type) {
        if (token.getTokenType() != type) {
            //System.out.println("Error: unexpected token");
            return false;
        }
        return true;
    }

    private AST_Node parseTerminal() throws JunctionParserException {
        switch(token.getTokenType()) {
            case ID: return new IdentifierNode(token.getValue().v_string);
            case FLOAT: return new FloatNode(token.getValue().v_float);
            case INTEGER: return new IntNode(token.getValue().v_int);
            case STRING: return new StringNode(token.getValue().v_string);
            case BOOLEAN: return new BooleanNode(token.getValue().v_bool);
            default: throw new JunctionParserException("Unrecognized terminal: " + token);
        }
    }

    private AST_Node parseIf() throws IOException, JunctionParserException {
        //currently at if
        //advance lexer
        token = lexer.nextToken();
        AST_Node condition = parseExpression();
        AST_Node body1 = parseExpression();
        AST_Node body2 = parseExpression();
        if (!expect(TokenType.RPAREN)) throw new JunctionParserException("Expected closing ')' for if statement");
        return new IfNode(condition, body1, body2);
    }

    private AST_Node parseLambda() throws IOException, JunctionParserException {
        //currently at lambda
        //next should be a (
        token = lexer.nextToken();
        if (!expect(TokenType.LPAREN)) throw new JunctionParserException("Expected '(' after lambda");

        //currently at '('
        //next should be identifiers (params)
        LinkedList<String> formals = new LinkedList<String>();

        token = lexer.nextToken();
        while (token.getTokenType() != TokenType.RPAREN) {
            if (!expect(TokenType.ID)) throw new JunctionParserException("Expected identifier as formal parameter");
            formals.add(token.getValue().v_string);
            token = lexer.nextToken();
        }
        token = lexer.nextToken();
        AST_Node value = parseExpression();
        if (!expect(TokenType.RPAREN)) throw new JunctionParserException("Expected ')' at end of function definition");
        token = lexer.nextToken();
        return new LambdaNode(formals.toArray(new String[formals.size()]), value);
    }
    private AST_Node parseDefine() throws IOException, JunctionParserException {
        //currently at def
        //next should be a (
        token = lexer.nextToken();
        if (!expect(TokenType.LPAREN)) throw new JunctionParserException("Expected '(' after def");
        
        //currently at '('
        //next should be identifier
        token = lexer.nextToken(); 
        if (!expect( TokenType.ID)) throw new JunctionParserException("Expected identifier after '(' in function definition");
        String name = token.getValue().v_string;
        LinkedList<String> formals = new LinkedList<String>();

        token = lexer.nextToken();
        while (token.getTokenType() != TokenType.RPAREN) {
            if (!expect(TokenType.ID)) throw new JunctionParserException("Expected identifier as formal parameter");
            formals.add(token.getValue().v_string);
            token = lexer.nextToken();
        }
        //done parsing args, advance lexer
        token = lexer.nextToken();

        //parse function return value
        AST_Node value = parseExpression();
        if (!expect(TokenType.RPAREN)) throw new JunctionParserException("Expected ')' at end of function definition");
        token = lexer.nextToken();
        return new DefNode(name, formals.toArray(new String[formals.size()]), value);
    }

    private AST_Node parseCall() throws IOException, JunctionParserException {
        //lexer currently at something (identifier, or perhaps another call)
        //note that it doesn't have to be an identifier
        //the language supports expressions that evaluate to a function,
        //and then that expression can call something 
        //(e.g.  ((f n) x) is valid if (f n) is a function
        AST_Node caller = parseExpression();
        LinkedList<AST_Node> args = new LinkedList<AST_Node>();
        while (token.getTokenType() != TokenType.RPAREN) {
            AST_Node arg = parseExpression();
            args.add(arg);
        }
        //currently at RPAREN
        token = lexer.nextToken();
        //System.out.println("Parsed call");
        return new CallNode(caller, args.toArray(new AST_Node[args.size()]));
    }
    private AST_Node parseCombination() throws IOException, JunctionParserException {
        //currently at '('
        token = lexer.nextToken();
        //could either be a special form
        //or a function call
        switch(token.getTokenType()) {
            case DEF: return parseDefine();
            case LAMBDA: return parseLambda();
            case IF: return parseIf();
            default: return parseCall(); //if it isn't a special form, it's call 
        }
    }

    private AST_Node parseExpression() throws IOException, JunctionParserException {
        if (token.getTokenType() == TokenType.LPAREN) return parseCombination();
        if (token.getTokenType() == TokenType.EOF) return null;
        AST_Node terminal = parseTerminal();
        token = lexer.nextToken();
        return terminal;
    }
}
