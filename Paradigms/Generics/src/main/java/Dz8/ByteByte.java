package Dz8;

/**
 * Created by Drozdov Gleb on 19.04.2017.
 */
public class ByteByte extends AbstractOper<Byte> {
    public ByteByte(Byte value) {
        super(value);
    }

    @Override
    public Oper<Byte> parse(String str) {

        return new ByteByte(Byte.parseByte(str));
    }

    @Override
    public Oper<Byte> add(Oper<Byte> y) {

        return new ByteByte((byte) (getValue() + y.getValue()));
    }

    @Override
    public Oper<Byte> subtract(Oper<Byte> y) {

        return new ByteByte((byte) (getValue() - y.getValue()));
    }

    @Override
    public Oper<Byte> multiply(Oper<Byte> y) {

        return new ByteByte((byte) (getValue() * y.getValue()));
    }

    @Override
    public Oper<Byte> divide(Oper<Byte> y) {

        return new ByteByte((byte) (getValue() / y.getValue()));
    }

    @Override
    public Oper<Byte> mod(Oper<Byte> y) {

        return new ByteByte((byte) (getValue() % y.getValue()));
    }

    @Override
    public Oper<Byte> negate() {

        return new ByteByte((byte) -getValue());
    }

    @Override
    public Oper<Byte> abs() {
        byte b = getValue();
        return new ByteByte(b > 0 ? b : (byte) -b);
    }

    @Override
    public Oper<Byte> square() {
        return new ByteByte((byte) (getValue() * getValue()));
    }
}
