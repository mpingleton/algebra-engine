package functions;

import java.util.ArrayList;

public class FunctionSelector {

    public static final int RAD = 0;
    public static final int DEG = 1;

    private ArrayList<String> functionNames;
    private ArrayList<Function> functions;

    public FunctionSelector() {
        functionNames = new ArrayList<>();
        functions = new ArrayList<>();
    }

    public Function getFunction(int mode, String name) {

        if (name.equalsIgnoreCase("abs"))
            return new Abs(mode);
        else if (name.equalsIgnoreCase("sqrt"))
            return new Sqrt(mode);
        else if (name.equalsIgnoreCase("cbrt"))
            return new Cbrt(mode);

        return null;
    }
}
