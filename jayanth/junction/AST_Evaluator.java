package jayanth.junction;

public class AST_Evaluator implements AST_Visitor {

    JunObject visit(CallNode node, JunFrame frame) {
        //evaluate the caller expression
        Function function = node.caller().accept(this);
        //get # of args for function
        //for each arg of the function, get the JunObject of the node's
        //next one
        JunFrame child = new JunFrame(frame); //parent=frame
        for (int i = 0; i < function.getArgCount(); i++) {
            AST_Node arg = node.getIthArgumentNode(i);
            JunObject parameter = arg.accept(this);
            child.add(new String(function.getIthFormal(i)), parameter);
        }
    }
    JunObject visit(DefNode node, JunFrame frame) {
        Function function = new Function(node.getFunctionName(), node.getFormals(), frame);
        frame.add(new String(node.getFunctionName()), function);
        return function;
    }
    JunObject visit(LambdaNode node, JunFrame frame) {
    }
    JunObject visit(BooleanNode node, JunFrame frame);
    JunObject visit(IdentifierNode node, JunFrame frame) {
        return frame.getValueInCurrentEnvironment(node.getName());
    }
    JunObject visit(StringNode node, JunFrame frame);
    JunObject visit(FloatNode node, JunFrame frame);
    JunObject visit(IntNode node, JunFrame frame);
}
