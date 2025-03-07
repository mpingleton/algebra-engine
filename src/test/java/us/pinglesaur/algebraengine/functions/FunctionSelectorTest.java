package us.pinglesaur.algebraengine.functions;

import org.junit.jupiter.api.Test;

public class FunctionSelectorTest {

    @Test
    void testNull() {
        FunctionSelector selector = new FunctionSelector();
        Function f = selector.getFunction(FunctionSelector.RAD, "");
        assert(f == null);
    }

    @Test
    void testAbs() {
        FunctionSelector selector = new FunctionSelector();
        Function f = selector.getFunction(FunctionSelector.RAD, "abs");
        assert(f != null);
        assert(f.name().equals("abs"));
    }

    @Test
    void testSqrt() {
        FunctionSelector selector = new FunctionSelector();
        Function f = selector.getFunction(FunctionSelector.RAD, "sqrt");
        assert(f != null);
        assert(f.name().equals("sqrt"));
    }

    @Test
    void testCbrt() {
        FunctionSelector selector = new FunctionSelector();
        Function f = selector.getFunction(FunctionSelector.RAD, "cbrt");
        assert(f != null);
        assert(f.name().equals("cbrt"));
    }
}
