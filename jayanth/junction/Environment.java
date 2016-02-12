package jayanth.junction;

public class Environment {
    
    JunObject executeExpression(AST_Node node, JunFrame frame) {
        //get type of node
        //node types are:
        //composite: call, definition, lambda
        //base node literals: identifier, integer, float, string, boolean 

        //Evaluating a call:
        //a call node contains a reference to -
        //  another node that evaluates to the caller
        //  0 or more nodes which are evaluated,
        //  then passed into the function 
        //  then create a new frame for the new function
        //  then call execute expression on the function's node
        //  (with the new frame)
    }
}
