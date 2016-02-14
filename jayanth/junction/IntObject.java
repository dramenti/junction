package jayanth.junction;

public class IntObject implements JunObject {
    private int value;

    public IntObject(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public boolean isTruthy() {
        return (getValue() != 0);
    }
    public Function getFunctionValue() {
        //error
        return null;
    }
}
