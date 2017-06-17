package Dz6;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public abstract class Operations implements TripleExpression {
    private TripleExpression a, b;

    protected Operations(TripleExpression x, TripleExpression y) {
        a = x;
        b = y;
    }

    protected abstract int operation(int x, int y);

    public int evaluate(int x, int y, int z) {
        return operation(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }


}
