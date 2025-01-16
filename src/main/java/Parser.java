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

    public int parse(int fromIndex) {
        if (input == null)
            return -1;

        int currentIndex = fromIndex;
        while (currentIndex < input.numberTokens()) {
            Token t = input.getToken(currentIndex);

            switch (t.type) {
                case Token.WHITESPACE: {
                    currentIndex++;
                    break;
                }
                case Token.OPERATOR: {
                    if (t.str.length() != 1)
                        return -1;
                    else if (output.isOpInitialized()) {
                        if (!output.isLValInitialized() || !output.isRValInitialized())
                            return -1;

                        // TODO: Based on PEMDAS, figure out where the new OP should go.
                        BinaryOp newOp = new BinaryOp(t.str.charAt(0), output, Double.NaN);
                        output = newOp;
                    }
                    else
                        output.initOp(t.str.charAt(0));

                    currentIndex++;
                    break;
                }
                case Token.DELIMITER: {
                    // TODO: If it's an open parenthese, recursive call?
                    // TODO: If it's a closing parenthese, return from recursive call?

                    break;
                }
                case Token.CONSTANT: {
                    double v = Double.parseDouble(t.str);
                    if (!output.isLValInitialized())
                        output.initLVal(v);
                    else if (!output.isRValInitialized())
                        output.initRVal(v);
                    else
                        return -1;

                    currentIndex++;
                    break;
                }
                case Token.IDENTIFIER: {
                    if (!output.isLValInitialized())
                        output.initLVal(t.str);
                    else if(!output.isRValInitialized())
                        output.initRVal(t.str);
                    else
                        return -1;

                    currentIndex++;
                    break;
                }
                default: {
                    return -1;
                }
            }
        }

        return currentIndex;
    }
}
