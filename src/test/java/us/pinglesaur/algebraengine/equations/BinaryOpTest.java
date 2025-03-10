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
}
