package jayanth.junction;

public class StringObject implements JunObject {
    String value;

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

}
