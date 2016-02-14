package jayanth.junction;

public class LambdaNode implements AST_Node {
    private AST_Node body; 
    private String[] formals;

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }
    public AST_Node getBodyNode() {
        return body;
    }
    public String[] getFormals() {
        return formals;
    }
}

