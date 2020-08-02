public class NumberValue {
    public final float value;

    public NumberValue(int value) {
        this.value = (float) value;
    }

    public NumberValue(float value) {
        this.value = (float) value;
    }

    public NumberValue(String value) {
        this.value = Float.parseFloat(value);
    }

    public NumberValue addition(NumberValue addend) {
        return new NumberValue(this.value + addend.value);
    }

    public NumberValue subtraction(NumberValue subtrahend) {
        return new NumberValue(this.value - subtrahend.value);
    }

    public NumberValue division(NumberValue divisor) {
        return new NumberValue(this.value / divisor.value);
    }

    public NumberValue multiplication(NumberValue factor) {
        return new NumberValue(this.value * factor.value);
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }
}
