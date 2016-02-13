package jayanth.junction;

public interface AST_Node {
    JunObject accept(AST_Visitor visitor, JunFrame frame);
}
