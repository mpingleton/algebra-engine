

public class BinaryOp {

    public static final char NULL = 0;
    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MUL = '*';
    public static final char DIV = '/';
    public static final char MOD = '%';
    public static final char POW = '^';

    public static int operatorPrecedence(char op) {
        if (op == ADD || op == SUB)
            return 2;
        else if (op == MUL || op == DIV || op == MOD)
            return 3;
        else if (op == POW)
            return 4;
        else
            return -1;
    }

    private char o;
    private double lVal, rVal;
    private BinaryOp lOp, rOp;

    public BinaryOp() {
        this.o = NULL;
        this.lVal = Double.NaN;
        this.rVal = Double.NaN;
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

    public void swap(BinaryOp other) {
        char oTmp = this.o;
        double lValTmp = this.lVal;
        double rValTmp = this.rVal;
        BinaryOp lOpTmp = this.lOp;
        BinaryOp rOpTmp = this.rOp;

        this.o = other.o;
        this.lVal = other.lVal;
        this.rVal = other.rVal;
        this.lOp = other.lOp;
        this.rOp = other.rOp;

        other.o = oTmp;
        other.lVal = lValTmp;
        other.rVal = rValTmp;
        other.lOp = lOpTmp;
        other.rOp = rOpTmp;
    }

    public boolean takesPrecedenceOver(BinaryOp other) {
        return (operatorPrecedence(o) > operatorPrecedence(other.o));
    }

    public void initLValueWithOtherRValue(BinaryOp other) {
        this.lVal = other.rVal;
        this.lOp = other.rOp;
    }

    public boolean isOpInitialized() {
        return (o != NULL);
    }

    public boolean isLValInitialized() {
        return !(lOp == null && Double.isNaN(lVal));
    }

    public boolean isRValInitialized() {
        return !(rOp == null && Double.isNaN(rVal));
    }

    public void initOp(char opCode) {
        o = opCode;
    }

    public void initLVal(double lValue) {
        lVal = lValue;
        lOp = null;
    }

    public void initLVal(String lValue) {
        // TODO

        lVal = 1.0; // This is temporary
    }

    public void initLVal(BinaryOp lValue) {
        lVal = Double.NaN;
        lOp = lValue;
    }

    public void initRVal(double rValue) {
        rVal = rValue;
        rOp = null;
    }

    public void initRVal(String rValue) {
        // TODO

        rVal = 1.0; // This is temporary.
    }

    public void initRVal(BinaryOp rValue) {
        rVal = Double.NaN;
        rOp = rValue;
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
