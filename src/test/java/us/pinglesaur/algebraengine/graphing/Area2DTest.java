package us.pinglesaur.algebraengine.graphing;

import org.junit.jupiter.api.Test;

public class Area2DTest {

    @Test
    void testToString() {
        Area2D a1 = new Area2D(-10, 10, -20, 20);
        assert(a1.toString().equals("Area2D: X=(-10.0-10.0); Y=(-20.0-20.0)"));

        Area2D a2 = new Area2D(-5, 8, -3, 8);
        assert(a2.toString().equals("Area2D: X=(-5.0-8.0); Y=(-3.0-8.0)"));
    }
}
