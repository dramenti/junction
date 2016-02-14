package jayanth.junction;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.IOException;

public class Lexer {
    private BufferedReader reader;
    private char current_char;
    private State current_state;
    private StringBuilder current_token;
    private State[][] table_f; //refactor as EnumMap later

    private State transition(Typechar c) {
        return table_f[current_state.ordinal()][c.ordinal()];
    }

    private Token lex_token() throws IOException { 
        current_token = new StringBuilder(); //will add each char to build up
        current_state = transition(classify(current_char));
        while (!is_final_state(current_state)) {
            current_token.append(current_char);
            current_char = (char)reader.read();
            current_state = transition(classify(current_char));
        }
        if (current_state == State.SFINAL_ERROR) {
            //throw `invalid token` error
        }
        else if (current_state == State.SFINAL_LPAREN) {
            current_char = (char)reader.read();
            return new Token(TokenType.LPAREN);
        }
        else if (current_state == State.SFINAL_RPAREN) {
            current_char = (char)reader.read();
            return new Token(TokenType.RPAREN);
        }
        else if (current_state == State.SFINAL_INTEGER) {
            return new Token(TokenType.INTEGER, Integer.parseInt(current_token.toString()));
        }
        else if (current_state == State.SFINAL_FLOAT) {
            return new Token(TokenType.FLOAT, Double.parseDouble(current_token.toString()));
        }
        else if (current_state == State.SFINAL_STRING) {
            //the semantic value is currently:
            // <quote>contents
            //so delete that first character "
            current_token.deleteCharAt(0);
            current_char = (char)reader.read(); //prepare for reading the next token
            return new Token(TokenType.STRING, current_token.toString());
        }
        else if (current_state == State.SFINAL_ID) {
            //deal with special cases
            String idesque = current_token.toString();
            if (idesque.equals("def")) return new Token(TokenType.DEF);
            if (idesque.equals("if")) return new Token(TokenType.IF);
            if (idesque.equals("lambda") || idesque.equals("λ")) return new Token(TokenType.LAMBDA);
            if (idesque.equals("and")) return new Token(TokenType.AND);
            if (idesque.equals("or")) return new Token(TokenType.OR);
            if (idesque.equals("True")) return new Token(TokenType.BOOLEAN, true);
            if (idesque.equals("False")) return new Token(TokenType.BOOLEAN, false);
            return new Token(TokenType.ID, idesque);
        }
        else {
            //throw error
        }
        return null;
    }

    private boolean is_final_state(State s) {
        return s.ordinal() > State.SFINAL.ordinal();
    }
    private Typechar classify(char c) {
        if (c == '"') return Typechar.TC_QUOTE;
        if (c == '(') return Typechar.TC_LPAREN;
        if (c == ')') return Typechar.TC_RPAREN;
        if (c == '.') return Typechar.TC_DOT;
        if (c == '-') return Typechar.TC_HYPHEN;
        if (Character.isDigit(c)) return Typechar.TC_DIGIT;
        if (Character.isLetter(c)) return Typechar.TC_IDSTART;
        if (c == '=' || c == '$' || c == '+' || c == '-' || c == '*' || c == '/') return Typechar.TC_IDSTART;
        return Typechar.TC_OTHER;
    }
    private enum State {
        S_INITIAL,
        S_STRING,
        S_NUMERIC,
        S_FLOAT,
        S_ID,
        SFINAL,
        SFINAL_LPAREN,
        SFINAL_RPAREN,
        SFINAL_ID,
        SFINAL_INTEGER,
        SFINAL_FLOAT,
        SFINAL_STRING,
        SFINAL_ERROR;
    }
    private enum Typechar {
        TC_QUOTE,
        TC_LPAREN,
        TC_RPAREN,
        TC_DOT,
        TC_HYPHEN,
        TC_DIGIT,
        TC_IDSTART,
        TC_OTHER;
    }
    public Lexer(Reader in) throws IOException{
        reader = new BufferedReader(in);
        current_char = (char)reader.read();
        //initialize the table for FSM
        final int ROWS = 5;
        final int COLS = Typechar.values().length;
        table_f = new State[ROWS][COLS];
        //initialize the FSM itself
        table_f[State.S_INITIAL.ordinal()][Typechar.TC_QUOTE.ordinal()] = State.S_STRING;
        table_f[State.S_INITIAL.ordinal()][Typechar.TC_LPAREN.ordinal()] = State.SFINAL_LPAREN;
        table_f[State.S_INITIAL.ordinal()][Typechar.TC_RPAREN.ordinal()] = State.SFINAL_RPAREN;
        table_f[State.S_INITIAL.ordinal()][Typechar.TC_DOT.ordinal()] = State.S_FLOAT;
        table_f[State.S_INITIAL.ordinal()][Typechar.TC_HYPHEN.ordinal()] = State.S_NUMERIC;
        table_f[State.S_INITIAL.ordinal()][Typechar.TC_DIGIT.ordinal()] = State.S_NUMERIC;
        table_f[State.S_INITIAL.ordinal()][Typechar.TC_IDSTART.ordinal()] = State.S_ID;
        table_f[State.S_INITIAL.ordinal()][Typechar.TC_OTHER.ordinal()] = State.SFINAL_ERROR;

        table_f[State.S_STRING.ordinal()][Typechar.TC_QUOTE.ordinal()] = State.SFINAL_STRING;
        table_f[State.S_STRING.ordinal()][Typechar.TC_LPAREN.ordinal()] = State.S_STRING;
        table_f[State.S_STRING.ordinal()][Typechar.TC_RPAREN.ordinal()] = State.S_STRING;
        table_f[State.S_STRING.ordinal()][Typechar.TC_DOT.ordinal()] = State.S_STRING;
        table_f[State.S_STRING.ordinal()][Typechar.TC_HYPHEN.ordinal()] = State.S_STRING;
        table_f[State.S_STRING.ordinal()][Typechar.TC_DIGIT.ordinal()] = State.S_STRING;
        table_f[State.S_STRING.ordinal()][Typechar.TC_IDSTART.ordinal()] = State.S_STRING;
        table_f[State.S_STRING.ordinal()][Typechar.TC_OTHER.ordinal()] = State.S_STRING;

        table_f[State.S_NUMERIC.ordinal()][Typechar.TC_QUOTE.ordinal()] = State.SFINAL_INTEGER;
        table_f[State.S_NUMERIC.ordinal()][Typechar.TC_LPAREN.ordinal()] = State.SFINAL_INTEGER;
        table_f[State.S_NUMERIC.ordinal()][Typechar.TC_RPAREN.ordinal()] = State.SFINAL_INTEGER;
        table_f[State.S_NUMERIC.ordinal()][Typechar.TC_DOT.ordinal()] = State.S_FLOAT;
        table_f[State.S_NUMERIC.ordinal()][Typechar.TC_HYPHEN.ordinal()] = State.SFINAL_INTEGER;
        table_f[State.S_NUMERIC.ordinal()][Typechar.TC_DIGIT.ordinal()] = State.S_NUMERIC;
        table_f[State.S_NUMERIC.ordinal()][Typechar.TC_IDSTART.ordinal()] = State.SFINAL_INTEGER;
        table_f[State.S_NUMERIC.ordinal()][Typechar.TC_OTHER.ordinal()] = State.SFINAL_INTEGER;

        table_f[State.S_FLOAT.ordinal()][Typechar.TC_QUOTE.ordinal()] = State.SFINAL_FLOAT;
        table_f[State.S_FLOAT.ordinal()][Typechar.TC_LPAREN.ordinal()] = State.SFINAL_FLOAT;
        table_f[State.S_FLOAT.ordinal()][Typechar.TC_RPAREN.ordinal()] = State.SFINAL_FLOAT;
        table_f[State.S_FLOAT.ordinal()][Typechar.TC_DOT.ordinal()] = State.S_FLOAT;
        table_f[State.S_FLOAT.ordinal()][Typechar.TC_HYPHEN.ordinal()] = State.SFINAL_FLOAT;
        table_f[State.S_FLOAT.ordinal()][Typechar.TC_DIGIT.ordinal()] = State.S_FLOAT;
        table_f[State.S_FLOAT.ordinal()][Typechar.TC_IDSTART.ordinal()] = State.SFINAL_FLOAT;
        table_f[State.S_FLOAT.ordinal()][Typechar.TC_OTHER.ordinal()] = State.SFINAL_FLOAT;

        table_f[State.S_ID.ordinal()][Typechar.TC_QUOTE.ordinal()] = State.SFINAL_ID;
        table_f[State.S_ID.ordinal()][Typechar.TC_LPAREN.ordinal()] = State.SFINAL_ID;
        table_f[State.S_ID.ordinal()][Typechar.TC_RPAREN.ordinal()] = State.SFINAL_ID;
        table_f[State.S_ID.ordinal()][Typechar.TC_DOT.ordinal()] = State.S_ID;
        table_f[State.S_ID.ordinal()][Typechar.TC_HYPHEN.ordinal()] = State.SFINAL_ID;
        table_f[State.S_ID.ordinal()][Typechar.TC_DIGIT.ordinal()] = State.S_ID;
        table_f[State.S_ID.ordinal()][Typechar.TC_IDSTART.ordinal()] = State.S_ID;
        table_f[State.S_ID.ordinal()][Typechar.TC_OTHER.ordinal()] = State.SFINAL_ID;
    }
    public Token next_token() throws IOException {
        //System.out.println((short)current_char);
        while (Character.isWhitespace(current_char)) {
            current_char = (char)reader.read();
        }
        if ((short)current_char < 32) return new Token(TokenType.EOF);
        current_state = State.S_INITIAL;
        return lex_token();
    }
}
