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

    public AST_Node parse() throws IOException {
        return parseExpression();
    }

    private void parseErr(String message) {
        System.out.println(message);
    }

    private boolean expect(TokenType type) {
        if (token.getTokenType() != type) {
            System.out.println("Error: unexpected token");
            return false;
        }
        return true;
    }

    private AST_Node parseTerminal() {
        switch(token.getTokenType()) {
            case ID: return new IdentifierNode(token.getValue().v_string);
            case FLOAT: return new FloatNode(token.getValue().v_float);
            case INTEGER: return new IntNode(token.getValue().v_int);
            case STRING: return new StringNode(token.getValue().v_string);
            default: parseErr("Unrecognized token when parsing terminal"); return null;
        }
    }

    private AST_Node parseIf() throws IOException{
        //TODO: implement!
        return null;
    }

    private AST_Node parseLambda() throws IOException {
        //currently at lambda
        //next should be a (
        token = lexer.nextToken();
        if (!expect(TokenType.LPAREN)) return null;

        //currently at '('
        //next should be identifiers (params)
        LinkedList<String> formals = new LinkedList<String>();

        token = lexer.nextToken();
        while (token.getTokenType() != TokenType.RPAREN) {
            if (!expect( TokenType.ID)) return null;
            formals.add(token.getValue().v_string);
            token = lexer.nextToken();
        }
        token = lexer.nextToken();
        AST_Node value = parseExpression();
        if (value == null) return null; //error
        if (!expect(TokenType.RPAREN)) return null;
        token = lexer.nextToken();
        return new LambdaNode(formals.toArray(new String[formals.size()]), value);
    }
    private AST_Node parseDefine() throws IOException{
        //currently at def
        //next should be a (
        token = lexer.nextToken();
        if (!expect(TokenType.LPAREN)) return null;
        
        //currently at '('
        //next should be identifier
        token = lexer.nextToken(); 
        if (!expect( TokenType.ID)) return null;
        String name = token.getValue().v_string;
        LinkedList<String> formals = new LinkedList<String>();

        token = lexer.nextToken();
        while (token.getTokenType() != TokenType.RPAREN) {
            if (!expect( TokenType.ID)) return null;
            formals.add(token.getValue().v_string);
            token = lexer.nextToken();
        }
        //done parsing args, advance lexer
        token = lexer.nextToken();

        //parse function return value
        AST_Node value = parseExpression();
        if (value == null) return null; //error
        if (!expect(TokenType.RPAREN)) return null;
        token = lexer.nextToken();
        return new DefNode(name, formals.toArray(new String[formals.size()]), value);
    }

    private AST_Node parseCall() throws IOException{
        //lexer currently at something (identifier, or perhaps another call)
        //note that it doesn't have to be an identifier
        //the language supports expressions that evaluate to a function,
        //and then that expression can call something 
        //(e.g.  ((f n) x) is valid if (f n) is a function
        AST_Node caller = parseExpression();
        LinkedList<AST_Node> args = new LinkedList<AST_Node>();
        while (token.getTokenType() != TokenType.RPAREN) {
            AST_Node arg = parseExpression();
            if (arg == null) return null; //error checking?
            args.add(arg);
        }
        //currently at RPAREN
        token = lexer.nextToken();
        //System.out.println("Parsed call");
        return new CallNode(caller, args.toArray(new AST_Node[args.size()]));
    }
    private AST_Node parseCombination() throws IOException{
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

    private AST_Node parseExpression() throws IOException {
        if (token.getTokenType() == TokenType.LPAREN) return parseCombination();
        if (token.getTokenType() == TokenType.EOF) return null;
        AST_Node terminal = parseTerminal();
        token = lexer.nextToken();
        return terminal;
    }
}
