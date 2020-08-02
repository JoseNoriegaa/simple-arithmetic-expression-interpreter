
public class NumberNode {
    public final Token token;

    public NumberNode(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.token.toString();
    }
}
