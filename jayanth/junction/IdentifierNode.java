package jayanth.junction;

public class IdentifierNode implements AST_Node {
    private String name;

    public IdentifierNode(String name) {
        this.name = name;
    }
    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public String getName() {
        return name;
    }
}
