package us.pinglesaur.algebraengine.equations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryOpTest {

    @Test
    void testOperatorPrecedence() {
        assertEquals(-1, BinaryOp.operatorPrecedence(BinaryOp.NULL));
        assertEquals(1, BinaryOp.operatorPrecedence(BinaryOp.EQU));
        assertEquals(2, BinaryOp.operatorPrecedence(BinaryOp.ADD));
        assertEquals(2, BinaryOp.operatorPrecedence(BinaryOp.SUB));
        assertEquals(3, BinaryOp.operatorPrecedence(BinaryOp.MUL));
        assertEquals(3, BinaryOp.operatorPrecedence(BinaryOp.DIV));
        assertEquals(3, BinaryOp.operatorPrecedence(BinaryOp.MOD));
        assertEquals(4, BinaryOp.operatorPrecedence(BinaryOp.POW));
    }

    @Test
    void testSwap() {
        BinaryOp opA = new BinaryOp(new Value(3, "x"), BinaryOp.ADD, new Value(4, "x"));
        BinaryOp opB = new BinaryOp(new Value(3.141), BinaryOp.POW, new Value(2));

        opA.swap(opB);
        assertEquals(Value.TYPE_CONSTANT, opA.l.type);
        assertEquals(Value.TYPE_CONSTANT, opA.r.type);
        assertEquals(Value.TYPE_VARIABLE, opB.l.type);
        assertEquals(Value.TYPE_VARIABLE, opB.r.type);
    }

    @Test
    void testTakesPrecedenceOver() {
        BinaryOp opA = new BinaryOp(new Value(3, "x"), BinaryOp.ADD, new Value(4, "x"));
        BinaryOp opB = new BinaryOp(new Value(3, "x"), BinaryOp.ADD, new Value(4));
        BinaryOp opC = new BinaryOp(new Value(3, "x"), BinaryOp.SUB, new Value(5));
        BinaryOp opD = new BinaryOp(new Value(3, "x"), BinaryOp.MUL, new Value(6));
        BinaryOp opE = new BinaryOp(new Value(3, "x"), BinaryOp.DIV, new Value(7));
        BinaryOp opF = new BinaryOp(new Value(3.141), BinaryOp.POW, new Value(8));

        assert(!opA.takesPrecedenceOver(opB));
        assert(!opC.takesPrecedenceOver(opD));
        assert(opD.takesPrecedenceOver(opC));
        assert(opF.takesPrecedenceOver(opE));
        assert(!opE.takesPrecedenceOver(opF));
        assert(opF.takesPrecedenceOver(opC));
        assert(!opC.takesPrecedenceOver(opF));
    }

    @Test
    void testInitOp() {
        Value v1 = new Value(2);
        Value v2 = new Value(3);
        BinaryOp op = new BinaryOp();

        op.initOp(BinaryOp.ADD);

        op.initLVal(v1);
        assert(op.l != null);
        assert(op.r == null);

        op.initRVal(v2);
        assert(op.l != null);
        assert(op.r != null);

        assert(op.l == v1);
        assert(op.r == v2);
    }

    @Test
    void testEvaluate1() {
        VariableBundle vars = new VariableBundle();
        vars.setValue("x", 3);

        BinaryOp op1 = new BinaryOp(new Value(3), BinaryOp.MUL, new Value(2));
        Value[] v1 = op1.evaluate(vars);
        assertEquals(1, v1.length);
        assertEquals(Value.TYPE_CONSTANT, v1[0].type);
        assertEquals(6, v1[0].coeff);

        BinaryOp op2 = new BinaryOp(new Value(6), BinaryOp.ADD, new Value(2, "x"));
        Value[] v2 = op2.evaluate(vars);
        assertEquals(1, v2.length);
        assertEquals(Value.TYPE_CONSTANT, v2[0].type);
        assertEquals(12, v2[0].coeff);

        BinaryOp op3 = new BinaryOp(new Value(op1), BinaryOp.MUL, new Value(op2));
        Value[] v3 = op3.evaluate(vars);
        assertEquals(1, v3.length);
        assertEquals(Value.TYPE_CONSTANT, v3[0].type);
        assertEquals(72, v3[0].coeff);
    }

    @Test
    void testToString() {
        BinaryOp op1 = new BinaryOp(new Value(3, "x"), BinaryOp.ADD, new Value(2));
        BinaryOp op2 = new BinaryOp(new Value(5), BinaryOp.POW, new Value(2));

        assert(op1.toString().equals("3.0x+2.0"));
        assert(op2.toString().equals("5.0^2.0"));

        BinaryOp op3 = new BinaryOp(new Value(op1), BinaryOp.MUL, new Value(op2));

        assert(op3.toString().equals("3.0x+2.0*5.0^2.0"));
    }
}
