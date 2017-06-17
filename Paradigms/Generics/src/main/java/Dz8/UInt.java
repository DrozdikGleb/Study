package Dz8;

/**
 * Created by Drozdov Gleb on 19.04.2017.
 */
public class UInt extends AbstractOper<Integer> {
    protected UInt(Integer value) {
        super(value);
    }

    @Override
    public Oper<Integer> parse(String str) {
        return new UInt(Integer.parseInt(str));
    }

    @Override
    public Oper<Integer> add(Oper<Integer> y) {

        return new UInt(getValue() + y.getValue());
    }

    @Override
    public Oper<Integer> subtract(Oper<Integer> y) {

        return new UInt(getValue() - y.getValue());
    }

    @Override
    public Oper<Integer> multiply(Oper<Integer> y) {
        return new UInt(getValue() * y.getValue());
    }

    @Override
    public Oper<Integer> divide(Oper<Integer> y) {

        return new UInt(getValue() / y.getValue());
    }

    @Override
    public Oper<Integer> mod(Oper<Integer> y) {

        return new UInt(getValue() % y.getValue());
    }

    @Override
    public Oper<Integer> negate() {

        return new UInt(-getValue());
    }

    @Override
    public Oper<Integer> abs() {
        Integer b = getValue();
        return new UInt(b > 0 ? b : -b);
    }

    @Override
    public Oper<Integer> square() {

        return new UInt(getValue() * getValue());
    }
}
