import java.util.ArrayList;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Type an arithmetic expression and press <enter>");
        System.out.print("> ");

        String expression = in.nextLine();
        in.close();

        Lexer lexer = new Lexer(expression);
        ArrayList<Token> tokens = lexer.makeTokens();

        Parser parser = new Parser(tokens);
        Object ast = parser.makeAbstractSyntaxTree();

        Interpreter interpreter = new Interpreter(ast);
        NumberValue result = interpreter.execute();

        System.out.println(result);
    }
}
