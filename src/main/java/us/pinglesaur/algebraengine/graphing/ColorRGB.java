package us.pinglesaur.algebraengine.graphing;

public class ColorRGB {

    private float r, g, b;

    public ColorRGB(float red, float green, float blue) {
        this.r = red;
        this.g = green;
        this.b = blue;
    }

    @Override
    public String toString() {
        String tmp = "ColorRGB\n";

        tmp += "R: " + r + "\n";
        tmp += "G: " + g + "\n";
        tmp += "B: " + b + "\n";

        return tmp;
    }
}
