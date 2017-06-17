package Dz8;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Variable<T extends Number> implements TripleExpression<T> {
    private final char var;

    public Variable(String var) {
        this.var = var.charAt(0);
    }


    public Oper<T> evaluate(Oper<T> x, Oper<T> y, Oper<T> z) {
        switch (var) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
        }
        return null;
    }
}
