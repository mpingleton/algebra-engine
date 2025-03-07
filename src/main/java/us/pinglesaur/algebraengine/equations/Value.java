package us.pinglesaur.algebraengine.equations;

import us.pinglesaur.algebraengine.functions.Function;
import us.pinglesaur.algebraengine.functions.FunctionSelector;

import java.util.ArrayList;
import java.util.Arrays;

public class Value {

    public static final int TYPE_NULL = 0;
    public static final int TYPE_CONSTANT = 1;
    public static final int TYPE_VARIABLE = 2;
    public static final int TYPE_FUNCTION = 3;
    public static final int TYPE_BINARY_OP = 4;

    public int type;
    public boolean isImaginary;
    public boolean isNegative;
    public boolean isCoeffInit;
    public double coeff;
    public String name;
    public Value[] func;
    public BinaryOp biOp;
    public Function funcExec;

    public Value() {
        this.type = TYPE_NULL;
        this.isImaginary = false;
        this.isNegative = false;
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = "";
        this.func = null;
        this.biOp = null;
        this.funcExec = null;
    }

    public Value(double coeff) {
        this.type = TYPE_CONSTANT;
        this.isImaginary = false;
        this.isNegative = false;
        this.isCoeffInit = true;
        this.coeff = coeff;
        this.name = "";
        this.func = null;
        this.biOp = null;
        this.funcExec = null;
    }

    public Value(String name) {
        this.type = TYPE_VARIABLE;
        this.isImaginary = false;
        this.isNegative = false;
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = name;
        this.func = null;
        this.biOp = null;
        this.funcExec = null;
    }

    public Value(double coeff, String name) {
        this.type = TYPE_VARIABLE;
        this.isImaginary = false;
        this.isNegative = false;
        this.isCoeffInit = true;
        this.coeff = coeff;
        this.name = name;
        this.func = null;
        this.biOp = null;
        this.funcExec = null;
    }

    public Value(BinaryOp biOp) {
        this.type = TYPE_BINARY_OP;
        this.isImaginary = false;
        this.isNegative = false;
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = "";
        this.func = null;
        this.biOp = biOp;
        this.funcExec = null;
    }

    public void swap(Value other) {
        int tmpType = type;
        boolean tmpI = isImaginary;
        boolean tmpNegative = isNegative;
        boolean tmpInit = isCoeffInit;
        double tmpCoeff = coeff;
        String tmpName = name;
        BinaryOp tmpBiOp = biOp;
        Value[] tmpFunc = func;
        Function tmpFuncExec = funcExec;

        type = other.type;
        isImaginary = other.isImaginary;
        isNegative = other.isNegative;
        isCoeffInit = other.isCoeffInit;
        coeff = other.coeff;
        name = other.name;
        biOp = other.biOp;
        func = other.func;
        funcExec = other.funcExec;

        other.type = tmpType;
        other.isImaginary = tmpI;
        other.isNegative = tmpNegative;
        other.isCoeffInit = tmpInit;
        other.coeff = tmpCoeff;
        other.name = tmpName;
        other.biOp = tmpBiOp;
        other.func = tmpFunc;
        other.funcExec = tmpFuncExec;
    }

    public Value[] calculate(VariableBundle vars) {

        switch (type) {
            case TYPE_NULL: {
                return null;
            }
            case TYPE_CONSTANT: {
                Value[] r = new Value[1];
                r[0] = new Value();
                r[0].type = TYPE_CONSTANT;
                r[0].isImaginary = this.isImaginary;
                r[0].isCoeffInit = this.isCoeffInit;
                r[0].coeff = this.coeff;

                return r;
            }
            case TYPE_VARIABLE: {
                Value[] r = new Value[1];
                r[0] = new Value();

                double f = vars.getValue(name);
                if (Double.isNaN(f)) {
                    r[0].type = Value.TYPE_VARIABLE;
                    r[0].isCoeffInit = isCoeffInit;
                    r[0].isImaginary = isImaginary;
                    r[0].coeff = coeff;
                    r[0].name = name;
                } else {
                    r[0].type = Value.TYPE_CONSTANT;
                    r[0].isCoeffInit = true;
                    r[0].isImaginary = isImaginary;

                    if (isCoeffInit)
                        r[0].coeff = (coeff * f);
                    else
                        r[0].coeff = f;
                }

                return r;
            }
            case TYPE_FUNCTION: {
                if (funcExec == null)
                    return null;

                return funcExec.forwardExecute(func, Double.NaN, Double.NaN);
            }
            case TYPE_BINARY_OP: {
                if (biOp == null)
                    return null;

                return biOp.evaluate(vars);
            }
            default: return null;
        }
    }

    public double getConstant() {
        if (type != TYPE_CONSTANT || !isCoeffInit)
            return Double.NaN;

        if (isNegative)
            return coeff * -1;

        return coeff;
    }

    @Override
    public String toString() {
        switch (type) {
            case Value.TYPE_NULL: {
                return "";
            }
            case Value.TYPE_CONSTANT: {
                String r = "";

                if (isNegative)
                    r += "-";

                if (isCoeffInit)
                    r += Double.toString(coeff);

                if (isImaginary)
                    r += "i";

                return r;
            }
            case Value.TYPE_VARIABLE: {
                String r = "";

                if (isNegative)
                    r += "-";

                if (isCoeffInit)
                    r += Double.toString(coeff);

                if (isImaginary)
                    r += "i";

                r += name;

                return r;
            }
            case Value.TYPE_BINARY_OP: {
                return biOp.toString();
            }
            case Value.TYPE_FUNCTION: {
                String r = "";

                if (isNegative)
                    r += "-";

                if (isCoeffInit)
                    r += Double.toString(coeff);

                if (isImaginary)
                    r += "i";

                r += name;
                r += "(";

                for (int i = 0; i < func.length; i++) {
                    r += func[i].toString();

                    if (i < func.length - 1) {
                        r += ",";
                    }
                }

                r += ")";

                return r;
            }
            default: return "";
        }
    }
}

