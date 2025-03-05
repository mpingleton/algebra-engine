package us.pinglesaur.algebraengine.equations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ValueTest {

    @Test
    void constructorTest() {
        Value valueDefault = new Value();
        assertEquals(Value.TYPE_NULL, valueDefault.type);
        assert(!valueDefault.isImaginary);
        assert(!valueDefault.isNegative);
        assert(!valueDefault.isCoeffInit);
        assert(Double.isNaN(valueDefault.coeff));
        assert(valueDefault.name.isEmpty());
        assertNull(valueDefault.func);
        assertNull(valueDefault.biOp);
        assertNull(valueDefault.funcExec);

        Value valueCoeff = new Value(3.14159);
        assertEquals(Value.TYPE_CONSTANT, valueCoeff.type);
        assert(!valueCoeff.isImaginary);
        assert(!valueCoeff.isNegative);
        assert(valueCoeff.isCoeffInit);
        assertEquals(3.14159, valueCoeff.coeff);
        assert(valueCoeff.name.isEmpty());
        assertNull(valueCoeff.func);
        assertNull(valueCoeff.biOp);
        assertNull(valueCoeff.funcExec);

        Value valueName = new Value("x");
        assertEquals(Value.TYPE_VARIABLE, valueName.type);
        assert(!valueName.isImaginary);
        assert(!valueName.isNegative);
        assert(!valueName.isCoeffInit);
        assert(Double.isNaN(valueName.coeff));
        assert(valueName.name.equals("x"));
        assertNull(valueName.func);
        assertNull(valueName.biOp);
        assertNull(valueName.funcExec);

        Value valueCoeffName = new Value(3.14159, "x");
        assertEquals(Value.TYPE_VARIABLE, valueCoeffName.type);
        assert(!valueCoeffName.isImaginary);
        assert(!valueCoeffName.isNegative);
        assert(valueCoeffName.isCoeffInit);
        assertEquals(3.14159, valueCoeff.coeff);
        assert(valueName.name.equals("x"));
        assertNull(valueCoeffName.func);
        assertNull(valueCoeffName.biOp);
        assertNull(valueCoeffName.funcExec);

        BinaryOp biOp = new BinaryOp(valueCoeffName, '+', valueName);

        Value valueBiOp = new Value(biOp);
        assertEquals(Value.TYPE_BINARY_OP, valueBiOp.type);
        assert(!valueBiOp.isImaginary);
        assert(!valueBiOp.isNegative);
        assert(!valueBiOp.isCoeffInit);
        assert(Double.isNaN(valueBiOp.coeff));
        assert(valueBiOp.name.isEmpty());
        assertNull(valueBiOp.func);
        assert(valueBiOp.biOp == biOp);
        assertNull(valueBiOp.funcExec);
    }

    @Test
    void swapTest() {
        Value valA = new Value();
        valA.type = Value.TYPE_CONSTANT;
        valA.isImaginary = true;
        valA.isNegative = false;
        valA.isCoeffInit = true;
        valA.coeff = 3.14159;
        valA.name = "";

        Value valB = new Value();
        valB.type = Value.TYPE_VARIABLE;
        valB.isImaginary = false;
        valB.isNegative = true;
        valB.isCoeffInit = false;
        valB.name = "x";

        valA.swap(valB);

        assertEquals(Value.TYPE_VARIABLE, valA.type);
        assert(!valA.isImaginary);
        assert(valA.isNegative);
        assert(!valA.isCoeffInit);
        assert(Double.isNaN(valA.coeff));

        assertEquals(Value.TYPE_CONSTANT, valB.type);
        assert(valB.isImaginary);
        assert(!valB.isNegative);
        assert(valB.isCoeffInit);
        assertEquals(3.14159, valB.coeff);
    }

    @Test
    void calculateTest() {
        // TODO
    }

    @Test
    void getConstantTest() {
        // TODO
    }

    @Test
    void toStringTest() {
        // TODO
    }
}
