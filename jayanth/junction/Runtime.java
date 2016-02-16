package jayanth.junction;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

public class Runtime {
    Parser parser;
    AST_Evaluator evaluator;
    JunFrame global;

    public Runtime(Reader in) throws IOException {
       // parser = new Parser(in);
        evaluator = new AST_Evaluator();
        global = new JunFrame(null, "Global"); //global frame
        global.add("+", new Builtins.Add());
        global.add("-", new Builtins.Sub());
        global.add("*", new Builtins.Mul());
        global.add("/", new Builtins.Mul());
        global.add("=", new Builtins.Equals());
        global.add(">", new Builtins.GreaterThan());
        global.add("<", new Builtins.LessThan());
    }

    public void repl() throws IOException {
        Scanner s = new Scanner(System.in); 
        System.out.print(">>> ");
        StringReader strReader = new StringReader(s.nextLine());
        parser = new Parser(strReader);
        AST_Node node = parser.parse();
        while (node != null) {
            JunObject result = node.accept(evaluator, global);
            System.out.println(result);
            //System.out.println(node.accept(evaluator, global));
            System.out.print(">>> ");
            strReader = new StringReader(s.nextLine());
            parser = new Parser(strReader);
            node = parser.parse();
        } 
    }
    
}
