package Dz8;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Const<T extends Number> implements TripleExpression<T> {
    private Oper<T> value;

    public Const(Oper<T> value) {
        this.value=value;
    }

    public Oper<T> evaluate(Oper<T> x, Oper<T> y, Oper<T> z) {
        return value;
    }

}
