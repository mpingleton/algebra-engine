/*
 * Created 23-Jul-2024
 */

import functions.*;

public class AlgebraEngineMain {

    public static void main(String[] args) {
        Tokenizer t1 = new Tokenizer("2*3+4*5(2+(2+(1+1)))");
        t1.tokenize();
        System.out.println(t1);

        Parser p1 = new Parser(t1);
        System.out.println(p1.parse());
        System.out.println(p1.output);

        VariableBundle vars = new VariableBundle();
        System.out.println(p1.output.toValue(vars));

        FunctionSelector e = new FunctionSelector();
        Function fAbs = e.getFunction(FunctionSelector.RAD, "abs");
        Function fSqt = e.getFunction(FunctionSelector.RAD, "sqrt");
        Function fCbt = e.getFunction(FunctionSelector.RAD, "cbrt");
        if (fAbs == null || fSqt == null || fCbt == null) {
            System.out.println("Failed function execution");
            System.exit(-1);
        }

        double x1 = 9, x2 = -9;
        System.out.println("abs(" + x1 + ")=" + fAbs.execute(x1));
        System.out.println("abs(" + x2 + ")=" + fAbs.execute(x2));
        System.out.println("sqrt(" + x1 + ")=" + fSqt.execute(x1));
        System.out.println("sqrt(" + x2 + ")=" + fSqt.execute(x2));
        System.out.println("cbrt(" + x1 + ")=" + fCbt.execute(x1));
        System.out.println("cbrt(" + x2 + ")=" + fCbt.execute(x2));
    }
}
