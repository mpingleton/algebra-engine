package us.pinglesaur.algebraengine.functions;

import org.junit.jupiter.api.Test;
import us.pinglesaur.algebraengine.equations.Value;
import us.pinglesaur.algebraengine.equations.VariableBundle;

public class FunctionTest {

    @Test
    void testName() {
        Function f1 = new Function();
        assert(f1.name().isEmpty());
    }

    @Test
    void testForwardExecute() {
        VariableBundle bundle = new VariableBundle();
        Value[] v1 = new Value[1];
        v1[0] = new Value(3.14159);

        Function f1 = new Function();
        Value[] o1 = f1.forwardExecute(v1, -100, 100);
        assert(o1 == null);
    }

    @Test
    void testReverseExecute() {
        VariableBundle bundle = new VariableBundle();
        Value[] v1 = new Value[1];
        v1[0] = new Value(3.14159);

        Function f1 = new Function();
        Value[] o1 = f1.reverseExecute(v1, -100, 100);
        assert(o1 == null);
    }
}
