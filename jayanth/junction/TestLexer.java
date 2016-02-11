package jayanth.junction;
import java.io.IOException;
import java.io.FileReader;

public class TestLexer {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer(new FileReader(args[0])); 
        Token tk1 = lexer.next_token();
        Token tk2 = lexer.next_token();
        System.out.println(tk1);
        System.out.println(tk2);
    }
}
