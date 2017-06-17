package ru.itmo.ctddev.Drozdov;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Add extends Operations {
    public Add(ManyExpressions x, ManyExpressions y) {
        super(x, y);
    }

    @Override
    public double operation(double x, double y) {
        return x + y;
    }
    public int operation(int x,int y) {
        return x+y;
    }
}
