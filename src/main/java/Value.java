public class Value {

    public boolean isCoeffInit;
    public double coeff;
    public String name;
    public BinaryOp biOp;

    public Value() {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = "";
        this.biOp = null;
    }

    public Value(double coeff) {
        this.isCoeffInit = true;
        this.coeff = coeff;
        this.name = "";
        this.biOp = null;
    }

    public Value(String name) {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = name;
        this.biOp = null;
    }

    public Value(double coeff, String name) {
        this.isCoeffInit = true;
        this.coeff = coeff;
        this.name = name;
        this.biOp = null;
    }

    public Value(BinaryOp biOp) {
        this.isCoeffInit = false;
        this.coeff = Double.NaN;
        this.name = "";
        this.biOp = biOp;
    }

    public void swap(Value other) {
        boolean tmpInit = isCoeffInit;
        double tmpCoeff = coeff;
        String tmpName = name;
        BinaryOp tmpBiOp = biOp;

        isCoeffInit = other.isCoeffInit;
        coeff = other.coeff;
        name = other.name;
        biOp = other.biOp;

        other.isCoeffInit = tmpInit;
        other.coeff = tmpCoeff;
        other.name = tmpName;
        other.biOp = tmpBiOp;
    }

    public double toValue(VariableBundle vars) {
        double val = 1.0;
        if (biOp != null)
            val *= biOp.evaluate(vars);

        if (!name.isEmpty()) {
            double v = vars.getValue(name);
            if (Double.isNaN(v))
                return Double.NaN;

            val *= v;
        }

        if (isCoeffInit)
            val *= coeff;

        return val;
    }

    @Override
    public String toString() {
        String s = "";

        if (isCoeffInit) {
            s += Double.toString(coeff);
        }

        if (!name.isEmpty()) {
            s += name;
        }

        if (biOp != null) {
            s += biOp;
        }

        return s;
    }
}

