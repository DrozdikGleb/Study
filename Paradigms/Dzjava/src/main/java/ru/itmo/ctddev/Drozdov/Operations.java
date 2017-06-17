package ru.itmo.ctddev.Drozdov;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public abstract class Operations implements ManyExpressions{
    private ManyExpressions a,b;

    protected Operations(ManyExpressions x, ManyExpressions y) {
        a = x;
        b = y;
    }

    public int evaluate(int x) {
        return (int) operation(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return operation(a.evaluate(x),b.evaluate(x));
    }

    public int evaluate (int x,int y,int z){
        return operation(a.evaluate(x,y,z),b.evaluate(x,y,z));
    }



    protected abstract double operation (double x,double y);
    protected abstract int operation (int x,int y);


}
