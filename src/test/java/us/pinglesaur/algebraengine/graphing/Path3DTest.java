package us.pinglesaur.algebraengine.graphing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Path3DTest {

    @Test
    void testGeneral() {
        Path3D p1 = new Path3D();
        assertEquals(0, p1.size());

        p1.add(0, 0, 0);
        assertEquals(1, p1.size());
        assertEquals(0, p1.getX(0));
        assertEquals(0, p1.getY(0));
        assertEquals(0, p1.getZ(0));

        p1.add(1, 0.5, -1);
        assertEquals(2, p1.size());
        assertEquals(0, p1.getX(0));
        assertEquals(0, p1.getY(0));
        assertEquals(0, p1.getZ(0));
        assertEquals(1, p1.getX(1));
        assertEquals(0.5, p1.getY(1));
        assertEquals(-1, p1.getZ(1));

        p1.add(2, 3, -2);
        assertEquals(3, p1.size());
        assertEquals(0, p1.getX(0));
        assertEquals(0, p1.getY(0));
        assertEquals(0, p1.getZ(0));
        assertEquals(1, p1.getX(1));
        assertEquals(0.5, p1.getY(1));
        assertEquals(-1, p1.getZ(1));
        assertEquals(2, p1.getX(2));
        assertEquals(3, p1.getY(2));
        assertEquals(-2, p1.getZ(2));
    }

    @Test
    void testToString() {
        Path3D p1 = new Path3D();
        assert(p1.toString().equals("Path3D: Size=0"));
    }
}
