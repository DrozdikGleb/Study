package Dz8;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public abstract class Operations<T extends Number> implements TripleExpression<T> {
    private TripleExpression<T> a, b;

    protected Operations(TripleExpression<T> x, TripleExpression<T> y) {
        a = x;
        b = y;
    }

    protected abstract Oper<T> operation(Oper<T> x, Oper<T> y) throws Exception;

    public Oper<T> evaluate(Oper<T> x, Oper<T> y, Oper<T> z) throws Exception {
        return operation(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }


}
