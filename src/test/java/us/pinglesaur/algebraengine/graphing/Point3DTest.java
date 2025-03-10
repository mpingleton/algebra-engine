package us.pinglesaur.algebraengine.graphing;

import org.junit.jupiter.api.Test;

public class Point3DTest {

    @Test
    void testToString() {
        Point3D p1 = new Point3D(3, 5, 1);
        assert(p1.toString().equals("(3.0, 5.0, 1.0)"));
    }
}
