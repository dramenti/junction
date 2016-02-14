package jayanth.junction;

public class FloatNode implements AST_Node {
    private double value;

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public double getValue() {
        return value;
    }
}
