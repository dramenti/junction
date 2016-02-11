package jayanth.junction;
import java.io.BufferedReader;

public class Lexer {
    private BufferedReader reader;
    private char current_char;
    private State current_state;
    private StringBuilder current_token;
    private State[][] table_f; //refactor as EnumMap later

    private State transition(Typechar c) {
        return table_f[current_state][c];
    }

    private Token lex_token() { 
        current_token = new StringBuilder(); //will add each char to build up
        current_state = transition(classify(current_char));
        while (!is_final_state(current_state)) {
            current_token.append(current_char);
            current_char = reader.read();
            current_state = transition(classify(current_char));
        }
        if (current_state == SFINAL_ERROR) {
            //throw `invalid token` error
        }
        else if (current_state == SFINAL_LPAREN) {
            return new Token(LPAREN);
        }
        else if (current_state == SFINAL_RPAREN) {
            return new Token(RPAREN);
        }
        else if (current_state == SFINAL_INTEGER) {
            return new Token(INTEGER, Integer.parseInt(current_token.toString()));
        }
        else if (current_state == SFINAL_FLOAT) {
            return new Token(FLOAT, Double.parseDouble(current_token.toString()));
        }
        else if (current_state == SFINAL_STRING) {
            current_char = reader.read();
            return new Token(STRING, current_token.toString());
        }
        else if (current_state == SFINAL_ID) {
            //
        }
        else {
            //throw error
        }
    }

    private boolean is_final_state(State s) {
        return s > SFINAL;
    }
    private Typechar classify(char c) {
        if c == '"' return TC_QUOTE;
        if c == '(' return TC_LPAREN;
        if c == ')' return TC_RPAREN;
        if c == '.' return TC_DOT;
        if c == '-' return TC_HYPHEN;
        if Character.isDigit(c) return TC_DIGIT;
        if Character.isLetter(c) return TC_IDSTART;
        if (c == '+' || c == '-' || c == '*' || c == '/') return TC_IDSTART;
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
    public Lexer() {
        //initialize the table for FSM
        final int ROWS = 5;
        final int COLS = Typechar.values().length;
        table_f = new State[ROWS][COLS];
        //initialize the FSM itself
        table_f[S_INITIAL.ordinal()][TC_QUOTE.ordinal()] = S_STRING;
        table_f[S_INITIAL.ordinal()][TC_LPAREN.ordinal()] = SFINAL_LPAREN;
        table_f[S_INITIAL.ordinal()][TC_RPAREN.ordinal()] = SFINAL_RPAREN;
        table_f[S_INITIAL.ordinal()][TC_DOT.ordinal()] = S_FLOAT;
        table_f[S_INITIAL.ordinal()][TC_HYPHEN.ordinal()] = S_NUMERIC;
        table_f[S_INITIAL.ordinal()][TC_DIGIT.ordinal()] = S_NUMERIC;
        table_f[S_INITIAL.ordinal()][TC_IDSTART.ordinal()] = S_ID;
        table_f[S_INITIAL.ordinal()][TC_OTHER.ordinal()] = SFINAL_ERROR;

        table_f[S_STRING.ordinal()][TC_QUOTE.ordinal()] = SFINAL_STRING;
        table_f[S_STRING.ordinal()][TC_LPAREN.ordinal()] = S_STRING;
        table_f[S_STRING.ordinal()][TC_RPAREN.ordinal()] = S_STRING;
        table_f[S_STRING.ordinal()][TC_DOT.ordinal()] = S_STRING;
        table_f[S_STRING.ordinal()][TC_HYPHEN.ordinal()] = S_STRING;
        table_f[S_STRING.ordinal()][TC_DIGIT.ordinal()] = S_STRING;
        table_f[S_STRING.ordinal()][TC_IDSTART.ordinal()] = S_STRING;
        table_f[S_STRING.ordinal()][TC_OTHER.ordinal()] = S_STRING;

        table_f[S_NUMERIC.ordinal()][TC_QUOTE.ordinal()] = SFINAL_INTEGER;
        table_f[S_NUMERIC.ordinal()][TC_LPAREN.ordinal()] = SFINAL_INTEGER;
        table_f[S_NUMERIC.ordinal()][TC_RPAREN.ordinal()] = SFINAL_INTEGER;
        table_f[S_NUMERIC.ordinal()][TC_DOT.ordinal()] = S_FLOAT;
        table_f[S_NUMERIC.ordinal()][TC_HYPHEN.ordinal()] = SFINAL_INTEGER;
        table_f[S_NUMERIC.ordinal()][TC_DIGIT.ordinal()] = S_NUMERIC;
        table_f[S_NUMERIC.ordinal()][TC_IDSTART.ordinal()] = SFINAL_INTEGER;
        table_f[S_NUMERIC.ordinal()][TC_OTHER.ordinal()] = SFINAL_INTEGER;

        table_f[S_FLOAT.ordinal()][TC_QUOTE.ordinal()] = SFINAL_FLOAT;
        table_f[S_FLOAT.ordinal()][TC_LPAREN.ordinal()] = SFINAL_FLOAT;
        table_f[S_FLOAT.ordinal()][TC_RPAREN.ordinal()] = SFINAL_FLOAT;
        table_f[S_FLOAT.ordinal()][TC_DOT.ordinal()] = S_FLOAT;
        table_f[S_FLOAT.ordinal()][TC_HYPHEN.ordinal()] = SFINAL_FLOAT;
        table_f[S_FLOAT.ordinal()][TC_DIGIT.ordinal()] = SFINAL_FLOAT;
        table_f[S_FLOAT.ordinal()][TC_IDSTART.ordinal()] = SFINAL_FLOAT;
        table_f[S_FLOAT.ordinal()][TC_OTHER.ordinal()] = SFINAL_FLOAT;

        table_f[S_ID.ordinal()][TC_QUOTE.ordinal()] = SFINAL_ID;
        table_f[S_ID.ordinal()][TC_LPAREN.ordinal()] = SFINAL_ID;
        table_f[S_ID.ordinal()][TC_RPAREN.ordinal()] = SFINAL_ID;
        table_f[S_ID.ordinal()][TC_DOT.ordinal()] = S_ID;
        table_f[S_ID.ordinal()][TC_HYPHEN.ordinal()] = SFINAL_ID;
        table_f[S_ID.ordinal()][TC_DIGIT.ordinal()] = S_ID;
        table_f[S_ID.ordinal()][TC_IDSTART.ordinal()] = S_ID;
        table_f[S_ID.ordinal()][TC_OTHER.ordinal()] = SFINAL_ID;
    }
    public Token next_token() {
        current_state = S_INITIAL;
        return lex_token();
    }
}
