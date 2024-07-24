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

    public String toString() {
        StringBuilder returnString = new StringBuilder();

        returnString.append(leftSide.toString());
        returnString.append("=");
        returnString.append(rightSide.toString());

        return returnString.toString();
    }
}
