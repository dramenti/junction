package jayanth.junction;

public interface JunObject {
    boolean isTruthy();
    int compare(JunObject j);
    Function getFunctionValue();
    IntObject getIntValue();
    FloatObject getFloatValue();
    StringObject getStringValue();
    JunObject add(JunObject j);
    JunObject sub(JunObject j);
    JunObject mul(JunObject j);
    JunObject div(JunObject j);
    JunObject mod(JunObject j);
}
