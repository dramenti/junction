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

    public Function() {
        this.intrinsic = "builtin";
    }

    public boolean isTruthy() {
        return true;
    }
    public JunObject builtin(JunObject[] args) {
        //throw error: Function is not builtin
        return null;
    }
    public boolean isBuiltin() {
        return false;
    }
    public Function getFunctionValue() {
        return this;
    }
    public IntObject getIntValue() {
        //error: cannot get int from function
        return null;
    }
    public FloatObject getFloatValue() {
        //error: cannot get float from function
        return null;
    }
    public StringObject getStringValue() {
        //error: cannot get String from function
        return null;
    }
    public JunObject add(JunObject j) {
        //error: cannot add functions
        return null;
    }
    public JunObject sub(JunObject j) {
        //error: cannot add functions
        return null;
    }
    public JunObject mul(JunObject j) {
        //error: cannot multiply functions
        return null;
    }
    public JunObject div(JunObject j) {
        //error: cannot divide functions
        return null;
    }
    public JunObject mod(JunObject j) {
        //error: cannot modulo functions
        return null;
    }
    public int compare(JunObject j) {
        return (this == j.getFunctionValue()) ? 1 : 0;
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
