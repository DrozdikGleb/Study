package Dz6;

/**
 * Created by Drozdov Gleb on 29.03.2017.
 */
public class Or extends Operations {
    protected Or(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    @Override
    protected int operation(int x, int y) {
        return x|y;
    }
}
