package us.pinglesaur.algebraengine.functions;

import us.pinglesaur.algebraengine.equations.Value;

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
    public Value[] forwardExecute(Value[] input, double rangeMin, double rangeMax) {
        if (input.length != 1)
            return null;
        else if (input[0].type != Value.TYPE_CONSTANT || input[0].isImaginary)
            return null;

        double c = input[0].getConstant();

        Value[] v = new Value[1];
        v[0] = new Value();
        v[0].type = Value.TYPE_CONSTANT;
        v[0].isImaginary = (c < 0);
        v[0].isNegative = false;
        v[0].isCoeffInit = true;
        v[0].coeff = Math.sqrt(Math.abs(c));

        return v;
    }

    @Override
    public Value[] reverseExecute(Value[] input, double rangeMin, double rangeMax) {
        if (input.length != 1)
            return null;
        else if (input[0].type != Value.TYPE_CONSTANT)
            return null;

        double c = input[0].getConstant();

        Value[] v = new Value[1];
        v[0] = new Value();
        v[0].type = Value.TYPE_CONSTANT;
        v[0].isImaginary = false;
        v[0].isNegative = input[0].isImaginary;
        v[0].isCoeffInit = true;
        v[0].coeff = Math.pow(c, 2);

        return v;
    }
}
