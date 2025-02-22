public class Value {

    public boolean isCoeffInit;
    public double coeff;
    public String name;
    public Value func;
    public BinaryOp biOp;

    public Value() {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = "";
        this.func = null;
        this.biOp = null;
    }

    public Value(double coeff) {
        this.isCoeffInit = true;
        this.coeff = coeff;
        this.name = "";
        this.func = null;
        this.biOp = null;
    }

    public Value(String name) {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = name;
        this.func = null;
        this.biOp = null;
    }

    public Value(double coeff, String name) {
        this.isCoeffInit = true;
        this.coeff = coeff;
        this.name = name;
        this.func = null;
        this.biOp = null;
    }

    public Value(BinaryOp biOp) {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = "";
        this.func = null;
        this.biOp = biOp;
    }

    public void swap(Value other) {
        boolean tmpInit = isCoeffInit;
        double tmpCoeff = coeff;
        String tmpName = name;
        BinaryOp tmpBiOp = biOp;
        Value tmpFunc = func;

        isCoeffInit = other.isCoeffInit;
        coeff = other.coeff;
        name = other.name;
        biOp = other.biOp;
        tmpFunc = other.func;

        other.isCoeffInit = tmpInit;
        other.coeff = tmpCoeff;
        other.name = tmpName;
        other.biOp = tmpBiOp;
        other.func = tmpFunc;
    }

    public double toValue(VariableBundle vars) {
        if (biOp != null) {
            return biOp.evaluate(vars);
        }

        double val = 1.0;
        if (!name.isEmpty()) {
            double v = vars.getValue(name);
            if (!Double.isNaN(v))
                val *= v;
        }
        // TODO: Figure out if this.name is a variable or a function name.
        if (func != null)
            val *= func.toValue(vars);

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

