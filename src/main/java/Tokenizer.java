import java.util.ArrayList;

public class Tokenizer {

    private String input;
    private ArrayList<Token> tokens;

    public Tokenizer() {
        this.input = "";
        this.tokens = new ArrayList<>();
    }

    public Tokenizer(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
    }

    int tokenize() {
        int currentIndex = 0;

        while (currentIndex < input.length()) {
            Token tmpToken = new Token();

            int nextToken = tmpToken.parseNext(input, currentIndex);
            if (nextToken < 0)
                return -1;

            tokens.add(tmpToken);
            currentIndex = nextToken;
        }

        return currentIndex;
    }

    @Override
    public String toString() {
        String output = "Number of tokens: " + tokens.size();
        output += "\n";

        for (Token token : tokens) {
            output += "\t" + token.toString() + "\n";
        }

        return output;
    }
}
