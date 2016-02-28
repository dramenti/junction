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
    public IntObject getIntValue() {
        return new IntObject(getValue() ? 1 : 0);
    }
    public FloatObject getFloatValue() {
        return new FloatObject(getValue() ? 1.0 : 0.0);
    }
    public StringObject getStringValue() {
        //error: cannot get string
        return null;
    }
    public JunObject get(int i) {
        return null;
    }

    //todo: add support for arithmetic on booleans
    public JunObject add(JunObject j) {
        return null;
    }
    public JunObject sub(JunObject j) {
        return null;
    }
    public JunObject mul(JunObject j){
        return null;
    }
    public JunObject div(JunObject j) {
        return null;
    }
    public JunObject mod(JunObject j) {
        return null;
    }
    public int compare(JunObject j) {
        return (isTruthy() == j.isTruthy()) ? 0 : -1;
    }
    public String toString() {
        return getValue() ? "True" : "False" ;
    }
}
