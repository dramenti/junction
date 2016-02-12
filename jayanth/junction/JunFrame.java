package jayanth.junction;

public class JunFrame {
    private JunFrame parent; //null for global
    private Map<String, JunObject> symbol_table;

    public boolean isGlobal() {
        return parent == null;
    }

    public Map<String, JunObject> getSymbolTable() {
        return symbol_table;
    }
}
