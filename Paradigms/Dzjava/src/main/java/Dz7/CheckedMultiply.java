package Dz7;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class CheckedMultiply extends Operations {
    public CheckedMultiply(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected void check(int a, int b) throws Exception {
        if (a > b) {
            check(b, a);
        } else {
            if (a < 0) {
                if (b < 0) {
                    if (a < Integer.MAX_VALUE / b) {
                        throw new OverflowException();
                    }
                } else if (b > 0) {
                    if (Integer.MIN_VALUE / b > a) {
                        throw new OverflowException();
                    }
                }
            } else if (a > 0) {
                if (a > Integer.MAX_VALUE / b) {
                    throw new OverflowException();
                }
            }
        }

    }


    public int operation(int x, int y) throws Exception {
        check(x, y);
        return x * y;
    }
}
