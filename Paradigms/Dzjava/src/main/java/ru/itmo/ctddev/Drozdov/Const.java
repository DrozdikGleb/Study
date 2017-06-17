package ru.itmo.ctddev.Drozdov;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Const implements ManyExpressions {
    private final int value;

    public Const(int value) {
        this.value=value;
    }


    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    public int evaluate (int x,int y,int z){
        return value;
    }

}
