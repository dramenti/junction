public interface AST_Visitor {
    void visit(CallNode node);
    void visit(DefNode node);
    void visit(LambdaNode node);
    void visit(BooleanNode node);
    void visit(IdentifierNode node);
    void visit(StringNode node);
    void visit(FloatNode node);
    void visit(IntNode node);
}
