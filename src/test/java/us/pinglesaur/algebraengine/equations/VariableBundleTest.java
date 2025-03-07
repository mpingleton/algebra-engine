package us.pinglesaur.algebraengine.equations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariableBundleTest {

    @Test
    void valueGetSet() {
        VariableBundle vars = new VariableBundle();
        assert(Double.isNaN(vars.getValue("x")));
        assert(Double.isNaN(vars.getValue("y")));

        vars.setValue("x", 3);
        assertEquals(3, vars.getValue("x"));
        assert(Double.isNaN(vars.getValue("y")));

        vars.setValue("x", 2);
        assertEquals(2, vars.getValue("x"));
        assert(Double.isNaN(vars.getValue("y")));

        vars.setValue("y", 9);
        assertEquals(2, vars.getValue("x"));
        assertEquals(9, vars.getValue("y"));
    }
}
