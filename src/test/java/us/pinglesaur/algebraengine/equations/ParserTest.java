package us.pinglesaur.algebraengine.equations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void testNextValue() {
        Tokenizer t1 = new Tokenizer("3");
        t1.tokenize();
        Parser p1 = new Parser(t1);
        Value v1 = new Value();
        p1.nextValue(v1);
        assertEquals(Value.TYPE_CONSTANT, v1.type);
        assertEquals(3, v1.coeff);
        assert(v1.name.isEmpty());

        Tokenizer t2 = new Tokenizer("2x");
        t2.tokenize();
        Parser p2 = new Parser(t2);
        Value v2 = new Value();
        p2.nextValue(v2);
        assertEquals(Value.TYPE_VARIABLE, v2.type);
        assertEquals(2, v2.coeff);
        assert(v2.name.equals("x"));
    }

    @Test
    void testParseRec1() {
        Tokenizer t = new Tokenizer("2");
        t.tokenize();

        Parser p = new Parser(t);
        Value v = new Value();
        p.parseRec(v);

        assertEquals(Value.TYPE_CONSTANT, v.type);
    }

    @Test
    void testParseRec2() {
        Tokenizer t = new Tokenizer("2x+3");
        t.tokenize();

        Parser p = new Parser(t);
        Value v = new Value();
        p.parseRec(v);

        assertEquals(Value.TYPE_BINARY_OP, v.type);
        assert(v.biOp.r != null);
        assert(v.biOp.l != null);
    }

    @Test
    void testParseRec3() {
        Tokenizer t = new Tokenizer("2*3x+4");
        t.tokenize();

        Parser p = new Parser(t);
        Value v = new Value();
        p.parseRec(v);

        assertEquals(Value.TYPE_BINARY_OP, v.type);
        assert(v.biOp.l != null);
        assert(v.biOp.r != null);
        assertEquals(Value.TYPE_BINARY_OP, v.biOp.l.type);
        assertEquals(Value.TYPE_CONSTANT, v.biOp.l.biOp.l.type);
        assertEquals(2, v.biOp.l.biOp.l.coeff);
        assertEquals(Value.TYPE_VARIABLE, v.biOp.l.biOp.r.type);
        assertEquals(3, v.biOp.l.biOp.r.coeff);
        assertEquals(Value.TYPE_CONSTANT, v.biOp.r.type);
        assertEquals(4, v.biOp.r.coeff);
    }

    @Test
    void testParseRec4() {
        Tokenizer t = new Tokenizer("2+3x*4");
        t.tokenize();

        Parser p = new Parser(t);
        Value v = new Value();
        p.parseRec(v);

        assertEquals(Value.TYPE_BINARY_OP, v.type);
        assert(v.biOp.l != null);
        assert(v.biOp.r != null);
        assertEquals(Value.TYPE_CONSTANT, v.biOp.l.type);
        assertEquals(Value.TYPE_BINARY_OP, v.biOp.r.type);
    }

    @Test
    void testParseRec5() {
        Tokenizer t = new Tokenizer("2+3*4+5");
        t.tokenize();

        Parser p = new Parser(t);
        Value v = new Value();
        p.parseRec(v);

        assertEquals(Value.TYPE_BINARY_OP, v.type);
        assertEquals(Value.TYPE_BINARY_OP, v.biOp.l.type);
        assertEquals(Value.TYPE_CONSTANT, v.biOp.r.type);
        assertEquals(5, v.biOp.r.coeff);

        assertEquals(Value.TYPE_CONSTANT, v.biOp.l.biOp.l.type);
        assertEquals(Value.TYPE_BINARY_OP, v.biOp.l.biOp.r.type);
        assertEquals(2, v.biOp.l.biOp.l.coeff);


        assertEquals(Value.TYPE_CONSTANT, v.biOp.l.biOp.r.biOp.l.type);
        assertEquals(Value.TYPE_CONSTANT, v.biOp.l.biOp.r.biOp.r.type);
        assertEquals(3, v.biOp.l.biOp.r.biOp.l.coeff);
        assertEquals(4, v.biOp.l.biOp.r.biOp.r.coeff);
    }

    @Test
    void testParseRec6() {
        Tokenizer t4 = new Tokenizer("2+3*4+5");
        t4.tokenize();

        Parser p4 = new Parser(t4);
        Value v4 = new Value();
        p4.parseRec(v4);

        assertEquals(Value.TYPE_BINARY_OP, v4.type);
        assertEquals(Value.TYPE_BINARY_OP, v4.biOp.l.type);
        assertEquals(Value.TYPE_CONSTANT, v4.biOp.l.biOp.l.type);
        assertEquals(2, v4.biOp.l.biOp.l.coeff);
        assertEquals(Value.TYPE_BINARY_OP, v4.biOp.l.biOp.r.type);
        assertEquals(Value.TYPE_CONSTANT, v4.biOp.l.biOp.r.biOp.l.type);
        assertEquals(3, v4.biOp.l.biOp.r.biOp.l.coeff);
        assertEquals(Value.TYPE_CONSTANT, v4.biOp.l.biOp.r.biOp.r.type);
        assertEquals(4, v4.biOp.l.biOp.r.biOp.r.coeff);
        assertEquals(Value.TYPE_CONSTANT, v4.biOp.r.type);
        assertEquals(5, v4.biOp.r.coeff);
    }

    @Test
    void testParseRec7() {
        Tokenizer t5 = new Tokenizer("2*3+4*5");
        t5.tokenize();

        Parser p5 = new Parser(t5);
        Value v5 = new Value();
        p5.parseRec(v5);

        assertEquals(Value.TYPE_BINARY_OP, v5.type);
        assertEquals(Value.TYPE_BINARY_OP, v5.biOp.l.type);
        assertEquals(Value.TYPE_CONSTANT, v5.biOp.l.biOp.l.type);
        assertEquals(2, v5.biOp.l.biOp.l.coeff);
        assertEquals(Value.TYPE_CONSTANT, v5.biOp.l.biOp.r.type);
        assertEquals(3, v5.biOp.l.biOp.r.coeff);
        assertEquals(Value.TYPE_BINARY_OP, v5.biOp.r.type);
        assertEquals(Value.TYPE_CONSTANT, v5.biOp.r.biOp.l.type);
        assertEquals(4, v5.biOp.r.biOp.l.coeff);
        assertEquals(Value.TYPE_CONSTANT, v5.biOp.r.biOp.r.type);
        assertEquals(5, v5.biOp.r.biOp.r.coeff);
    }

    @Test
    void testParseRec8() {
        Tokenizer t6 = new Tokenizer("abs(2x)");
        t6.tokenize();

        Parser p6 = new Parser(t6);
        Value v6 = new Value();
        p6.parseRec(v6);

        assertEquals(Value.TYPE_FUNCTION, v6.type);
        assert(v6.func != null);
        assertEquals(1, v6.func.length);
        assertEquals(Value.TYPE_VARIABLE, v6.func[0].type);
        assertEquals(2, v6.func[0].coeff);
        assert(v6.func[0].name.equals("x"));
    }

    @Test
    void testParseRec9() {
        Tokenizer t7 = new Tokenizer("abs(2x)+2");
        t7.tokenize();

        Parser p7 = new Parser(t7);
        Value v7 = new Value();
        p7.parseRec(v7);

        assertEquals(Value.TYPE_BINARY_OP, v7.type);
        assertEquals(Value.TYPE_FUNCTION, v7.biOp.l.type);
        assert(v7.biOp.l.name.equals("abs"));
        assertEquals(1, v7.biOp.l.func.length);
        assertEquals(Value.TYPE_VARIABLE, v7.biOp.l.func[0].type);
        assertEquals(2, v7.biOp.l.func[0].coeff);
        assert(v7.biOp.l.func[0].name.equals("x"));
        assertEquals(Value.TYPE_CONSTANT, v7.biOp.r.type);
        assertEquals(2, v7.biOp.r.coeff);
    }

    @Test
    void testParser1() {
        Tokenizer t = new Tokenizer("y=sin(2x)");
        t.tokenize();
        Parser p = new Parser(t);
        int r = p.parse();
        assertEquals(7, r);
    }

    @Test
    void testParser2() {
        Tokenizer t = new Tokenizer("2x=sin(y)");
        t.tokenize();
        Parser p = new Parser(t);
        int r = p.parse();
        assertEquals(7, r);
    }
}
