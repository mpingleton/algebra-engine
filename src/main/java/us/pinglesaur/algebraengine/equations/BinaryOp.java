package us.pinglesaur.algebraengine.equations;

public class BinaryOp {

    public static final char NULL = 0;
    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MUL = '*';
    public static final char DIV = '/';
    public static final char MOD = '%';
    public static final char POW = '^';
    public static final char EQU = '=';
    public static final char LT = '<';
    public static final char GT = '>';

    public static int operatorPrecedence(char op) {
        if (op == EQU)
            return 1;
        else if (op == ADD || op == SUB)
            return 2;
        else if (op == MUL || op == DIV || op == MOD)
            return 3;
        else if (op == POW)
            return 4;
        else
            return -1;
    }

    private char o;
    public Value l, r;

    public BinaryOp() {
        this.o = NULL;
        this.l = null;
        this.r = null;
    }

    public BinaryOp(char opCode) {
        this.o = opCode;
        this.l = null;
        this.r = null;
    }

    public BinaryOp(Value lValue, char opCode, Value rValue) {
        this.o = opCode;
        this.l = lValue;
        this.r = rValue;
    }

    public void swap(BinaryOp other) {
        char oTmp = this.o;
        Value lTmp = this.l;
        Value rTmp = this.r;

        this.o = other.o;
        this.l = other.l;
        this.r = other.r;

        other.o = oTmp;
        other.l = lTmp;
        other.r = rTmp;
    }

    public boolean takesPrecedenceOver(BinaryOp other) {
        return (operatorPrecedence(o) > operatorPrecedence(other.o));
    }

    public void initLValueWithOtherRValue(BinaryOp other) {
        this.l = other.r;
    }

    public void initOp(char opCode) {
        o = opCode;
    }

    public void initLVal(Value lValue) {
        l = lValue;
    }

    public void initRVal(Value rValue) {
        r = rValue;
    }

    public void addNext(BinaryOp next) {
        if (next.takesPrecedenceOver(this)) {
            if (r == null) {
                next.initLValueWithOtherRValue(this);
                initRVal(new Value(next));
            } else {
                r.biOp.addNext(next);
            }
        } else {
            swap(next);
            initLVal(new Value(next));
        }
    }

    public Value[] evaluate(VariableBundle vars) {
        if (l == null || r == null)
            return null;

        Value[] lVal = l.calculate(vars);
        Value[] rVal = r.calculate(vars);

        int solutions = lVal.length * rVal.length;
        Value[] oVal = new Value[solutions];

        for (int l = 0; l < lVal.length; l++) {
            for (int r = 0; r < rVal.length; r++) {
                int oo = lVal.length * l + r;
                // TODO: Deal with other than constants.
                double d = switch (o) {
                    case ADD -> lVal[l].getConstant() + rVal[r].getConstant();
                    case SUB -> lVal[l].getConstant() - rVal[r].getConstant();
                    case MUL -> lVal[l].getConstant() * rVal[r].getConstant();
                    case DIV -> lVal[l].getConstant() / rVal[r].getConstant();
                    case MOD -> lVal[l].getConstant() % rVal[r].getConstant();
                    case POW -> Math.pow(lVal[l].getConstant(), rVal[r].getConstant());
                    default -> Double.NaN;
                };

                oVal[oo] = new Value(d);
            }
        }

        return oVal;
    }

    @Override
    public String toString() {
        String tmp = "";

        if (l != null)
            tmp += l.toString();

        tmp += o;

        if (r != null)
            tmp += r.toString();

        return tmp;
    }
}
