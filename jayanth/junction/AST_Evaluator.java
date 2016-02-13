package jayanth.junction;

public class AST_Evaluator implements AST_Visitor {

    JunObject visit(CallNode node, JunFrame frame) {
        //evaluate the caller expression
        Function function = node.caller().accept(this, frame);
        //get # of args for function
        //for each arg of the function, get the JunObject of the node's
        //next one
        JunFrame next_frame = new JunFrame(function.getParentFrame()); 

        //bind formal parameters in new frame
        for (int i = 0; i < function.getArgCount(); i++) {
            AST_Node arg = node.getIthArgumentNode(i);
            JunObject parameter = arg.accept(this, frame);
            next_frame.add(new String(function.getIthFormal(i)), parameter);
        }
        //next_frame is the new frame
        //with formal parameters bound to values
        //now just execute the function within the frame
        return function.getBodyNode().accept(this, next_frame);
    }
    JunObject visit(DefNode node, JunFrame frame) {
        Function function = new Function(node.getFunctionName(), node.getFormals(), frame, node.getBodyNode());
        frame.add(new String(node.getFunctionName()), function);
        return function;
    }
    JunObject visit(LambdaNode node, JunFrame frame) {
        //do the same thing as a DefNode, except
        Function lambda = new Function("Lambda", node.getFormals(), frame, node.getBodyNode());
        return lambda;
    }
    JunObject visit(BooleanNode node, JunFrame frame);
    JunObject visit(IdentifierNode node, JunFrame frame) {
        return frame.getValueInCurrentEnvironment(node.getName());
    }
    JunObject visit(StringNode node, JunFrame frame) {
        return new StringObject(node.getValue());
    }
    JunObject visit(FloatNode node, JunFrame frame);
    JunObject visit(IntNode node, JunFrame frame);
}
