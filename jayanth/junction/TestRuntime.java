package jayanth.junction;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestRuntime {
    public static void main(String[] args) throws IOException {
        Runtime runtime = new Runtime(new InputStreamReader(System.in));
        runtime.repl();
    }
}
