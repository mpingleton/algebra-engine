package us.pinglesaur.algebraengine.equations;

public class Token {

    public static final int NULL = 0;
    public static final int WHITESPACE = 1;
    public static final int OPERATOR = 2;
    public static final int DELIMITER = 3;
    public static final int CONSTANT = 4;
    public static final int IDENTIFIER = 5;

    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '=');
    }

    public static boolean isDelimiter(char c) {
        return (c == '(' || c == ')');
    }

    public int first, last;
    public int type;
    public String str;

    public Token() {
        this.first = 0;
        this.last = 0;
        this.type = NULL;
        this.str = "";
    }

    int parseNext(String input, int fromIndex) {
        int currentIndex = fromIndex;

        while (currentIndex < input.length()) {
            char c = input.charAt(currentIndex);

            switch (type) {
                case NULL: {
                    if (Character.isWhitespace(c))
                        type = WHITESPACE;
                    else if (isOperator(c))
                        type = OPERATOR;
                    else if (isDelimiter(c)) {
                        type = DELIMITER;
                        first = currentIndex;
                        last = currentIndex;
                        str += c;

                        return currentIndex + 1;
                    }
                    else if (Character.isDigit(c) || c == '.')
                        type = CONSTANT;
                    else if (Character.isLetter(c))
                        type = IDENTIFIER;
                    else
                        return -1;

                    first = currentIndex;
                    break;
                }
                case WHITESPACE: {
                    if (!Character.isWhitespace(c))
                        return currentIndex;

                    break;
                }
                case OPERATOR: {
                    if (!isOperator(c))
                        return currentIndex;

                    break;
                }
                case CONSTANT: {
                    if (!Character.isDigit(c) && c != '.')
                        return currentIndex;

                    break;
                }
                case IDENTIFIER: {
                    if (!Character.isLetter(c))
                        return currentIndex;

                    break;
                }
                default: {
                    return -1;
                }
            }

            str += c;
            last = currentIndex;
            currentIndex++;
        }

        return currentIndex;
    }

    @Override
    public String toString() {
        String output = "(";

        output += "Type: ";
        switch (type) {
            case NULL: {
                output += "null";
                break;
            }
            case WHITESPACE: {
                output += "whitespace";
                break;
            }
            case OPERATOR: {
                output += "operator";
                break;
            }
            case DELIMITER: {
                output += "delimiter";
                break;
            }
            case CONSTANT: {
                output += "constant";
                break;
            }
            case IDENTIFIER: {
                output += "identifier";
                break;
            }
            default: {
                output += "invalid";
                break;
            }
        }
        output += ", ";

        output += "First: " + first + ", ";
        output += "Last: " + last + ", ";
        output += "Content: '" + str + "'";

        output += ")";
        return output;
    }
}
