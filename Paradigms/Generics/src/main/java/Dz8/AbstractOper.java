package Dz8;

/**
 * Created by Drozdov Gleb on 11.04.2017.
 */
public abstract class AbstractOper<T extends Number> implements Oper<T> {
    T value;

    protected AbstractOper(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }


}
