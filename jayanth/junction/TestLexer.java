package jayanth.junction;
import java.io.IOException;
import java.io.FileReader;

public class TestLexer {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer(new FileReader(args[0])); 
        Token t;
        do {
            t = lexer.next_token();
            System.out.println(t);
        } while (t.getTokenType() != TokenType.EOF);
    }
}
