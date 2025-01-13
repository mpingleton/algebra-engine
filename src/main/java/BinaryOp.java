

public class BinaryOp {

    public static final char NULL = 0;
    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MUL = '*';
    public static final char DIV = '/';
    public static final char MOD = '%';
    public static final char POW = '^';

    private char o;
    private double lVal, rVal;
    private BinaryOp lOp, rOp;

    public BinaryOp() {
        this.o = NULL;
        this.lVal = 0.0;
        this.rVal = 0.0;
        this.lOp = null;
        this.rOp = null;
    }

    public BinaryOp(char opCode, double lValue, double rValue) {
        this.o = opCode;
        this.lVal = lValue;
        this.rVal = rValue;
        this.lOp = null;
        this.rOp = null;
    }

    public BinaryOp(char opCode, BinaryOp lValue, double rValue) {
        this.o = opCode;
        this.lVal = Double.NaN;
        this.rVal = rValue;
        this.lOp = lValue;
        this.rOp = null;
    }

    public BinaryOp(char opCode, double lValue, BinaryOp rValue) {
        this.o = opCode;
        this.lVal = lValue;
        this.rVal = Double.NaN;
        this.lOp = null;
        this.rOp = rValue;
    }

    public BinaryOp(char opCode, BinaryOp lValue, BinaryOp rValue) {
        this.o = opCode;
        this.lVal = Double.NaN;
        this.rVal = Double.NaN;
        this.lOp = lValue;
        this.rOp = rValue;
    }

    public double evaluate() {
        if (lOp != null)
            lVal = lOp.evaluate();
        if (rOp != null)
            rVal = rOp.evaluate();

        return switch (o) {
            case ADD -> lVal + rVal;
            case SUB -> lVal - rVal;
            case MUL -> lVal * rVal;
            case DIV -> lVal / rVal;
            case MOD -> lVal % rVal;
            case POW -> Math.pow(lVal, rVal);
            default -> Double.NaN;
        };
    }

    @Override
    public String toString() {
        String tmp = "";

        if (lOp == null)
            tmp += lVal;
        else
            tmp += lOp.toString();

        tmp += o;

        if (rOp == null)
            tmp += rVal;
        else
            tmp += rOp.toString();

        return tmp;
    }
}
