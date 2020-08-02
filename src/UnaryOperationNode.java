
public class UnaryOperationNode {

    public final Token operation;
    public final Object node;

    public UnaryOperationNode(Token operation, Object node) {
        this.operation = operation;
        this.node = node;
    }

    @Override
    public String toString() {
        String representation = "(";
        if (operation.type == Token.TYPES.MINUS) {
            representation += "-";
        }
        representation += this.node.toString();
        representation += ")";
        return representation;
    }
}
