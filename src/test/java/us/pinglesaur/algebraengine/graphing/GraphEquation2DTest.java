package us.pinglesaur.algebraengine.graphing;

import org.junit.jupiter.api.Test;
import us.pinglesaur.algebraengine.equations.Value;

public class GraphEquation2DTest {

    @Test
    void testGeneral() {
        GraphEquation2D e1 = new GraphEquation2D();
        e1.setArea(new Area2D(-5, 5, -5, 5));
        assert(e1.label.isEmpty());
        assert(e1.equation.isEmpty());
        assert(e1.getPaths().length == 0);
        assert(e1.toString().equals("GraphEquation2D: Label=\"\"; Equation=\"\"; Graph Color=(ColorRGB: R=0.0; G=1.0; B=0.0)"));

        e1.setEquation("y=abs(x+3)");
        e1.calculateGraph();
        Path2D[] p1 = e1.getPaths();
        assert(e1.label.isEmpty());
        assert(e1.equation.equals("y=abs(x+3)"));
        assert(p1.length == 1);
        assert(e1.toString().equals("GraphEquation2D: Label=\"\"; Equation=\"y=sin(x+3)\"; Graph Color=(ColorRGB: R=0.0; G=1.0; B=0.0)"));

        // TODO: Test the graph paths.
    }
}
