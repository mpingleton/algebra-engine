package us.pinglesaur.algebraengine.functions;

public class Function {

    private int mode;

    public Function() {
        this.mode = FunctionSelector.RAD;
    }

    public Function(int mode) {
        this.mode = mode;
    }

    public String name() {
        return "";
    }

    public double execute(double x) {
        return Double.NaN;
    }
}
