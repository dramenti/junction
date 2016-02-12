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
    public JunFrame getParent() {
        return parent;
    }

    public void add(String name, JunObject value) {
        symbol_table.put(name, value);
    }
    public JunObject getValueInCurrentEnvironment(String name) {
        JunObject value;
        JunFrame frame = this;
        while (frame != null) {
            value = frame.getSymbolTable.get(name);
            if (value != null) return value;
            frame = frame.getParent();
        }
        //value was always null, Name Error!
        return null; //ERROR! Name `name` not defined in current environment
    }
}
