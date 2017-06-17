package Dz6;


/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Const implements TripleExpression {
    private final int value;

    public Const(int value) {
        this.value=value;
    }

    public int evaluate (int x,int y,int z){
        return value;
    }

}
