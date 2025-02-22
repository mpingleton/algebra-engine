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

    private Token nextToken() {
        while (currentIndex < input.numberTokens()) {
            Token t = input.getToken(currentIndex);
            currentIndex++;

            if (t.type != Token.WHITESPACE)
                return t;
        }

        return null;
    }

    private Value parseRec() {
        Value val = null;

        Token tokenL = nextToken();
        if (tokenL == null)
            return null;

        if (tokenL.type == Token.CONSTANT) {
            val = new Value(Double.parseDouble(tokenL.str));
        } else {
            return null;
        }

        while (currentIndex < input.numberTokens()) {
            Token tokenO = nextToken();
            Token tokenR = nextToken();
            if (tokenO == null || tokenR == null)
                return null;

            BinaryOp newOp = new BinaryOp();
            if (tokenO.type == Token.OPERATOR) {
                newOp.initOp(tokenO.str.charAt(0));
            } else {
                return null;
            }

            if (tokenR.type == Token.CONSTANT) {
                Value v = new Value(Double.parseDouble(tokenR.str));
                newOp.initRVal(v);
            } else {
                return null;
            }

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
