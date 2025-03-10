package us.pinglesaur.algebraengine.graphing;

import org.junit.jupiter.api.Test;

public class Point2DTest {

    @Test
    void testToString() {
        Point2D p1 = new Point2D(3, 5);
        assert(p1.toString().equals("(3.0, 5.0)"));
    }
}
