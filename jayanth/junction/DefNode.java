package jayanth.junction;

public class DefNode implements AST_Node {
    private String name;
    private String[] formals;
    private AST_Node body;

    public DefNode(String name, String[] formals, AST_Node body) {
        this.name = name;
        this.formals = formals;
        this.body = body;
    }

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
