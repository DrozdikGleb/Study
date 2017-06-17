package ru.itmo.ctddev.Drozdov;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Variable implements ManyExpressions{
    private final char var;

    public Variable(String var) {
        this.var = var.charAt(0);
    }

    public int evaluate(int x) {
        return x;
    }
    @Override
    public double evaluate(double x) {
        return x;
    }
    public int evaluate(int x,int y,int z){
        switch (var) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
        }
        return 0;
    }
}
