package Dz8;

/**
 * Created by Drozdov Gleb on 09.04.2017.
 */
public interface Oper<T extends Number> {
    T getValue();
    Oper<T> parse(String str);

    Oper<T> add(Oper<T> y);
    Oper<T> subtract(Oper<T> y);
    Oper<T> multiply(Oper<T> y);
    Oper<T> divide(Oper<T> y);
    Oper<T> mod(Oper<T> y);
    Oper<T> negate();
    Oper<T> abs();
    Oper<T> square();


}
