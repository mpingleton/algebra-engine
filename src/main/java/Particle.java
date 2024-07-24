/*
 * Created 23-Jul-2024
 */

import java.util.ArrayList;
import java.util.List;

public class Particle {

    public static int OP_NULL = 0;
    public static int OP_POW = 1;
    public static int OP_MUL = 2;
    public static int OP_DIV = 3;
    public static int OP_ADD = 4;
    public static int OP_SUB = 5;

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
