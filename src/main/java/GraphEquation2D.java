import java.util.ArrayList;

public class GraphEquation2D {

    private String label;
    private String equation;
    private ColorRGB color;
    private Area2D area;
    private BinaryOp op;
    private ArrayList<Path2D> graphPaths;

    public GraphEquation2D() {
        this.label = "";
        this.equation = "";
        this.color = new ColorRGB(0, 1, 0);
        this.area = new Area2D();
        this.op = new BinaryOp();
        this.graphPaths = new ArrayList<>();
    }

    // TODO: Parse the equation.

    // TODO: Run the equation to create the paths.

    @Override
    public String toString() {
        String tmp = "GraphEquation2D\n";

        tmp += "Label: " + label + "\n";
        tmp += "Equation: " + equation + "\n";
        tmp += color.toString();
        tmp += area.toString();
        tmp += "Parsed op: " + op.toString();
        tmp += "Number of paths: " + graphPaths.size() + "\n";

        return tmp;
    }
}
