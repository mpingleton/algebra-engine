/*
 * Created 23-Jul-2024
 */

public class AlgebraEngineMain {

    public static void main(String[] args) {
        System.out.println("Token parser");

        Tokenizer t1 = new Tokenizer("2*x+4");
        Tokenizer t2 = new Tokenizer("2+x*4");
        t1.tokenize();
        t2.tokenize();
        System.out.println(t1);
        System.out.println(t2);

        Parser parser1 = new Parser(t1);
        parser1.parse();
        System.out.println(parser1.output);
        System.out.println(parser1.output.evaluate());

        Parser parser2 = new Parser(t2);
        parser2.parse();
        System.out.println(parser2.output);
        System.out.println(parser2.output.evaluate());
    }
}
