package Dz7;


/**
 * Created by Drozdov Gleb on 29.03.2017.
 */
public class CheckedSquare extends Operations {
    protected CheckedSquare(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    private void check(int a) throws Exception {
        if (a < 0) {
            throw new ParsingException("sqrt from negative number");
        }
    }

    @Override
    protected int operation(int x, int y) throws Exception {
        check(x);
        return sqrt(x);
    }

    private int sqrt(int x) {
        int l = -1;
        int r = 46341;
        int res = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            if (m * m <= x) {
                res = m;
                l = m + 1;
            } else
                r = m - 1;
        }
        return res;
    }
}
