package Dz6;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Multiply extends Operations {
    public Multiply(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    public int operation(int x, int y) {
        return x * y;
    }
}
