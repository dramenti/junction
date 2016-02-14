package jayanth.junction;
import java.util.Map;
import java.util.HashMap;

public class JunFrame {
    private JunFrame parent; //null for global
    private String name;
    private Map<String, JunObject> symbol_table;

    public JunFrame(JunFrame parent, String name) {
        this.parent = parent;
        this.name = name;
        this.symbol_table = new HashMap<String, JunObject>();
    }

    public boolean isGlobal() {
        return parent == null;
    }

    public Map<String, JunObject> getSymbolTable() {
        return symbol_table;
    }
    public JunFrame getParent() {
        return parent;
    }
    public String getName() {
        return name;
    }

    public void add(String name, JunObject value) {
        symbol_table.put(name, value);
    }
    public JunObject getValueInCurrentEnvironment(String name) {
        JunObject value;
        JunFrame frame = this;
        while (frame != null) {
            value = frame.getSymbolTable().get(name);
            if (value != null) return value;
            frame = frame.getParent();
        }
        //value was always null, Name Error!
        return null; //ERROR! Name `name` not defined in current environment
    }
}
