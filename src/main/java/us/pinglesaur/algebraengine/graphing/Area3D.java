package us.pinglesaur.algebraengine.graphing;

public class Area3D {

    public double xMin, xMax;
    public double yMin, yMax;
    public double zMin, zMax;

    public Area3D() {
        this.xMin = 0.0;
        this.xMax = 0.0;
        this.yMin = 0.0;
        this.yMax = 0.0;
        this.zMin = 0.0;
        this.zMax = 0.0;
    }

    @Override
    public String toString() {
        String tmp = "Area3D:\n";

        tmp += "X: (" + xMin + "-" + xMax + ")\n";
        tmp += "Y: (" + yMin + "-" + yMax + ")\n";
        tmp += "Z: (" + zMin + "-" + zMax + ")\n";

        return tmp;
    }
}
