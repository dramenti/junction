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
        //error: not a function
        return null;
    }
    public IntObject getIntValue() {
        return this;
    }
    public FloatObject getFloatValue() {
        return new FloatObject((double)getValue());
    }
    public StringObject getStringValue() {
        //error: not castable to String!
        return null;
    }
    public JunObject add(JunObject j) {
        //maybe refactor later to avoid using instanceof
        //though maybe in this special case it is justified 
        if (j instanceof FloatObject) {
            return new FloatObject(getValue() + j.getFloatValue().getValue());
        }
        return new IntObject(getValue() + j.getIntValue().getValue());
    }
    public JunObject sub(JunObject j) {
        if (j instanceof FloatObject) {
            return new FloatObject(getValue() - j.getFloatValue().getValue());
        }
        return new IntObject(getValue() - j.getIntValue().getValue());
    }
    public JunObject mul(JunObject j) {
        if (j instanceof FloatObject) {
            return new FloatObject(getValue() * j.getFloatValue().getValue());
        }
        return new IntObject(getValue() * j.getIntValue().getValue());
    }
    public JunObject div(JunObject j) {
        if (j instanceof FloatObject) {
            return new FloatObject(getValue() / j.getFloatValue().getValue());
        }
        return new IntObject(getValue() / j.getIntValue().getValue());
    }
    public JunObject mod(JunObject j) {
        return new IntObject(getValue() % j.getIntValue().getValue());
    }
    public int compare(JunObject j) {
        return getValue() - j.getIntValue().getValue();
    }
}
