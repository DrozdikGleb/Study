package Dz8;

/**
 * Created by Drozdov Gleb on 10.04.2017.
 */
public class Int extends AbstractOper<Integer> {
    public Int(Integer value) {
        super(value);
    }

    @Override
    public Oper<Integer> add(Oper<Integer> y) {
        return new Int(Math.addExact(getValue(), y.getValue()));
    }

    @Override
    public Oper<Integer> subtract(Oper<Integer> y) {

        return new Int(Math.subtractExact(getValue(), y.getValue()));
    }

    @Override
    public Oper<Integer> multiply(Oper<Integer> y) {

        return new Int(Math.multiplyExact(getValue(), y.getValue()));
    }

    @Override
    public Oper<Integer> divide(Oper<Integer> y) {

        return new Int(getValue() / y.getValue());
    }

    @Override
    public Oper<Integer> parse(String str) {

        return new Int(Integer.parseInt(str));
    }

    @Override
    public Oper<Integer> negate() {

        return new Int(Math.negateExact(getValue()));
    }

    @Override
    public Oper<Integer> abs() {

        return new Int(Math.abs(getValue()));
    }

    @Override
    public Oper<Integer> square() {

        return new Int(Math.multiplyExact(getValue(),getValue()));
    }

    @Override
    public Oper<Integer> mod(Oper<Integer> y) {

        return new Int(getValue() % y.getValue());
    }
}