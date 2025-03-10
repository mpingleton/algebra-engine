package us.pinglesaur.algebraengine.graphing;

import java.util.ArrayList;

public class Path3D {

    private ColorRGB color;
    private ArrayList<Double> x;
    private ArrayList<Double> y;
    private ArrayList<Double> z;

    public Path3D() {
        this.color = new ColorRGB(0, 1, 0);
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
        this.z = new ArrayList<>();
    }

    public int size() {
        if (x.size() != y.size())
            return 0;
        else if (y.size() != z.size())
            return 0;

        return x.size();
    }

    public void add(double inX, double inY, double inZ) {
        x.add(inX);
        y.add(inY);
        z.add(inZ);
    }

    public double getX(int n) {
        return x.get(n);
    }

    public double getY(int n) {
        return y.get(n);
    }

    public double getZ(int n) {
        return z.get(n);
    }

    @Override
    public String toString() {
        String tmp = "Path3D: ";

        tmp += "Size=" + size();

        return tmp;
    }
}
