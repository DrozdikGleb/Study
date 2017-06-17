package Dz8;

/**
 * Created by Drozdov Gleb on 19.04.2017.
 */
public class Mod<T extends Number> extends Operations<T> {
    public Mod(TripleExpression<T> x, TripleExpression<T> y) {
        super(x, y);
    }

    @Override
    protected Oper<T> operation(Oper<T> x, Oper<T> y) {
        return x.mod(y);
    }
}
