package Dz8;


/**
 * Created by Drozdov Gleb on 29.03.2017.
 */
public class CheckedSquare<T extends Number> extends Operations <T>{
    protected CheckedSquare(TripleExpression x, TripleExpression y) {
        super(x, y);
    }


    @Override
    protected Oper<T> operation(Oper<T> x, Oper<T> y) throws Exception {
        return x.square();
    }


}
