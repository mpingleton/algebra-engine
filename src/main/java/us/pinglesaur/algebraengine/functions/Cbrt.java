package us.pinglesaur.algebraengine.functions;

public class Cbrt extends Function {

    public Cbrt() {
        super();
    }

    public Cbrt(int mode) {
        super(mode);
    }

    @Override
    public String name() {
        return "cbrt";
    }

    @Override
    public double execute(double x) {
        return Math.cbrt(x);
    }
}
