package us.pinglesaur.algebraengine.equations;

import org.junit.jupiter.api.Test;
import us.pinglesaur.algebraengine.functions.Abs;

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
        VariableBundle vars = new VariableBundle();
        vars.setValue("x", 3.14159265);

        Value vNull = new Value();
        Value[] oNull = vNull.calculate(vars);
        assertNull(oNull);

        Value vConst = new Value(3.14159265);
        Value[] oConst = vConst.calculate(vars);
        assert(oConst != null);
        assert(oConst.length == 1);
        assertEquals(Value.TYPE_CONSTANT, oConst[0].type);
        assert(!oConst[0].isImaginary);
        assert(oConst[0].isCoeffInit);
        assertEquals(3.14159265, oConst[0].coeff);

        Value vVar1 = new Value("x");
        Value vVar2 = new Value("y");
        Value[] oVar1 = vVar1.calculate(vars);
        Value[] oVar2 = vVar2.calculate(vars);
        assert(oVar1 != null && oVar2 != null);
        assert(oVar1.length == 1 && oVar2.length == 1);
        assertEquals(Value.TYPE_CONSTANT, oVar1[0].type);
        assertEquals(Value.TYPE_VARIABLE, oVar2[0].type);
        assert(!oVar1[0].isImaginary);
        assert(!oVar2[0].isImaginary);
        assert(oVar1[0].isCoeffInit);
        assert(!oVar2[0].isCoeffInit);
        assertEquals(3.14159265, oVar1[0].coeff);
        assert(Double.isNaN(oVar2[0].coeff));
        assert(oVar1[0].name.isEmpty());
        assert(oVar2[0].name.equals("y"));

        Value vFunc = new Value();
        vFunc.type = Value.TYPE_FUNCTION;
        vFunc.isImaginary = false;
        vFunc.isNegative = false;
        vFunc.isCoeffInit = false;
        vFunc.name = "abs";
        vFunc.func = new Value[1];
        vFunc.func[0] = new Value(-12);
        vFunc.funcExec = new Abs();
        Value[] oFunc = vFunc.calculate(vars);
        assert(oFunc != null);
        assert(oFunc.length == 1);
        assertEquals(Value.TYPE_CONSTANT, oFunc[0].type);
        assert(!oFunc[0].isImaginary);
        assert(oFunc[0].isCoeffInit);
        assertEquals(3.14159265, oConst[0].coeff);

        BinaryOp biOp = new BinaryOp(new Value(5), '+', new Value(2));
        Value vBinaryOp = new Value(biOp);
        Value[] oBinaryOp = vBinaryOp.calculate(vars);
        assert(oBinaryOp != null);
        assert(oBinaryOp.length == 1);
        assertEquals(Value.TYPE_CONSTANT, oBinaryOp[0].type);
        assert(!oBinaryOp[0].isImaginary);
        assert(oBinaryOp[0].isCoeffInit);
        assertEquals(7, oBinaryOp[0].coeff);
    }

    @Test
    void getConstantTest() {
        Value v1 = new Value(3.141);
        assertEquals(3.141, v1.getConstant());

        Value v2 = new Value(3.141);
        v2.isNegative = true;
        assertEquals(-3.141, v2.getConstant());

        Value v3 = new Value(-3.141);
        v3.isNegative = true;
        assertEquals(3.141, v3.getConstant());

        Value v4 = new Value(-3.141);
        v4.isImaginary = true;
        assert(Double.isNaN(v4.getConstant()));
    }

    @Test
    void toStringTest() {
        Value v1 = new Value(3.14159);
        assert(v1.toString().equals("3.14159"));

        Value v2 = new Value(3.14159);
        v2.isNegative = true;
        assert(v2.toString().equals("-3.14159"));

        Value v3 = new Value(3.14159);
        v3.isNegative = true;
        v3.isImaginary = true;
        assert(v3.toString().equals("-3.14159i"));

        BinaryOp b1 = new BinaryOp(v1, '+', v2);
        Value v4 = new Value(b1);
        assert(v4.toString().equals("3.14159+-3.14159"));

        Value v5 = new Value("x");
        assert(v5.toString().equals("x"));

        Value v6 = new Value(3, "x");
        assert(v6.toString().equals("3.0x"));

        Value v7 = new Value();
        v7.type = Value.TYPE_FUNCTION;
        v7.name = "abs";
        v7.func = new Value[2];
        v7.func[0] = new Value(7, "x");
        v7.func[1] = new Value(1);
        assert(v7.toString().equals("abs(7.0x,1.0)"));

        Value v8 = new Value();
        assert(v8.toString().isEmpty());
    }
}
