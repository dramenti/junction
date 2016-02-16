package jayanth.junction;

public class FloatObject implements JunObject {
    private double value;
    public FloatObject(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    public FloatObject getFloatValue() {
        return this;
    }
    public IntObject getIntValue() {
        //error: cannot get an int from a float
        return null;
    }
    public StringObject getStringValue() {
        //error: cannot get a string from a float
        return null;
    }
    public boolean isTruthy() {
        return (getValue() != 0.0);
    }
    public Function getFunctionValue() {
        //error: not a function
        return null;
    }
    public JunObject add(JunObject j) {
        return new FloatObject(getValue() + j.getFloatValue().getValue());
    }
    public JunObject sub(JunObject j) {
        return new FloatObject(getValue() - j.getFloatValue().getValue());
    }
    public JunObject mul(JunObject j) {
        return new FloatObject(getValue() * j.getFloatValue().getValue());
    }
    public JunObject div(JunObject j) {
        return new FloatObject(getValue() / j.getFloatValue().getValue());
    }
    public JunObject mod(JunObject j) {
        //error: modulo not supported on floats
        return null;
    }
    public int compare(JunObject j) {
        return Double.compare(getValue(), j.getFloatValue().getValue());
    }
}
