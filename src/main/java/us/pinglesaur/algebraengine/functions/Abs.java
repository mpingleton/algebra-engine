package us.pinglesaur.algebraengine.functions;

import us.pinglesaur.algebraengine.equations.Value;

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
    public Value[] forwardExecute(Value[] input, double rangeMin, double rangeMax) {
        if (input.length != 1)
            return null;
        else if (input[0].type != Value.TYPE_CONSTANT)
            return null;

        Value[] v = new Value[1];
        v[0] = new Value();
        v[0].type = Value.TYPE_CONSTANT;
        v[0].isImaginary = input[0].isImaginary;
        v[0].isNegative = false;
        v[0].isCoeffInit = true;
        v[0].coeff = Math.abs(input[0].getConstant());

        return v;
    }

    @Override
    public Value[] reverseExecute(Value[] input, double rangeMin, double rangeMax) {
        if (input.length != 1)
            return null;
        else if (input[0].type != Value.TYPE_CONSTANT)
            return null;

        double c = input[0].getConstant();
        if (c > 0) {
            Value[] v = new Value[2];

            v[0] = new Value();
            v[0].type = Value.TYPE_CONSTANT;
            v[0].isNegative = false;
            v[0].isImaginary = input[0].isImaginary;
            v[0].isCoeffInit = true;
            v[0].coeff = c;

            v[1] = new Value();
            v[1].type = Value.TYPE_CONSTANT;
            v[1].isNegative = true;
            v[1].isImaginary = input[0].isImaginary;
            v[1].isCoeffInit = true;
            v[1].coeff = c;

            return v;
        }

        return new Value[0];
    }
}
