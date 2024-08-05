/*
 * Created 23-Jul-2024
 */

public class Equation {

    protected Expression leftSide, rightSide;

    public Equation() {
        leftSide = new Expression();
        rightSide = new Expression();
    }

    public Equation(Equation equation) {
        leftSide = new Expression(equation.leftSide);
        rightSide = new Expression(equation.rightSide);
    }

    public Equation replaceVariable(VariableBundle bundle) {
        Equation newEquation = new Equation();

        newEquation.leftSide = leftSide.replaceVariable(bundle);
        newEquation.rightSide = rightSide.replaceVariable(bundle);

        return newEquation;
    }

    public int parseEquationFromString(String input) {
        int index = 0;

        leftSide = new Expression();
        rightSide = new Expression();

        index = leftSide.parseExpressionFromString(input, index);

        if (input.charAt(index) == '=') {
            index++;
        } else {
            return index;
        }

        index = rightSide.parseExpressionFromString(input, index);

        return index;
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder();

        returnString.append(leftSide.toString());
        returnString.append("=");
        returnString.append(rightSide.toString());

        return returnString.toString();
    }
}
