public class Area2D {

    public double xMin, xMax;
    public double yMin, yMax;

    public Area2D() {
        this.xMin = 0.0;
        this.xMax = 0.0;
        this.yMin = 0.0;
        this.yMax = 0.0;
    }

    @Override
    public String toString() {
        String tmp = "Area2D:\n";

        tmp += "X: (" + xMin + "-" + xMax + ")\n";
        tmp += "Y: (" + yMin + "-" + yMax + ")\n";

        return tmp;
    }
}
