package jayanth.junction;

public interface AST_Visitor {
    JunObject visit(CallNode node, JunFrame frame);
    JunObject visit(DefNode node, JunFrame frame);
    JunObject visit(LambdaNode node, JunFrame frame);
    JunObject visit(BooleanNode node, JunFrame frame);
    JunObject visit(IdentifierNode node, JunFrame frame);
    JunObject visit(StringNode node, JunFrame frame);
    JunObject visit(FloatNode node, JunFrame frame);
    JunObject visit(IntNode node, JunFrame frame);
    JunObject visit(AndNode node, JunFrame frame);
    JunObject visit(OrNode node, JunFrame frame);
    JunObject visit(IfNode node, JunFrame frame);
}
