package Dz6;

/**
 * Created by Drozdov Gleb on 29.03.2017.
 */
import static Dz6.Util.*;
public class ParserBitwiseTest extends ParserTest {
    protected ParserBitwiseTest() {
        levels.add(0, list(op("&", (a, b) -> (long) (a.intValue() & b.intValue()))));
        levels.add(0, list(op("^", (a, b) -> (long) (a.intValue() ^ b.intValue()))));
        levels.add(0, list(op("|", (a, b) -> (long) (a.intValue() | b.intValue()))));

        tests.addAll(list(
                op("6 & 1 + 2", (x, y, z) -> 2L),
                op("6 ^ 1 + 2", (x, y, z) -> 5L),
                op("6 | 1 + 2", (x, y, z) -> 7L),
                op("x & y", (x, y, z) -> x & y),
                op("x | y", (x, y, z) -> x | y),
                op("x ^ y", (x, y, z) -> x ^ y),
                op("x | z & y", (x, y, z) -> x | z & y),
                op("x ^ z & y", (x, y, z) -> x ^ z & y),
                op("x | z ^ y", (x, y, z) -> x | z ^ y),
                op("x & y + z", (x, y, z) -> x & y + z),
                op("x ^ y - z", (x, y, z) -> x ^ y - z),
                op("x | y + z", (x, y, z) -> x | y + z),
                op("(- - - x^1883669513)|(- x^1681810605)", (x, y, z) -> (- - - x^1883669513)|(- x^1681810605))
        ));
    }

    public static void main(final String[] args) {
        checkAssert(ParserBitwiseTest.class);
        new ParserBitwiseTest().test();
    }
}
