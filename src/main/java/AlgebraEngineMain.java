/*
 * Created 23-Jul-2024
 */

public class AlgebraEngineMain {

    public static void main(String[] args) {
        String equationString = "y+2=x^23.9";

        Equation equation = new Equation();
        int charactersParsed = equation.parseEquationFromString(equationString);

        System.out.println("Parsing " + equationString + " which is " + equationString.length() + " characters long.");
        System.out.println("Characters parsed: " + charactersParsed);

        if (charactersParsed != equationString.length()) {
            System.out.println("OOPS! There was an error parsing the equation.");
            return;
        }

        VariableBundle bundle = new VariableBundle();
        bundle.setValue("x", 4);

        System.out.println(equation);
        System.out.println(equation.replaceVariable(bundle));
    }
}
