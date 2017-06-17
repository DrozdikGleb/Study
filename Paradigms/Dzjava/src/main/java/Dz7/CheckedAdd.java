package Dz7;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class CheckedAdd extends Operations {
    public CheckedAdd(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected void check(int a, int b) throws Exception {
        if (a > 0 && b > Integer.MAX_VALUE - a) {
            throw new OverflowException();
        }
        if (a < 0 &&  b < Integer.MIN_VALUE - a) {
            throw new OverflowException();
        }
    }

    public int operation(int x, int y) throws Exception
    {
        check(x, y);
        return x + y;
    }
}
