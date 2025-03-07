package us.pinglesaur.algebraengine.functions;

import org.junit.jupiter.api.Test;
import us.pinglesaur.algebraengine.equations.Value;
import us.pinglesaur.algebraengine.equations.VariableBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CbrtTest {

    @Test
    void testName() {
        Cbrt f1 = new Cbrt();
        assert(f1.name().equals("cbrt"));
    }

    @Test
    void testForwardExecute() {
        VariableBundle bundle = new VariableBundle();
        Value[] v1 = new Value[1];
        v1[0] = new Value(27);
        Value[] v2 = new Value[1];
        v2[0] = new Value(-27);

        Cbrt f1 = new Cbrt();
        Value[] o1 = f1.forwardExecute(v1, -100, 100);
        Value[] o2 = f1.forwardExecute(v2, -100, 100);

        assert(o1 != null && o2 != null);
        assert(o1.length == 1);
        assert(o2.length == 1);
        assert(!o1[0].isImaginary);
        assert(o2[0].isImaginary);
        assertEquals(3, o1[0].getConstant());
        assertEquals(3, o2[0].getConstant());
    }

    @Test
    void testBackwardExecute() {
        VariableBundle bundle = new VariableBundle();
        Value[] v1 = new Value[1];
        v1[0] = new Value(3);
        v1[0].isImaginary = false;
        Value[] v2 = new Value[1];
        v2[0] = new Value(3);
        v2[0].isImaginary = true;

        Cbrt f1 = new Cbrt();
        Value[] o1 = f1.reverseExecute(v1, -100, 100);
        Value[] o2 = f1.reverseExecute(v2, -100, 100);

        assert(o1 != null && o2 != null);
        assert(o1.length == 1);
        assert(o2.length == 1);

        assertEquals(27, o1[0].getConstant());
        assertEquals(-27, o2[0].getConstant());
    }
}
