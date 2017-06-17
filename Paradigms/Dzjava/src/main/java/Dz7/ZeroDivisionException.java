package Dz7;

/**
 * Created by Drozdov Gleb on 03.04.2017.
 */
public class ZeroDivisionException extends Exception {
    public ZeroDivisionException() {
        super("division by zero");
    }
}
