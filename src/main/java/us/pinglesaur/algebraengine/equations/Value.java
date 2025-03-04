package us.pinglesaur.algebraengine.equations;

import us.pinglesaur.algebraengine.functions.Function;
import us.pinglesaur.algebraengine.functions.FunctionSelector;

public class Value {

    public boolean isCoeffInit;
    public double coeff;
    public String name;
    public Value func;
    public BinaryOp biOp;
    public Function funcExec;

    public Value() {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = "";
        this.func = null;
        this.biOp = null;
        this.funcExec = null;
    }

    public Value(double coeff) {
        this.isCoeffInit = true;
        this.coeff = coeff;
        this.name = "";
        this.func = null;
        this.biOp = null;
        this.funcExec = null;
    }

    public Value(String name) {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = name;
        this.func = null;
        this.biOp = null;
        this.funcExec = null;
    }

    public Value(double coeff, String name) {
        this.isCoeffInit = true;
        this.coeff = coeff;
        this.name = name;
        this.func = null;
        this.biOp = null;
        this.funcExec = null;
    }

    public Value(BinaryOp biOp) {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = "";
        this.func = null;
        this.biOp = biOp;
        this.funcExec = null;
    }

    public void swap(Value other) {
        boolean tmpInit = isCoeffInit;
        double tmpCoeff = coeff;
        String tmpName = name;
        BinaryOp tmpBiOp = biOp;
        Value tmpFunc = func;
        Function tmpFuncExec = funcExec;

        isCoeffInit = other.isCoeffInit;
        coeff = other.coeff;
        name = other.name;
        biOp = other.biOp;
        func = other.func;
        funcExec = other.funcExec;


        other.isCoeffInit = tmpInit;
        other.coeff = tmpCoeff;
        other.name = tmpName;
        other.biOp = tmpBiOp;
        other.func = tmpFunc;
        other.funcExec = tmpFuncExec;
    }

    public double toValue(VariableBundle vars) {
        if (biOp != null) {
            return biOp.evaluate(vars);
        }

        double val = 1.0;
        if (!name.isEmpty()) {
            FunctionSelector fs = new FunctionSelector();
            funcExec = fs.getFunction(FunctionSelector.RAD, name);
            if (funcExec == null) {
                double v = vars.getValue(name);
                if (!Double.isNaN(v)) // TODO: Need a way to figure out for sure whether or not a variable exists.
                    val *= v;
            }
        }

        if (func != null) {
            if (funcExec != null)
                val *= funcExec.execute(func.toValue(vars));
            else
                val *= func.toValue(vars);
        }

        if (isCoeffInit)
            val *= coeff;

        return val;
    }

    @Override
    public String toString() {
        if (biOp != null) {
            return biOp.toString();
        }

        String s = "";
        if (isCoeffInit)
            s += Double.toString(coeff);

        if (!name.isEmpty()) {
            s += name;
        }

        if (func != null) {
            s += "(" + func + ")";
        }

        return s;
    }
}

