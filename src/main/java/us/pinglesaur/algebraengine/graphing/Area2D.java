package us.pinglesaur.algebraengine.graphing;

public class Area2D {

    public double xMin, xMax;
    public double yMin, yMax;

    public Area2D() {
        this.xMin = 0.0;
        this.xMax = 0.0;
        this.yMin = 0.0;
        this.yMax = 0.0;
    }

    public Area2D(double left, double right, double bottom, double top) {
        this.xMin = left;
        this.xMax = right;
        this.yMin = bottom;
        this.yMax = top;
    }

    @Override
    public String toString() {
        String tmp = "Area2D: ";

        tmp += "X=(" + xMin + "-" + xMax + ")";
        tmp += "; ";
        tmp += "Y=(" + yMin + "-" + yMax + ")";

        return tmp;
    }
}
