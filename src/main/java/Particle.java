/*
 * Created 23-Jul-2024
 */

import java.util.ArrayList;
import java.util.List;

public class Particle {

    public static int OP_NULL = 0;
    public static int OP_POW = 1;
    public static int OP_MUL = 2;
    public static int OP_IMP = 3;
    public static int OP_DIV = 4;
    public static int OP_ADD = 5;
    public static int OP_SUB = 6;

    public static int VALUE_TYPE_NULL = 0;
    public static int VALUE_TYPE_CONSTANT = 1;
    public static int VALUE_TYPE_VARIABLE = 2;
    public static int VALUE_TYPE_FUNCTION = 3;

    protected int op;
    protected int valueType;
    protected String value;
    protected float parsedValue;
    protected List<Expression> subExpressions;

    public Particle() {
        op = OP_NULL;
        valueType = VALUE_TYPE_NULL;
        value = "";
        parsedValue = Float.NaN;
        subExpressions = new ArrayList<>();
    }

    public Particle(Particle particle) {
        op = particle.op;
        valueType = particle.valueType;
        value = particle.value;
        parsedValue = particle.parsedValue;
        subExpressions = new ArrayList<>();

        for (int i = 0; i < particle.subExpressions.size(); i++) {
            subExpressions.add(new Expression(particle.subExpressions.get(i)));
        }
    }

    public Particle replaceVariable(VariableBundle bundle) {
        Particle newParticle = new Particle(this);

        if (newParticle.valueType == VALUE_TYPE_VARIABLE) {
            float value = bundle.getValue(newParticle.value);

            if (!Float.isNaN(value)) {
                newParticle.valueType = VALUE_TYPE_CONSTANT;
                newParticle.parsedValue = value;
                newParticle.value = String.valueOf(value);
            }
        }

        return newParticle;
    }

    public int parseParticleFromString(String input, int offset) {
        int index = offset;

        while (index < input.length()) {
            char c = input.charAt(index);

            if (Character.isWhitespace(c)) {
                index++;
            }
            else if (c == '=') {
                return index;
            }
            else if (c == '^') {
                if (op == OP_NULL && valueType == VALUE_TYPE_NULL) {
                    op = OP_POW;
                } else if (op != OP_NULL && valueType == VALUE_TYPE_NULL) {
                    // TODO: Throw syntax error.
                } else {
                    return index;
                }

                index++;
            }
            else if (c == '*') {
                if (op == OP_NULL && valueType == VALUE_TYPE_NULL) {
                    op = OP_MUL;
                } else if (op != OP_NULL && valueType == VALUE_TYPE_NULL) {
                    // TODO: Throw syntax error.
                } else {
                    return index;
                }

                index++;
            }
            else if (c == '/') {
                if (op == OP_NULL && valueType == VALUE_TYPE_NULL) {
                    op = OP_DIV;
                } else if (op != OP_NULL && valueType == VALUE_TYPE_NULL) {
                    // TODO: Throw syntax error.
                } else {
                    return index;
                }

                index++;
            }
            else if (c == '+') {
                if (op == OP_NULL && valueType == VALUE_TYPE_NULL) {
                    op = OP_ADD;
                } else if (op != OP_NULL && valueType == VALUE_TYPE_NULL) {
                    // TODO: Throw syntax error.
                } else {
                    return index;
                }

                index++;
            }
            else if (c == '-') {
                if (op == OP_NULL && valueType == VALUE_TYPE_NULL) {
                    op = OP_SUB;
                } else if (op != OP_NULL && valueType == VALUE_TYPE_NULL) {
                    // TODO: Negative value?  Make a isNegative member to this class.
                } else {
                    return index;
                }

                index++;
            }
            else if (c == '(') {
                // This

                // Go one lever deeper.
                // Call the parseExpressionFromString(input, index + 1) at the next string position.
            }
            else if (c == ')') {
                // Stop. Return from this function.
            }
            else if (Character.isDigit(c) || c == '.') {
                if (valueType == VALUE_TYPE_NULL) {
                    valueType = VALUE_TYPE_CONSTANT;
                    value = "" + c;
                    parsedValue = Float.parseFloat(value);
                }
                else if (valueType == VALUE_TYPE_VARIABLE) {
                    // TODO: Throw syntax error or implicitly multiply?
                }
                else if (valueType == VALUE_TYPE_CONSTANT) {
                    value += c;
                    parsedValue = Float.parseFloat(value);
                }

                index++;
            }
            else if (Character.isLetter(c)) {
                if (valueType == VALUE_TYPE_NULL) {
                    if (op == OP_NULL) {
                        op = OP_IMP;
                    }

                    valueType = VALUE_TYPE_VARIABLE;
                    value = "" + c;
                }
                else if (valueType == VALUE_TYPE_VARIABLE) {
                    value += c;
                }
                else if (valueType == VALUE_TYPE_CONSTANT) {
                    return index;
                }

                index++;
            }
            else {
                // TODO: Throw syntax error.
            }
        }

        return index;
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder();

        if (op == OP_POW) {
            returnString.append("^");
        }
        else if (op == OP_MUL) {
            returnString.append("*");
        }
        else if (op == OP_DIV) {
            returnString.append("/");
        }
        else if (op == OP_ADD) {
            returnString.append("+");
        }
        else if (op == OP_SUB) {
            returnString.append("-");
        }

        returnString.append(value);

        if (subExpressions.size() > 0) {
            returnString.append("(");

            for (int subExpressionIndex = 0; subExpressionIndex < subExpressions.size(); subExpressionIndex++) {
                returnString.append(subExpressions.get(subExpressionIndex).toString());

                if (subExpressionIndex < subExpressions.size() - 1) {
                    returnString.append(", ");
                }
            }

            returnString.append(")");
        }

        return returnString.toString();
    }
}
