package Dz8;

import java.math.BigInteger;

/**
 * Created by Drozdov Gleb on 09.04.2017.
 */
public class GenericTabulator implements Tabulator {
    private int x1, x2, y1, y2, z1, z2;

    private String expression;

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
        this.expression = expression;

        switch (mode) {
            case "i":
                return tabulate(new ExpressionParser<>(new Int(0)));
            case "d":
                return tabulate(new ExpressionParser<>(new DoubleOperation(0d)));
            case "bi":
                return tabulate(new ExpressionParser<>(new BigInt(new BigInteger("0"))));
            case "u":
                return tabulate(new ExpressionParser<>(new UInt(0)));
            case "f":
                return tabulate(new ExpressionParser<>(new FloatFloat(0f)));
            case "b":
                return tabulate(new ExpressionParser<>(new ByteByte((byte) 0)));
        }
        return new Object[0][][];
    }

    private <T extends Number> Object[][][] tabulate(ExpressionParser<T> parser) throws Exception {
        TripleExpression<T> exp = parser.parse(expression);
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];

        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    Oper<T> x = parser.getOper().parse(Integer.toString(x1 + i));
                    Oper<T> y = parser.getOper().parse(Integer.toString(y1 + j));
                    Oper<T> z = parser.getOper().parse(Integer.toString(z1 + k));
                    try {
                        ans[i][j][k] = exp.evaluate(x, y, z).getValue();
                    } catch (ArithmeticException ex) {
                        ans[i][j][k] = null;
                    }
                }
            }
        }

        return ans;
    }

}
