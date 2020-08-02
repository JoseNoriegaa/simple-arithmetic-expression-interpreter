import java.util.ArrayList;

public class Parser {
    private final ArrayList<Token> tokens;
    private int position;
    private Token currentToken;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.position = -1;
        this.next();
    }

    private void next() {
        this.position++;
        if (this.position < this.tokens.size()) {
            this.currentToken = this.tokens.get(this.position);
        } else {
            this.currentToken = null;
        }
    }

    public Object makeAbstractSyntaxTree() {
        return this.expression();
    }

    private Object factor() {
        Token token = this.currentToken;
        if (token.type == Token.TYPES.PLUS || token.type == Token.TYPES.MINUS) {
            this.next();
            Object factor = this.factor();
            return new UnaryOperationNode(token, factor);
        } else if (token.type == Token.TYPES.INT || token.type == Token.TYPES.FLOAT) {
            this.next();
            return new NumberNode(token);
        } else if (token.type == Token.TYPES.LEFTPARENTHESES) {
            this.next();
            Object expression = this.expression();
            if (this.currentToken.type == Token.TYPES.RIGHTPARENTHESES) {
                this.next();
                return expression;
            } else {
                throw new Error("Expected ')' but got <" + this.currentToken.type.toString() + ">");
            }
        }
        throw new Error("Expected '+', '-', '(', a float or an integer value but got <" + this.currentToken.type.toString() + ">");
    }

    private Object term() {
        Object left = this.factor();
        while (this.currentToken != null && (this.currentToken.type == Token.TYPES.MULTIPLICATION || this.currentToken.type == Token.TYPES.DIVISION)) {
            Token operation = this.currentToken;
            this.next();
            Object right = this.factor();
            left = new BinaryOperationNode(left, operation, right);
        }
        return left;
    }

    private Object expression() {
        Object left = this.term();
        while (this.currentToken != null && (this.currentToken.type == Token.TYPES.PLUS || this.currentToken.type == Token.TYPES.MINUS)) {
            Token operation = this.currentToken;
            this.next();
            Object right = this.term();
            left = new BinaryOperationNode(left, operation, right);
        }
        return left;
    }
}
