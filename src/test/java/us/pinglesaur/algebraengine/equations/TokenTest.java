package us.pinglesaur.algebraengine.equations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTest {

    @Test
    void testParseNext() {
        String ss = "2.3x+32y(3+234)";

        Token t1 = new Token();
        int c1 = t1.parseNext(ss, 0);
        assertEquals(3, c1);
        assertEquals(0, t1.first);
        assertEquals(2, t1.last);
        assertEquals(Token.CONSTANT, t1.type);
        assertEquals("2.3", t1.str);

        Token t2 = new Token();
        int c2 = t2.parseNext(ss, 3);
        assertEquals(4, c2);
        assertEquals(3, t2.first);
        assertEquals(3, t2.last);
        assertEquals(Token.IDENTIFIER, t2.type);
        assertEquals("x", t2.str);

        Token t3 = new Token();
        int c3 = t3.parseNext(ss, 4);
        assertEquals(5, c3);
        assertEquals(4, t3.first);
        assertEquals(4, t3.last);
        assertEquals(Token.OPERATOR, t3.type);
        assertEquals("+", t3.str);

        Token t4 = new Token();
        int c4 = t4.parseNext(ss, 5);
        assertEquals(7, c4);
        assertEquals(5, t4.first);
        assertEquals(6, t4.last);
        assertEquals(Token.CONSTANT, t4.type);
        assertEquals("32", t4.str);

        Token t5 = new Token();
        int c5 = t5.parseNext(ss, 7);
        assertEquals(8, c5);
        assertEquals(7, t5.first);
        assertEquals(7, t5.last);
        assertEquals(Token.IDENTIFIER, t5.type);
        assertEquals("y", t5.str);

        Token t6 = new Token();
        int c6 = t6.parseNext(ss, 8);
        assertEquals(9, c6);
        assertEquals(8, t6.first);
        assertEquals(8, t6.last);
        assertEquals(Token.DELIMITER, t6.type);
        assertEquals("(", t6.str);

        Token t7 = new Token();
        int c7 = t7.parseNext(ss, 9);
        assertEquals(10, c7);
        assertEquals(9, t7.first);
        assertEquals(9, t7.last);
        assertEquals(Token.CONSTANT, t7.type);
        assertEquals("3", t7.str);

        Token t8 = new Token();
        int c8 = t8.parseNext(ss, 10);
        assertEquals(11, c8);
        assertEquals(10, t8.first);
        assertEquals(10, t8.last);
        assertEquals(Token.OPERATOR, t8.type);
        assertEquals("+", t8.str);

        Token t9 = new Token();
        int c9 = t9.parseNext(ss, 11);
        assertEquals(14, c9);
        assertEquals(11, t9.first);
        assertEquals(13, t9.last);
        assertEquals(Token.CONSTANT, t9.type);
        assertEquals("234", t9.str);

        Token t10 = new Token();
        int c10 = t10.parseNext(ss, 14);
        assertEquals(15, c10);
        assertEquals(14, t10.first);
        assertEquals(14, t10.last);
        assertEquals(Token.DELIMITER, t10.type);
        assertEquals(")", t10.str);
    }
}
