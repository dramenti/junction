package jayanth.junction;

public interface AST_Node {
    void accept(AST_Visitor visitor);
}
