package us.pinglesaur.algebraengine.equations;

import java.util.ArrayList;

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

    private boolean end() {
        return !(currentIndex < input.numberTokens());
    }

    public void takeWhitespace() {
        while (currentIndex < input.numberTokens()) {
            Token t = input.getToken(currentIndex);
            if (t.type != Token.WHITESPACE)
                break;

            currentIndex++;
        }
    }

    public void nextValue(Value value) {
        if (value == null)
            return;

        takeWhitespace();
        if (end())
            return;

        // TODO: coefficient
        Token tokenCoeff = input.getToken(currentIndex);
        if (tokenCoeff == null)
            return;
        else if (tokenCoeff.type == Token.CONSTANT) {
            value.type = Value.TYPE_CONSTANT;
            value.isCoeffInit = true;
            value.coeff = Double.parseDouble(tokenCoeff.str);

            currentIndex++;
        }

        if (end())
            return;

        // TODO: identifier
        Token tokenIdent = input.getToken(currentIndex);
        if (tokenIdent == null)
            return;
        else if (tokenIdent.type == Token.IDENTIFIER) {
            value.type = Value.TYPE_VARIABLE;
            value.name = tokenIdent.str;

            currentIndex++;
        }

        if (end())
            return;

        // TODO: parentheses opening
        Token tokenFuncOpen = input.getToken(currentIndex);
        if (tokenFuncOpen == null)
            return;
        else if (tokenFuncOpen.type == Token.DELIMITER && tokenFuncOpen.str.equals("(")) {
            value.type = Value.TYPE_FUNCTION;
            currentIndex++;
        } else return;

        // TODO: recursive call.
        ArrayList<Value> recVals = new ArrayList<>();
        while (!end()) {
            takeWhitespace();
            if (end()) break;

            Token tokenDelimiter = input.getToken(currentIndex);
            if (tokenDelimiter.type == Token.DELIMITER) {
                if (tokenDelimiter.str.equals(",")) {
                    currentIndex++;
                } else if (tokenDelimiter.str.equals(")")) {
                    currentIndex++;
                    break;
                }
            }

            Value recVal = new Value();
            parseRec(recVal);
            recVals.add(recVal);
        }

        if (!recVals.isEmpty()) {
            Value[] arr = new Value[recVals.size()];
            arr = recVals.toArray(arr);
            value.func = arr;
        }
    }

    private void addBiOp(Value value, BinaryOp biOp) {
        if (value == null || biOp == null)
            return;

        Value tmpVal = value;
        while (tmpVal.type == Value.TYPE_BINARY_OP) {
            if (!biOp.takesPrecedenceOver(tmpVal.biOp)) {
                break;
            }

            tmpVal = tmpVal.biOp.r;
        }

        Value lVal = new Value(biOp);
        lVal.swap(tmpVal);
        tmpVal.biOp.initLVal(lVal);
    }

    public void parseRec(Value value) {
        if (value == null)
            return;
        else if (value.type != Value.TYPE_NULL)
            return;

        nextValue(value);
        if (end()) return;

        while (currentIndex < input.numberTokens()) {
            takeWhitespace();
            if (end()) break;

            // TODO: Operator
            BinaryOp newOp = new BinaryOp();
            Token tOp = input.getToken(currentIndex);
            if (tOp.type == Token.OPERATOR) {
                newOp.initOp(tOp.str.charAt(0));
                currentIndex++;
            } else if (tOp.type == Token.DELIMITER) {
                break;
            }

            takeWhitespace();
            if (end()) break;

            // TODO: r-value
            Value rVal = new Value();
            nextValue(rVal);
            newOp.initRVal(rVal);

            addBiOp(value, newOp);
        }
    }

    public int parse() {
        output = new Value();
        currentIndex = 0;
        parseRec(output);

        return currentIndex;
    }
}
