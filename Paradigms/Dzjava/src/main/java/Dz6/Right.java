package Dz6;

/**
 * Created by Drozdov Gleb on 28.03.2017.
 */
public class Right  extends Operations{
    public Right(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    public int operation(int x, int y) {
        return x >> y;
    }
}
