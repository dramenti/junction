package jayanth.junction;

public class BooleanObject implements JunObject {
    private boolean value;    

    public BooleanObject(boolean value) {
        this.value = value;
    }
    public boolean getValue() {
        return value;
    }
    public boolean isTruthy() {
        return getValue();
    }
    public Function getFunctionValue() {
        //error
        return null;
    }
}
