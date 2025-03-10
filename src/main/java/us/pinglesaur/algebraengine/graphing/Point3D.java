package us.pinglesaur.algebraengine.graphing;

public class Point3D {

    private ColorRGB color;
    public double x, y, z;

    public Point3D() {
        this.color = new ColorRGB(0.0f, 1.0f, 0.0f);
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Point3D(double x, double y, double z) {
        this.color = new ColorRGB(0.0f, 1.0f, 0.0f);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
