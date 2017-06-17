package ru.itmo.ctddev.Drozdov;

/**
 * Created by Drozdov Gleb on 20.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println(
                    new Add(
                            new Subtract(
                                    new Multiply(
                                            new Variable("x"),
                                            new Variable("x")
                                    ),
                                    new Multiply(
                                            new Const(2),
                                            new Variable("x")
                                    )
                            ),
                            new Const(1)
                    ).evaluate(Integer.parseInt(args[0]))
            );
        }
    }
}