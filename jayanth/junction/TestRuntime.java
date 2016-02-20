package jayanth.junction;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestRuntime {
    public static void main(String[] args) {
        Runtime runtime = new Runtime();
        try {
            runtime.repl2();
        } catch (IOException e) {
            System.err.println("IOException while attempting REPL: " + e.getMessage());
        }
    }
}
