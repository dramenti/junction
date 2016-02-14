package jayanth.junction;

public class StringNode implements AST_Node {
    private String value;

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public String getValue() {
        return value;
    }
}
