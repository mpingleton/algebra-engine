/*
 * Created 23-Jul-2024
 */

public class AlgebraEngineMain {

    public static void main(String[] args) {
        System.out.println("Token parser");

        Tokenizer tokenizer = new Tokenizer("2*x+4");
        tokenizer.tokenize();
        System.out.println(tokenizer);

        Parser parser = new Parser(tokenizer);
        parser.parse(0);
        System.out.println(parser.output);
        System.out.println(parser.output.evaluate());
    }
}
