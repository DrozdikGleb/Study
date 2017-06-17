package Dz6;

/**
 * Created by Drozdov Gleb on 28.03.2017.
 */
public class Left extends Operations {

    public Left(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    public int operation(int x, int y) {
        return x << y;
    }
}

