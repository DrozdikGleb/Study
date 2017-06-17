package Dz8;

/**
 * Created by Drozdov Gleb on 25.03.2017.
 */
public interface TripleExpression<T extends Number>  {
    Oper<T> evaluate(Oper<T> x, Oper<T> y, Oper<T> z) throws Exception;
}
