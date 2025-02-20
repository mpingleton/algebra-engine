public class Parser {

    private Tokenizer input;
    public BinaryOp output;

    public Parser() {
        this.input = null;
        this.output = null;
    }

    public Parser(Tokenizer input) {
        this.input = input;
        this.output = new BinaryOp();
    }

    public int nextOp(int fromIndex) {
        BinaryOp next = new BinaryOp();

        int currentIndex = fromIndex;

        int indexOp = -1;
        while (currentIndex < input.numberTokens()) {
            Token t = input.getToken(currentIndex);

            if (t.type == Token.OPERATOR) {
                indexOp = currentIndex;
                currentIndex++;
                next.initOp(t.str.charAt(0));
                break;
            } else if (t.type == Token.WHITESPACE) {
                currentIndex++;
            } else {
                return -1;
            }
        }

        int indexRVal = -1;
        while (currentIndex < input.numberTokens()) {
            Token t = input.getToken(currentIndex);

            if (t.type == Token.CONSTANT) {
                indexRVal = currentIndex;
                currentIndex++;
                double v = Double.parseDouble(t.str);
                next.initRVal(v);
                break;
            } else if (t.type == Token.IDENTIFIER) {
                indexRVal = currentIndex;
                currentIndex++;
                next.initRVal(t.str);
                break;
            } else if (t.type == Token.WHITESPACE) {
                currentIndex++;
            } else {
                return -1;
            }
        }

        output.addNext(next);
        return currentIndex;
    }

    public int recParse(int fromIndex, BinaryOp parentOp, boolean first) {
        int currentIndex = fromIndex;

        // TODO: Find the L-Value, but only if (first).
        int indexLVal = -1;
        if (first) {
            while (currentIndex < input.numberTokens()) {
                Token t = input.getToken(currentIndex);

                if (t.type == Token.CONSTANT) {
                    indexLVal = currentIndex;
                    currentIndex++;
                    double v = Double.parseDouble(t.str);
                    parentOp.initLVal(v);
                    break;
                } else if (t.type == Token.IDENTIFIER) {
                    indexLVal = currentIndex;
                    currentIndex++;
                    parentOp.initLVal(t.str);
                    break;
                } else if (t.type == Token.WHITESPACE) {
                    currentIndex++;
                } else {
                    return -1;
                }
            }
        }

        // TODO: Find the Operator
        int indexOp = -1;
        while (currentIndex < input.numberTokens()) {
            Token t = input.getToken(currentIndex);

            if (t.type == Token.OPERATOR) {
                indexOp = currentIndex;
                currentIndex++;
                parentOp.initOp(t.str.charAt(0));
                break;
            } else if (t.type == Token.WHITESPACE) {
                currentIndex++;
            } else {
                return -1;
            }
        }

        // TODO: Find the R-Value
        int indexRVal = -1;
        while (currentIndex < input.numberTokens()) {
            Token t = input.getToken(currentIndex);

            if (t.type == Token.CONSTANT) {
                indexRVal = currentIndex;
                currentIndex++;
                double v = Double.parseDouble(t.str);
                parentOp.initRVal(v);
                break;
            } else if (t.type == Token.IDENTIFIER) {
                indexRVal = currentIndex;
                currentIndex++;
                parentOp.initRVal(t.str);
                break;
            } else if (t.type == Token.WHITESPACE) {
                currentIndex++;
            } else {
                return -1;
            }
        }

        while (currentIndex < input.numberTokens()) {
            int nextIndex = nextOp(currentIndex);
            currentIndex = nextIndex;
        }

        return currentIndex;
    }

    int parse() {
        return recParse(0, output, true);
    }
}
