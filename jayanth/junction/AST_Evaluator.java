package jayanth.junction;

public class AST_Evaluator implements AST_Visitor {

    public JunObject visit(CallNode node, JunFrame frame) {
        //evaluate the caller expression
        Function function = node.caller().accept(this, frame).getFunctionValue();
        //check if function is builtin:
        if (function.isBuiltin() ) {
            //do stuff
            int argcount = node.getArgumentNodesLength();
            //System.out.println(argcount);
            JunObject[] params = new JunObject[argcount];
            for (int i = 0; i < argcount; i++) {
                AST_Node arg = node.getIthArgumentNode(i);
                params[i] = arg.accept(this, frame);
                //System.out.println(params[i]);
            }
            return function.builtin(params);
        }

        //get # of args for function
        //for each arg of the function, get the JunObject of the node's
        //next one
        JunFrame next_frame = new JunFrame(function.getParentFrame(), function.getIntrinsic(), function); 

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
    public JunObject visit(IterBodyNode node, JunFrame frame) {
        //for each arg in 
        //evaluate node.condition. While condition not true,
        //Evaluate each 'rebind' expression, 
        //then rebind parameters using the same frame
        //repeat until condition is true, then return:
        //base case node.accept(this, frame)
        while(!node.getCondition().accept(this, frame).isTruthy()) {
            JunFrame next_frame = new JunFrame(frame.getParent(), frame.getName(), frame.getCaller());
            for (int i = 0; i < frame.getCaller().getArgCount(); i++) {
                AST_Node arg = node.getIthRebindNode(i);
                JunObject parameter = arg.accept(this, frame);
                next_frame.add(frame.getCaller().getIthFormal(i), parameter);
            }
            frame = next_frame;
        }

        return node.getBase().accept(this, frame);
    }

    public JunObject visit(DefNode node, JunFrame frame) {
        Function function = new Function(node.getFunctionName(), node.getFormals(), frame, node.getBodyNode());
        frame.add(new String(node.getFunctionName()), function);
        return function;
    }
    public JunObject visit(LambdaNode node, JunFrame frame) {
        //do the same thing as a DefNode, except
        return new Function(".lambda", node.getFormals(), frame, node.getBodyNode());
    }
    public JunObject visit(AndNode node, JunFrame frame) {
        boolean result = false;
        JunObject a = node.firstExpression().accept(this, frame);
        if (a.isTruthy()) {
            JunObject b = node.secondExpression().accept(this, frame);
            if (b.isTruthy()) result = true;
        }
        return new BooleanObject(result);
    }
    public JunObject visit(OrNode node, JunFrame frame) {
        boolean result = true;
        JunObject a = node.firstExpression().accept(this, frame);
        if (!a.isTruthy()) {
            JunObject b = node.secondExpression().accept(this, frame);
            if (!b.isTruthy()) result = false;
        }
        return new BooleanObject(result);
    }
    public JunObject visit(IfNode node, JunFrame frame) {
        JunObject condition = node.getCondition().accept(this, frame);
        if (condition.isTruthy()) {
            return node.getBody1().accept(this, frame);
        }
        return node.getBody2().accept(this, frame);
    }
    public JunObject visit(BooleanNode node, JunFrame frame) {
        return new BooleanObject(node.getValue());
    }
    public JunObject visit(IdentifierNode node, JunFrame frame) {
        return frame.getValueInCurrentEnvironment(node.getName());
    }
    public JunObject visit(StringNode node, JunFrame frame) {
        return new StringObject(node.getValue());
    }
    public JunObject visit(FloatNode node, JunFrame frame) {
        return new FloatObject(node.getValue());
    }
    public JunObject visit(IntNode node, JunFrame frame) {
        return new IntObject(node.getValue());
    }
}
