package Dz8;

/**
 * Created by Drozdov Gleb on 29.03.2017.
 */
public class CheckedAbs<T extends Number> extends Operations<T> {
    protected CheckedAbs(TripleExpression<T> x, TripleExpression<T> y) {
        super(x, y);
    }
    @Override
    protected Oper<T> operation(Oper<T> x, Oper<T> y)throws Exception {
        return x.abs();

    }
}
