package Dz8;

/**
 * Created by Drozdov Gleb on 19.04.2017.
 */
public class FloatFloat extends AbstractOper<Float> {
    protected FloatFloat(Float value) {
        super(value);
    }

    @Override
    public Oper<Float> parse(String str) {
        return new FloatFloat(Float.parseFloat(str));
    }

    @Override
    public Oper<Float> add(Oper<Float> y) {
        return new FloatFloat(getValue()+y.getValue());
    }

    @Override
    public Oper<Float> subtract(Oper<Float> y) {
        return new FloatFloat(getValue()-y.getValue());
    }

    @Override
    public Oper<Float> multiply(Oper<Float> y) {
        return new FloatFloat(getValue()*y.getValue());
    }

    @Override
    public Oper<Float> divide(Oper<Float> y) {
        return new FloatFloat(getValue()/y.getValue());
    }

    @Override
    public Oper<Float> mod(Oper<Float> y) {
        return new FloatFloat(getValue()%y.getValue());
    }

    @Override
    public Oper<Float> negate() {
        return new FloatFloat(-getValue());
    }

    @Override
    public Oper<Float> abs() {
        float b = getValue();
        return new FloatFloat(b >= 0 ? b : -b);
    }

    @Override
    public Oper<Float> square() {
        return new FloatFloat(getValue()*getValue());
    }
}
