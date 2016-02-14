package jayanth.junction;

public class DefNode implements AST_Node {
    private String[] formals;
    private String name;
    private AST_Node body;

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }
    public String getFunctionName() {
        return name;
    }
    public String[] getFormals() {
        return formals;
    }
    public AST_Node getBodyNode() {
        return body;
    }
}
