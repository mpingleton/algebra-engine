package us.pinglesaur.algebraengine.functions;

public class Sqrt extends Function {

    public Sqrt() {
        super();
    }

    public Sqrt(int mode) {
        super(mode);
    }

    @Override
    public String name() {
        return "sqrt";
    }

    @Override
    public double execute(double x) {
        return Math.sqrt(x);
    }
}
