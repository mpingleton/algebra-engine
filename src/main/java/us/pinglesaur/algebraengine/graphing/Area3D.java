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

    public Area3D(double left, double right, double bottom, double top, double front, double back) {
        this.xMin = left;
        this.xMax = right;
        this.yMin = bottom;
        this.yMax = top;
        this.zMin = front;
        this.zMax = back;
    }

    @Override
    public String toString() {
        String tmp = "Area3D: ";

        tmp += "X=(" + xMin + "-" + xMax + ")";
        tmp += "; ";
        tmp += "Y=(" + yMin + "-" + yMax + ")";
        tmp += "; ";
        tmp += "Z=(" + zMin + "-" + zMax + ")";

        return tmp;
    }
}
