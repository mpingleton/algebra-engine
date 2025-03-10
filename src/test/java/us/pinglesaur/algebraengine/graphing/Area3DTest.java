package us.pinglesaur.algebraengine.graphing;

import org.junit.jupiter.api.Test;

public class Area3DTest {

    @Test
    void testToString() {
        Area3D a1 = new Area3D(-10, 10, -20, 20, -8, 8);
        assert(a1.toString().equals("Area3D: X=(-10.0-10.0); Y=(-20.0-20.0); Z=(-8.0-8.0)"));

        Area3D a2 = new Area3D(-5, 8, -3, 8, -7, 7);
        assert(a2.toString().equals("Area3D: X=(-5.0-8.0); Y=(-3.0-8.0); Z=(-7.0-7.0)"));
    }
}
