package Dz8;

/**
 * Created by Drozdov Gleb on 03.04.2017.
 */
public class CheckedNegate <T extends Number> extends Operations<T> {


    protected CheckedNegate(TripleExpression<T> x, TripleExpression<T> y) {
        super(x, y);
    }

    @Override
    protected Oper<T> operation(Oper<T> x, Oper<T> y) throws Exception {
        return x.negate();
    }
}

