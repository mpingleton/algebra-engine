/*
 * Created 24-Jul-2024
 */

import java.util.ArrayList;
import java.util.List;

public class VariableBundle {

    protected List<Variable> variables;

    public VariableBundle() {
        variables = new ArrayList<>();
    }

    public void setValue(String name, float value) {
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).name.equals(name)) {
                variables.get(i).value = value;
            }
        }

        Variable v = new Variable();
        v.name = name;
        v.value = value;
        variables.add(v);
    }

    public double getValue(String name) {
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).name.equals(name)) {
                return variables.get(i).value;
            }
        }

        return Double.NaN;
    }
}
