package us.pinglesaur.algebraengine.functions;

import us.pinglesaur.algebraengine.equations.Value;

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

    public Value[] forwardExecute(Value[] input, double rangeMin, double rangeMax) {
        return null;
    }

    public Value[] reverseExecute(Value[] input, double rangeMin, double rangeMax) {
        return null;
    }
}
