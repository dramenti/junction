package jayanth.junction;

public class IntNode implements AST_Node {
    private int value;

    public IntNode(int value) {
        this.value = value;
    }

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public int getValue() {
        return value;
    }
}
