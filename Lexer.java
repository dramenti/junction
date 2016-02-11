package jayanth.junction;
import java.io.BufferedReader;

public class Lexer {
    private BufferedReader reader;
    private char current_char;
    private State current_state;
    private String current_token;

    private State transition(typechar c) {
        return table_f[current_state][c];
    }

    private Token lex_token() { 
    }

    private boolean is_final_state(State s) {
        return s > SFINAL;
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
        SFINAL_STRING
    }
    public Lexer() {
        //initialize the finite state machine
        table_f[S_INITIAL][TC_QUOTE] = S_STRING;
        table_f[S_INITIAL][TC_LPAREN] = SFINAL_LPAREN;
        table_f[S_INITIAL][TC_RPAREN] = SFINAL_RPAREN;
        table_f[S_INITIAL][TC_DOT] = S_FLOAT;
        table_f[S_INITIAL][TC_HYPHEN] = S_NUMERIC;
        table_f[S_INITIAL][TC_DIGIT] = S_NUMERIC;
        table_f[S_INITIAL][TC_IDSTART] = S_ID;
        table_f[S_INITIAL][TC_OTHER] = SFINAL_ERROR;

        table_f[S_STRING][TC_QUOTE] = SFINAL_STRING;
        table_f[S_STRING][TC_LPAREN] = S_STRING;
        table_f[S_STRING][TC_RPAREN] = S_STRING;
        table_f[S_STRING][TC_DOT] = S_STRING;
        table_f[S_STRING][TC_HYPHEN] = S_STRING;
        table_f[S_STRING][TC_DIGIT] = S_STRING;
        table_f[S_STRING][TC_IDSTART] = S_STRING;
        table_f[S_STRING][TC_OTHER] = S_STRING;

        table_f[S_NUMERIC][TC_QUOTE] = SFINAL_INTEGER;
        table_f[S_NUMERIC][TC_LPAREN] = SFINAL_INTEGER;
        table_f[S_NUMERIC][TC_RPAREN] = SFINAL_INTEGER;
        table_f[S_NUMERIC][TC_DOT] = S_FLOAT;
        table_f[S_NUMERIC][TC_HYPHEN] = SFINAL_INTEGER;
        table_f[S_NUMERIC][TC_DIGIT] = S_NUMERIC;
        table_f[S_NUMERIC][TC_IDSTART] = SFINAL_INTEGER;
        table_f[S_NUMERIC][TC_OTHER] = SFINAL_INTEGER;

        table_f[S_FLOAT][TC_QUOTE] = SFINAL_FLOAT;
        table_f[S_FLOAT][TC_LPAREN] = SFINAL_FLOAT;
        table_f[S_FLOAT][TC_RPAREN] = SFINAL_FLOAT;
        table_f[S_FLOAT][TC_DOT] = S_FLOAT;
        table_f[S_FLOAT][TC_HYPHEN] = SFINAL_FLOAT;
        table_f[S_FLOAT][TC_DIGIT] = SFINAL_FLOAT;
        table_f[S_FLOAT][TC_IDSTART] = SFINAL_FLOAT;
        table_f[S_FLOAT][TC_OTHER] = SFINAL_FLOAT;

        table_f[S_ID][TC_QUOTE] = SFINAL_ID;
        table_f[S_ID][TC_LPAREN] = SFINAL_ID;
        table_f[S_ID][TC_RPAREN] = SFINAL_ID;
        table_f[S_ID][TC_DOT] = S_ID;
        table_f[S_ID][TC_HYPHEN] = SFINAL_ID;
        table_f[S_ID][TC_DIGIT] = S_ID;
        table_f[S_ID][TC_IDSTART] = S_ID;
        table_f[S_ID][TC_OTHER] = SFINAL_ID;
    }
    public Token next_token() {
        return lex_token();
    }
}
