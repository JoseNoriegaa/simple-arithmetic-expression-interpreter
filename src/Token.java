/**
 * Token is a representation of each component into the
 * expression.
 */
public class Token {
    enum TYPES {
        INT("INT"),
        FLOAT("FLOAT"),
        PLUS("PLUS"),
        MINUS("MINUS"),
        MULTIPLICATION("MULTIPLICATION"),
        DIVISION("DIVISION"),
        LEFTPARENTHESES("LEFT PARENTHESES"),
        RIGHTPARENTHESES("RIGHT PARENTHESES");

        public final String value;

        TYPES(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public final TYPES type;
    public final String value;

    public Token(TYPES type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        String representation = this.type.toString();
        if (this.type == TYPES.FLOAT || this.type == TYPES.INT) {
            representation += ":" + this.value;
        }
        return representation;
    }
}
