package us.pinglesaur.algebraengine.graphing;

import us.pinglesaur.algebraengine.equations.Parser;
import us.pinglesaur.algebraengine.equations.Tokenizer;
import us.pinglesaur.algebraengine.equations.Value;

import java.util.ArrayList;

public class GraphEquation2D {

    public String label;
    public String equation;
    public ColorRGB color;
    private Area2D area;
    private Value value;
    private ArrayList<Path2D> graphPaths;

    public GraphEquation2D() {
        this.label = "";
        this.equation = "";
        this.color = new ColorRGB(0, 1, 0);
        this.area = new Area2D();
        this.value = null;
        this.graphPaths = new ArrayList<>();
    }

    public void setEquation(String inputEquation) {
        equation = inputEquation;

        Tokenizer tokenizer = new Tokenizer(equation);
        tokenizer.tokenize();

        Parser parser = new Parser(tokenizer);
        if (parser.parse() < 0)
            return;

        value = parser.output;
        graphPaths.clear();
    }

    public void setArea(Area2D inputArea) {
        area = inputArea;
    }

    public void calculateGraph() {
        graphPaths.clear();
        if (value == null)
            return;

        // TODO
    }

    public Path2D[] getPaths() {
        Path2D[] arr = new Path2D[graphPaths.size()];
        graphPaths.toArray(arr);

        return arr;
    }

    @Override
    public String toString() {
        String tmp = "GraphEquation2D: ";

        tmp += "Label=\"" + label;
        tmp += "\"; ";
        tmp += "Equation=\"" + equation;
        tmp += "\"; ";
        tmp += "Graph Color=(" + color.toString() + ")";

        return tmp;
    }
}
