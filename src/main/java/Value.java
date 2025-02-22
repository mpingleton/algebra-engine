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

    public double toValue(VariableBundle vars) {
        if (biOp == null) {
            double val = 0.0;
            if (!name.isEmpty()) {
                val = vars.getValue(name);
                if (Double.isNaN(val)) {
                    return Double.NaN;
                }
                // TODO: How to deal with functions.
            }

            if (isCoeffInit) {
                val *= coeff;
            }

            return val;
        } else {
            return biOp.evaluate(); // TODO: Figure out how to pass the variable bundle here.
        }
    }

    @Override
    public String toString() {
        if (biOp == null) {
            String s = "";

            if (isCoeffInit) {
                s += Double.toString(coeff);
            }

            s += name;
            return s;
        } else {
            return biOp.toString();
        }
    }
}

