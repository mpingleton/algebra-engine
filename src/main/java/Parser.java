public class Parser {

    private int currentIndex;
    private Tokenizer input;
    public Value output;

    public Parser() {
        this.currentIndex = 0;
        this.input = null;
        this.output = null;
    }

    public Parser(Tokenizer input) {
        this.currentIndex = 0;
        this.input = input;
        this.output = null;
    }

    private void placeValue(Value val) {
        if (output == null) {
            output = val;
        }
    }

    private boolean nextToken() {
        while (currentIndex < input.numberTokens()) {
            Token t = input.getToken(currentIndex);
            if (t.type == Token.WHITESPACE)
                currentIndex++;
            else
                return true;
        }

        return false;
    }

    private Value nextValue() {
        if (currentIndex >= input.numberTokens())
            return null;

        Token t = input.getToken(currentIndex);
        if (t.type != Token.CONSTANT && t.type != Token.IDENTIFIER && t.type != Token.DELIMITER)
            return null;

        Value val = new Value();

        if (t.type == Token.CONSTANT) {
            val.isCoeffInit = true;
            val.coeff = Double.parseDouble(t.str);
            currentIndex++;
        }

        if (!nextToken())
            return val;

        t = input.getToken(currentIndex);
        if (t.type == Token.IDENTIFIER) {
            val.name = t.str;
            currentIndex++;
        }

        if (!nextToken())
            return val;

        t = input.getToken(currentIndex);
        if (t.type == Token.DELIMITER && t.str.equals("(")) {
            currentIndex++;
            val.func = parseRec();
        }

        return val;
    }

    private Value parseRec() {
        if (!nextToken())
            return null;

        Value val = nextValue();
        if (!nextToken())
            return val;

        while (currentIndex < input.numberTokens()) {
            BinaryOp newOp = new BinaryOp();
            Token tokenO = input.getToken(currentIndex);
            if (tokenO.type == Token.OPERATOR) {
                newOp.initOp(tokenO.str.charAt(0));
                currentIndex++;
            } else if (tokenO.type == Token.DELIMITER && tokenO.str.equals(")")) {
                currentIndex++;
                return val;
            } else {
                return null;
            }

            if (!nextToken())
                return null;

            Value valR = nextValue();
            if (valR == null)
                return null;

            newOp.initRVal(valR);

            Value tmpVal = val;
            while (tmpVal.biOp != null) {
                if (newOp.takesPrecedenceOver(tmpVal.biOp))
                    tmpVal = tmpVal.biOp.r;
                else
                    break;
            }

            Value newVal = new Value(newOp);
            tmpVal.swap(newVal);
            tmpVal.biOp.initLVal(newVal);
        }

        return val;
    }

    public boolean parse() {
        output = parseRec();
        return (output != null);
    }
}
