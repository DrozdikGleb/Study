package ru.itmo.ctddev.Drozdov;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Divide extends Operations {
    public Divide(ManyExpressions x, ManyExpressions y) {
        super(x, y);
    }

    @Override
    public double operation(double x, double y) {
        return x / y;
    }

    @Override
    public int operation(int x,int y) {
        return x/y;
    }
}
