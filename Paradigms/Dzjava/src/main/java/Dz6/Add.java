package Dz6;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Add extends Operations {
    public Add(TripleExpression x, TripleExpression y) {
        super(x, y);
    }


    public int operation(int x, int y) {
        return x + y;
    }
}
