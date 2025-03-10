package us.pinglesaur.algebraengine.graphing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Path2DTest {

    @Test
    void testGeneral() {
        Path2D p1 = new Path2D();
        assertEquals(0, p1.size());

        p1.add(0, 0);
        assertEquals(1, p1.size());
        assertEquals(0, p1.getX(0));
        assertEquals(0, p1.getY(0));

        p1.add(1, 0.5);
        assertEquals(2, p1.size());
        assertEquals(0, p1.getX(0));
        assertEquals(0, p1.getY(0));
        assertEquals(1, p1.getX(1));
        assertEquals(0.5, p1.getY(1));

        p1.add(2, 3);
        assertEquals(3, p1.size());
        assertEquals(0, p1.getX(0));
        assertEquals(0, p1.getY(0));
        assertEquals(1, p1.getX(1));
        assertEquals(0.5, p1.getY(1));
        assertEquals(2, p1.getX(2));
        assertEquals(3, p1.getY(2));
    }

    @Test
    void testToString() {
        Path2D p1 = new Path2D();
        assert(p1.toString().equals("Path2D: Size=0"));
    }
}
