package jayanth.junction;

public class IdentifierNode implements AST_Node {
    String name;

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public String getName() {
        return name;
    }
}
