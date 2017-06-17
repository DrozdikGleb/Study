package Dz8;

import java.math.BigInteger;

/**
 * Created by Drozdov Gleb on 11.04.2017.
 */
public class BigInt extends AbstractOper<BigInteger> {
    public BigInt(BigInteger value) {
        super(value);
    }

    @Override
    public Oper<BigInteger> add(Oper<BigInteger> y) {

        return new BigInt(getValue().add(y.getValue()));
    }

    @Override
    public Oper<BigInteger> subtract(Oper<BigInteger> y) {

        return new BigInt(getValue().subtract(y.getValue()));
    }

    @Override
    public Oper<BigInteger> multiply(Oper<BigInteger> y) {

        return new BigInt(getValue().multiply(y.getValue()));
    }

    @Override
    public Oper<BigInteger> divide(Oper<BigInteger> y) {

        return new BigInt(getValue().divide(y.getValue()));
    }

    @Override
    public Oper<BigInteger> parse(String str) {

        return new BigInt(new BigInteger(str));
    }

    @Override
    public Oper<BigInteger> negate() {

        return new BigInt(getValue().negate());
    }

    @Override
    public Oper<BigInteger> abs() {

        return new BigInt(getValue().abs());
    }

    @Override
    public Oper<BigInteger> square() {

        return new BigInt(getValue().multiply(getValue()));
    }

    @Override
    public Oper<BigInteger> mod(Oper<BigInteger> y) {

        return new BigInt(BigInteger.valueOf(new Int(getValue().intValue()).mod(new Int(y.getValue().intValue())).getValue()));
    }

    private BigInteger sqrt(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        while (b.compareTo(a) >= 0) {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if (mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
            else a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }
}
