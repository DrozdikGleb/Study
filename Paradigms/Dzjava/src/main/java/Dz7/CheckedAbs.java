package Dz7;

/**
 * Created by Drozdov Gleb on 29.03.2017.
 */
public class CheckedAbs extends Operations {
    protected CheckedAbs(TripleExpression x, TripleExpression y) {
        super(x, y);
    }
    protected void check(int a) throws Exception {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }
    @Override
    protected int operation(int x, int y)throws Exception {
        check(x);
        if(x < 0) {
            x = -x;
        }
        return x;

    }
}
