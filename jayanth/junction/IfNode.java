package jayanth.junction;

public class IfNode implements AST_Node {
    AST_Node condition;
    AST_Node body1; //evaluate body1 if condition is true 
    AST_Node body2; //else evaluate body1

    public IfNode(AST_Node condition, AST_Node body1, AST_Node body2) {
        this.condition = condition;
        this.body1 = body1;
        this.body2 = body2;
    }

    JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }
}
