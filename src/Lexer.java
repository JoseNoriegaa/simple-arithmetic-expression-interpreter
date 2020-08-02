import java.util.ArrayList;

public class Lexer {
    private final String text;
    private int position;
    private char currentChar;

    public Lexer(String expression) {
        this.text = expression;
        this.position = -1;
        this.next();
    }

    private void next() {
        this.position++;
        if (this.position < this.text.length()) {
            this.currentChar = this.text.charAt(this.position);
        } else {
            this.currentChar = Character.MIN_VALUE;
        }
    }

    public ArrayList<Token> makeTokens() {
        ArrayList<Token> tokens = new ArrayList<>();

        while (this.currentChar != Character.MIN_VALUE) {
            if (Character.isDigit(this.currentChar)) {
                tokens.add(this.makeNumber());
            } else if (this.currentChar == '+') {
                tokens.add(new Token(Token.TYPES.PLUS, Character.toString(this.currentChar)));
                this.next();
            } else if (this.currentChar == '-') {
                tokens.add(new Token(Token.TYPES.MINUS, Character.toString(this.currentChar)));
                this.next();
            } else if (this.currentChar == '*') {
                tokens.add(new Token(Token.TYPES.MULTIPLICATION, Character.toString(this.currentChar)));
                this.next();
            } else if (this.currentChar == '/') {
                tokens.add(new Token(Token.TYPES.DIVISION, Character.toString(this.currentChar)));
                this.next();
            } else if (this.currentChar == '(') {
                tokens.add(new Token(Token.TYPES.LEFTPARENTHESES, Character.toString(this.currentChar)));
                this.next();
            } else if (this.currentChar == ')') {
                tokens.add(new Token(Token.TYPES.RIGHTPARENTHESES, Character.toString(this.currentChar)));
                this.next();
            } else if (this.currentChar == ' ' || this.currentChar == '\t') {
                this.next();
            } else {
                throw new Error("Unexpected character at position " + this.position);
            }
        }
        return tokens;
    }

    private Token makeNumber() {
        String tokenStr = "";
        int dotCount = 0;
        while (Character.isDigit(this.currentChar) || this.currentChar == '.') {
            tokenStr += this.currentChar;
            if (this.currentChar == '.') {
                dotCount++;
                if (dotCount > 1) {
                    throw new Error("Unexpected character at position " + this.position);
                }
            }
            this.next();
        }
        Token.TYPES type;
        if (dotCount == 0) {
            type = Token.TYPES.INT;
        } else {
            type = Token.TYPES.FLOAT;
        }
        return new Token(type, tokenStr);
    }
}
