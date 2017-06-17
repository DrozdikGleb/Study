package Dz8;

/**
 * Created by Drozdov Gleb on 11.04.2017.
 */
public class DoubleOperation extends AbstractOper<Double> {
    protected DoubleOperation(Double value) {
        super(value);
    }

    @Override
    public Oper<Double> parse(String str) {

        return new DoubleOperation(Double.parseDouble(str));
    }

    @Override
    public Oper<Double> add(Oper<Double> y) {

        return new DoubleOperation(getValue() + y.getValue());
    }

    @Override
    public Oper<Double> subtract(Oper<Double> y) {

        return new DoubleOperation(getValue() - y.getValue());
    }

    @Override
    public Oper<Double> multiply(Oper<Double> y) {
        return new DoubleOperation(getValue()*y.getValue());
    }

    @Override
    public Oper<Double> divide(Oper<Double> y) {
        return new DoubleOperation(getValue()/y.getValue());
    }

    @Override
    public Oper<Double> mod(Oper<Double> y) {
        return null;
    }

    @Override
    public Oper<Double> negate() {

        return new DoubleOperation(-getValue());
    }

    @Override
    public Oper<Double> abs() {

        return null;
    }

    @Override
    public Oper<Double> square() {

        return new DoubleOperation(getValue()*getValue());
    }
}
