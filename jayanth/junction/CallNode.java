package jayanth.junction;

public class CallNode implements AST_Node {
    private AST_Node caller;
    private AST_Node[] args;
    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }
    public AST_Node caller() {
        return caller;
    }
    public AST_Node getIthArgumentNode(int i) {
        return args[i];
    }
}
