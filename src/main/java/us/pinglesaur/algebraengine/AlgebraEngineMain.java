package us.pinglesaur.algebraengine;/*
 * Created 23-Jul-2024
 */

import us.pinglesaur.algebraengine.equations.Parser;
import us.pinglesaur.algebraengine.equations.Tokenizer;
import us.pinglesaur.algebraengine.equations.VariableBundle;
import us.pinglesaur.algebraengine.functions.Function;
import us.pinglesaur.algebraengine.functions.FunctionSelector;
import us.pinglesaur.algebraengine.graphing.Point2D;
import us.pinglesaur.algebraengine.graphing.Point3D;

public class AlgebraEngineMain {

    // General TODOs.
    // TODO: Equivalence. (=, >=, <=)
    // TODO: Multiple solutions.
    // TODO: Points (Both 2D and 3D).
    // TODO: Graphs (Both 2D and 3D?).
    // TODO: Imaginary numbers? (Later maybe?)
    // TODO: Point analyzers (linear and quadratic regression).
    // TODO: Graph analyzers (intersections, plot points, min/max, find axis crossings)

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

        Tokenizer t2 = new Tokenizer("x(2.5*2)");
        t2.tokenize();
        System.out.println(t2);

        Parser p2 = new Parser(t2);
        System.out.println(p2.parse());
        System.out.println(p2.output);

        vars.setValue("x", 4);
        System.out.println(p2.output.toValue(vars));

        Point2D point1 = new Point2D(3, 1);
        System.out.println(point1);

        Point3D point2 = new Point3D(3, 1, 2);
        System.out.println(point2);
    }
}
