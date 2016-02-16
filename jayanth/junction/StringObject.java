package jayanth.junction;

public class StringObject implements JunObject {
    private String value;

    public StringObject(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public boolean isTruthy() {
        return value.equals("") ? false:true;
    }
    public Function getFunctionValue() {
        //error
        return null;
    }
    public StringObject getStringValue() {
        return this;
    }
    public IntObject getIntValue() {
        //throw an error
        return null;
    }
    public FloatObject getFloatValue() {
        //throw an error
        return null;
    }
    public JunObject add(JunObject j) {
        return null;
    }
    public JunObject sub(JunObject j) {
        return null;
    }
    public JunObject mul(JunObject j) {
        return null;
    }
    public JunObject div(JunObject j) {
        return null;
    }
    public JunObject mod(JunObject j) {
        return null;
    }
    public int compare(JunObject j) {
        return getValue().compareTo(j.getStringValue().getValue());
    }
}
