package ru.itmo.ctddev.Drozdov;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Subtract extends Operations {
    public Subtract(ManyExpressions x, ManyExpressions y) {
        super(x, y);
    }

    @Override
    protected double operation(double x, double y) {
        return x-y;
    }
    public int operation(int x,int y) {
        return x-y;
    }
}
