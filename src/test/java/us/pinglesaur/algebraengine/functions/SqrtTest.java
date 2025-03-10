package us.pinglesaur.algebraengine.functions;

import org.junit.jupiter.api.Test;
import us.pinglesaur.algebraengine.equations.Value;
import us.pinglesaur.algebraengine.equations.VariableBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqrtTest {

    @Test
    void testName() {
        Sqrt f1 = new Sqrt();
        assert(f1.name().equals("sqrt"));
    }

    @Test
    void testForwardExecute() {
        VariableBundle bundle = new VariableBundle();
        Value[] v1 = new Value[1];
        v1[0] = new Value(9);
        Value[] v2 = new Value[1];
        v2[0] = new Value(-9);
        Value[] v3 = new Value[1];
        v3[0] = new Value(0);

        Sqrt f1 = new Sqrt();
        Value[] o1 = f1.forwardExecute(v1, -100, 100);
        Value[] o2 = f1.forwardExecute(v2, -100, 100);
        Value[] o3 = f1.forwardExecute(v3, -100, 100);

        assert(o1 != null && o2 != null && o3 != null);
        assert(o1.length == 1);
        assert(o2.length == 1);
        assert(o3.length == 1);
        assert(!o1[0].isImaginary);
        assert(o2[0].isImaginary);
        assert(!o3[0].isImaginary);
        assertEquals(3, o1[0].getConstant());
        assertEquals(3, o2[0].getConstant());
        assertEquals(0, o3[0].getConstant());
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
        Value[] v3 = new Value[1];
        v3[0] = new Value(0);
        v3[0].isImaginary = false;

        Sqrt f1 = new Sqrt();
        Value[] o1 = f1.reverseExecute(v1, -100, 100);
        Value[] o2 = f1.reverseExecute(v2, -100, 100);
        Value[] o3 = f1.reverseExecute(v3, -100, 100);

        assert(o1 != null && o2 != null && o3 != null);
        assert(o1.length == 1);
        assert(o2.length == 1);
        assert(o3.length == 1);
        assert(!o1[0].isImaginary);
        assert(!o2[0].isImaginary);
        assert(!o3[0].isImaginary);
        assertEquals(9, o1[0].getConstant());
        assertEquals(-9, o2[0].getConstant());
        assertEquals(0, o3[0].getConstant());
    }
}
