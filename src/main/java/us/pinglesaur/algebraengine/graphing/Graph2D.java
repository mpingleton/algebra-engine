package us.pinglesaur.algebraengine.graphing;

import java.util.ArrayList;

public class Graph2D {

    private Area2D graphArea;
    private ArrayList<GraphEquation2D> equations;
    private ArrayList<Point2D> points;

    public Graph2D() {
        this.graphArea = new Area2D();
        this.equations = new ArrayList<>();
        this.points = new ArrayList<>();
    }
}
