package jayanth.junction;

public class Function implements JunObject {
    private AST_Node body;
    private JunFrame parent;
    private String[] formals;
    private String intrinsic;

    public Function(String intrinsic, String[] formals, JunFrame parent, AST_Node body) {
        this.intrinsic = intrinsic;
        this.formals = formals;
        this.parent = parent;
        this.body = body;
    }

    public boolean isTruthy() {
        return true;
    }
    public Function getFunctionValue() {
        return this;
    }
    public JunFrame getParentFrame() {
        return parent;
    }
    public String getIthFormal(int i) {
        return formals[i];
    }
    public int getArgCount() {
        return formals.length;
    }
    public AST_Node getBodyNode() {
        return body;
    }
    public String getIntrinsic() {
        return intrinsic;
    }
    public String toString() {
        return "<function " + getIntrinsic() + " : scope "+ getParentFrame().getName() + ">";
    }
}
