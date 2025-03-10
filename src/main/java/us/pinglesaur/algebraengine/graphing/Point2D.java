package us.pinglesaur.algebraengine.graphing;

public class Point2D {

    private ColorRGB color;
    public double x, y;

    public Point2D() {
        this.color = new ColorRGB(0.0f, 1.0f, 0.0f);
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point2D(double x, double y) {
        this.color = new ColorRGB(0.0f, 1.0f, 0.0f);
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
