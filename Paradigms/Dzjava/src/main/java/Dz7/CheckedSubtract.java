package Dz7;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class CheckedSubtract extends Operations {
    public CheckedSubtract(TripleExpression x, TripleExpression y) {
        super(x, y);
    }


    protected void check(int a, int b) throws Exception {
        if(b > 0 && a < Integer.MIN_VALUE + b) {
            throw new OverflowException();
        }
        if(b < 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException();
        }
    }


    public int operation(int x,int y) throws Exception {
        check(x,y);
        return x - y;
    }
}
