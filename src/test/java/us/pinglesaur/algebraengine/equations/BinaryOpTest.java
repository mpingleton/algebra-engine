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
}
