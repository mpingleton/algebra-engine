package us.pinglesaur.algebraengine.equations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenizerTest {

    @Test
    void testTokenize1() {
        Tokenizer t = new Tokenizer("y=sin(x)");
        assertEquals(8, t.tokenize());
        assertEquals(6, t.numberTokens());

        Token t1 = t.getToken(0);
        assertEquals(Token.IDENTIFIER, t1.type);
        assertEquals("y", t1.str);
        assertEquals(0, t1.first);
        assertEquals(0, t1.last);

        Token t2 = t.getToken(1);
        assertEquals(Token.OPERATOR, t2.type);
        assertEquals("=", t2.str);
        assertEquals(1, t2.first);
        assertEquals(1, t2.last);

        Token t3 = t.getToken(2);
        assertEquals(Token.IDENTIFIER, t3.type);
        assertEquals("sin", t3.str);
        assertEquals(2, t3.first);
        assertEquals(4, t3.last);

        Token t4 = t.getToken(3);
        assertEquals(Token.DELIMITER, t4.type);
        assertEquals("(", t4.str);
        assertEquals(5, t4.first);
        assertEquals(5, t4.last);

        Token t5 = t.getToken(4);
        assertEquals(Token.IDENTIFIER, t5.type);
        assertEquals("x", t5.str);
        assertEquals(6, t5.first);
        assertEquals(6, t5.last);

        Token t6 = t.getToken(5);
        assertEquals(Token.DELIMITER, t6.type);
        assertEquals(")", t6.str);
        assertEquals(7, t6.first);
        assertEquals(7, t6.last);
    }
}
