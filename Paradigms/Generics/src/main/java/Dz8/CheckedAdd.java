package Dz8;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class CheckedAdd <T extends Number> extends Operations<T> {
    public CheckedAdd(TripleExpression<T> x, TripleExpression<T> y) {
        super(x, y);
    }

    public Oper<T> operation(Oper<T> x, Oper<T> y) throws Exception
    {
        return x.add(y);
    }
}
