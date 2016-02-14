package jayanth.junction;

public class OrNode implements AST_Node {
    private AST_Node first; 
    private AST_Node second;

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public AST_Node firstExpression() {
        return first;
    }
    public AST_Node secondExpression() {
        return second;
    }
}

