package jayanth.junction;

public class IterBodyNode implements AST_Node {
    private AST_Node condition;
    private AST_Node[] rebinds;
    private AST_Node base;

    public IterBodyNode(AST_Node condition, AST_Node[] rebinds, AST_Node base) {
        this.condition = condition;
        this.rebinds = rebinds;
        this.base = base;
    }

    public JunObject accept(AST_Visitor visitor, JunFrame frame) {
        return visitor.visit(this, frame);
    }

    public AST_Node getCondition() {
        return condition;
    }
    public AST_Node getIthRebindNode(int i) {
        return rebinds[i];
    }
    public AST_Node getBase() {
        return base;
    }
}
