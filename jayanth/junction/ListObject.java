package jayanth.junction;

public class ListObject implements JunObject {
    private JunObject[] array;

    public ListObject(JunObject[] array) {
        this.array = array;
    }
    public Function getFunctionValue() {
        //error not a function
        return null;
    }
    public IntObject getIntValue() {
        return null;
    }
    public FloatObject getFloatValue() {
        return null;
    }
    public StringObject getStringValue() {
        return null;
    }
    public boolean isEmpty() {
        return array.length == 0;
    }
    public boolean isTruthy() {
        return !isEmpty();
    }

    public JunObject get(int i) {
        return array[i];
    }
    //remember to implement add later for list concatenation
    public JunObject add(JunObject j) { return null; };
    public JunObject sub(JunObject j) { return null; };
    public JunObject mul(JunObject j) { return null; };
    public JunObject div(JunObject j) { return null; };
    public JunObject mod(JunObject j) { return null; };
    public int compare(JunObject j)   {
        return 1; //change this to make more sense later
    }
}
