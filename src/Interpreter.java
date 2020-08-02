public class Interpreter {
    public Object abstractSyntaxTree;

    public Interpreter(Object abstractSyntaxTree) {
        this.abstractSyntaxTree = abstractSyntaxTree;
    }

    public NumberValue execute() {
        return this.evaluate(this.abstractSyntaxTree);
    }

    public NumberValue evaluate(Object node) {
        String mainObjectClassName = node.getClass().getSimpleName();
        return switch (mainObjectClassName) {
            case "UnaryOperationNode" -> this.unaryOperation((UnaryOperationNode) node);
            case "NumberNode" -> this.number((NumberNode) node);
            case "BinaryOperationNode" -> this.binaryOperation((BinaryOperationNode) node);
            default -> throw new Error("Unsupported node");
        };
    }

    private NumberValue binaryOperation(BinaryOperationNode node) {
        NumberValue left = this.evaluate(node.left);
        NumberValue right = this.evaluate(node.right);

        return switch (node.operation.type) {
            case PLUS -> left.addition(right);
            case MINUS -> left.subtraction(right);
            case MULTIPLICATION -> left.multiplication(right);
            case DIVISION -> left.division(right);
            default -> throw new Error("Unsupported operation type <" + node.operation.type + ">");
        };
    }

    private NumberValue number(NumberNode node) {
        return new NumberValue(node.token.value);
    }

    private NumberValue unaryOperation(UnaryOperationNode node) {
        NumberValue value = this.evaluate(node.node);
        return switch (node.operation.type) {
            case PLUS -> value;
            case MINUS -> value.multiplication(new NumberValue(-1));
            default -> throw new Error("Unsupported operation type <" + node.operation.type + ">");
        };
    }
}
