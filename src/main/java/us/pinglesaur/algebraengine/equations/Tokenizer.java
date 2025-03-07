package us.pinglesaur.algebraengine.equations;

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

    public Token getToken(int index) {
        if (index >= this.tokens.size())
            return null;

        return this.tokens.get(index);
    }

    public int numberTokens() {
        return this.tokens.size();
    }

    public int tokenize() {
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
