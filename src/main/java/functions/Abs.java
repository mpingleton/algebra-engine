package functions;

public class Abs extends Function {

    public Abs() {
        super();
    }

    public Abs(int mode) {
        super(mode);
    }

    @Override
    public String name() {
        return "abs";
    }

    @Override
    public double execute(double x) {
        return Math.abs(x);
    }
}
