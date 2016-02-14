package jayanth.junction;

public class FloatObject implements JunObject {
    private double value;
    public FloatObject(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    public boolean isTruthy() {
        return (getValue() != 0.0);
    }
    public Function getFunctionValue() {
        //error
        return null;
    }
}
