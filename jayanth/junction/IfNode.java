package jayanth.junction;

public class IfNode implements AST_Node {
    private AST_Node condition;
    private AST_Node body1; //evaluate body1 if condition is true 
    private AST_Node body2; //else evaluate body1

    public IfNode(AST_Node condition, AST_Node body1, AST_Node body2) {
        this.condition = condition;
        this.body1 = body1;
        this.body2 = body2;
    }

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public AST_Node getCondition() {
        return condition;
    }
    public AST_Node getBody1() {
        return body1;
    }
    public AST_Node getBody2() {
        return body2;
    }
}
