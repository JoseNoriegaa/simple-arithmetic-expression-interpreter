
public class BinaryOperationNode {
    public final Object left;
    public final Token operation;
    public final Object right;

    public BinaryOperationNode(Object left, Token operation, Object right) {
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + this.left.toString() + " " + this.operation.toString() + " " + this.right.toString() + ")";
    }
}
