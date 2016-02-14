package jayanth.junction;

public class BooleanNode implements AST_Node {
    private boolean value;

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public boolean getValue() {
        return value;
    }
}
