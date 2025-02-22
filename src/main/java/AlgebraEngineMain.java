/*
 * Created 23-Jul-2024
 */

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
    }
}
