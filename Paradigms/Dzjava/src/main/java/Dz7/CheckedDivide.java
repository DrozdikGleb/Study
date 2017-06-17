package Dz7;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class CheckedDivide extends Operations {
    public CheckedDivide(TripleExpression x, TripleExpression y) {
        super(x, y);
    }
    protected void check(int a, int b) throws Exception {
        if(a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException();
        }
        if (b == 0) {
            throw new ZeroDivisionException();
        }
    }

    public int operation(int x,int y) throws Exception {
        check(x, y);
        return x / y;
    }
}
