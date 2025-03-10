package us.pinglesaur.algebraengine.graphing;

public class ColorRGB {

    private float r, g, b;

    public ColorRGB() {
        this.r = 0.0f;
        this.g = 0.0f;
        this.b = 0.0f;
    }

    public ColorRGB(float red, float green, float blue) {
        this.r = red;
        this.g = green;
        this.b = blue;
    }

    @Override
    public String toString() {
        String tmp = "ColorRGB: ";

        tmp += "R=" + r;
        tmp += "; ";
        tmp += "G=" + g;
        tmp += "; ";
        tmp += "B=" + b;

        return tmp;
    }
}
