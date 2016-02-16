package jayanth.junction;

public class Builtins {

    public static class Add extends Function {
        public JunObject builtin(JunObject[] args) {
            JunObject result = args[0];
            for (int i = 1; i < args.length; i++) {
                result = result.add(args[i]);
            }
            return result;
        } 
        public boolean isBuiltin() {
            return true;
        }
    }

    public static class Sub extends Function {
        public JunObject builtin(JunObject[] args) {
            JunObject result = args[0];
            for (int i = 1; i < args.length; i++) {
                result = result.sub(args[i]);
            }
            return result;
        } 
        public boolean isBuiltin() {
            return true;
        }
    }
    public static class Mul extends Function {
        public JunObject builtin(JunObject[] args) {
            JunObject result = args[0];
            for (int i = 1; i < args.length; i++) {
                result = result.mul(args[i]);
            }
            return result;
        } 
        public boolean isBuiltin() {
            return true;
        }
    }
    public static class Div extends Function {
        public JunObject builtin(JunObject[] args) {
            JunObject result = args[0];
            for (int i = 1; i < args.length; i++) {
                result = result.div(args[i]);
            }
            return result;
        }  
        public boolean isBuiltin() {
            return true;
        }
    }
    public static class Mod extends Function {
        public JunObject builtin(JunObject[] args) {
            //ensure args.length == 2
            JunObject n = args[0];
            JunObject result = n.mod(args[1]);
            return result;
        } 
        public boolean isBuiltin() {
            return true;
        }
    }
    public static class Equals extends Function {
        public JunObject builtin(JunObject[] args) {
            //ensure args.length == 2
            return new BooleanObject(args[0].compare(args[1]) == 0);
        } 
        public boolean isBuiltin() {
            return true;
        }
    }
    public static class LessThan extends Function {
        public JunObject builtin(JunObject[] args) {
            //ensure args.length == 2
            return new BooleanObject(args[0].compare(args[1]) < 0);
        } 
        public boolean isBuiltin() {
            return true;
        }
    }
    public static class GreaterThan extends Function {
        public JunObject builtin(JunObject[] args) {
            //ensure args.length == 2
            return new BooleanObject(args[0].compare(args[1]) > 0);
        } 
        public boolean isBuiltin() {
            return true;
        }
    }
}
