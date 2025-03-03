import java.util.ArrayList;

public class Path2D {

    private ColorRGB color;
    private ArrayList<Double> x;
    private ArrayList<Double> y;

    public Path2D() {
        this.color = new ColorRGB(0, 1, 0);
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
    }

    public int size() {
        if (x.size() != y.size())
            return 0;

        return x.size();
    }

    public double getX(int n) {
        return x.get(n);
    }

    public double getY(int n) {
        return y.get(n);
    }

    @Override
    public String toString() {
        String tmp = "Path2D\n";
        tmp += "Size: " + size() + "\n";

        return tmp;
    }
}
