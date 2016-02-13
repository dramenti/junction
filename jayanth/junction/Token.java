package jayanth.junction;

public class Token {
    private TokenType type;
    private Semantic value;

    public Token(TokenType t) {
        value = new Semantic();
        type = t;
    }
    public Token(TokenType t, String s) {
        value = new Semantic();
        type = t;
        value.v_string = new String(s);
    }
    public Token(TokenType t, int i) {
        value = new Semantic();
        type = t;
        value.v_int = i;
    }
    public Token(TokenType t, double d) {
        value = new Semantic();
        type = t;
        value.v_float = d;
    }
    public Token(TokenType t, boolean b) {
        value = new Semantic();
        type = t;
        value.v_bool = b;
    }

    public Semantic getValue() {
        return value;
    }

    public TokenType getTokenType() {
        return type;
    }

    public String toString() {
        String strtype = "Invalid, ";
        String strsemantic = "";
        switch(type) {
            case EOF: strtype = "Token type: EOF, "; break;
            case DEF: strtype = "Token type: DEF, "; break;
            case IF: strtype = "Token type: IF, "; break;
            case LAMBDA: strtype = "Token type: LAMBDA, "; break;
            case OR: strtype = "Token type: OR, "; break;
            case AND: strtype = "Token type: AND, "; break;
            case LPAREN: strtype = "Token type: LPAREN, "; break;
            case RPAREN: strtype = "Token type: RPAREN, "; break;
            case ID:        strtype = "Token type: ID, ";
                            strsemantic = value.v_string;
                            break;
            case INTEGER:   strtype = "Token type: INTEGER, "; 
                            strsemantic = Integer.toString(value.v_int);
                            break;
            case FLOAT:     strtype = "Token type: FLOAT, ";
                            strsemantic = Double.toString(value.v_float);
                            break;
            case STRING:    strtype = "Token type: STRING, ";
                            strsemantic = value.v_string;
                            break;
            case BOOLEAN:   strtype = "Token type: BOOLEAN, ";
                            strsemantic = Boolean.toString(value.v_bool);
                            break;
            default: break;
        }
        return strtype + strsemantic;
    }
}
