package us.pinglesaur.algebraengine.graphing;

import org.junit.jupiter.api.Test;

public class ColorRGBTest {

    @Test
    void testToString() {
        ColorRGB c1 = new ColorRGB(0.5f, 0.6f, 0.7f);
        assert(c1.toString().equals("ColorRGB: R=0.5; G=0.6; B=0.7"));

        ColorRGB c2 = new ColorRGB(0.1f, 0.2f, 0.3f);
        assert(c2.toString().equals("ColorRGB: R=0.1; G=0.2; B=0.3"));
    }
}
