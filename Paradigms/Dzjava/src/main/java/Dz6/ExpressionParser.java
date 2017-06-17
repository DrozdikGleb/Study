package Dz6;

/**
 * Created by Drozdov Gleb on 25.03.2017.
 */

public class ExpressionParser implements Parser {
    private int i, num;
    private String str;
    private char v;

    private enum Char {number, plus, minus, mul, div, bracket1, bracket2, var, or, and, xor, mod, sq}

    private Char id;

    private char next() {
        if (i < str.length()) {
            char c = str.charAt(i);
            i++;
            return c;
        } else {
            return '@';
        }
    }

    private void deleteWhitespace() {
        while (Character.isWhitespace(next())) {
        }
        i--;
    }

    private void getNext() {
        deleteWhitespace();
        char ch = next();
        if (Character.isDigit(ch)) {
            StringBuilder str_num = new StringBuilder();
            while (Character.isDigit(ch)) {
                str_num.append(ch);
                ch = next();
            }
            i--;
            num = Integer.parseUnsignedInt(str_num.toString());
            id = Char.number;
        } else {
            switch (ch) {

                case '+':
                    id = Char.plus;
                    break;
                case '-':
                    id = Char.minus;
                    break;
                case '*':
                    id = Char.mul;
                    break;
                case '/':
                    id = Char.div;
                    break;
                case '(':
                    id = Char.bracket1;
                    break;
                case ')':
                    id = Char.bracket2;
                    break;
                case '|':
                    id = Char.or;
                    break;
                case '&':
                    id = Char.and;
                    break;
                case '^':
                    id = Char.xor;
                    break;
                case 'a':
                    id = Char.mod;
                    i += 3;
                    break;
                case 's':
                    id = Char.sq;
                    i += 6;
                    break;
            }

        }
        if (ch == 'x' || ch == 'y' || ch == 'z') {
            id = Char.var;
            v = ch;
        }


        deleteWhitespace();

    }


    private TripleExpression end() {
        getNext();
        TripleExpression rez;
        switch (id) {
            case number:
                rez = new Const(num);
                getNext();
                break;

            case var:
                rez = new Variable(Character.toString(v));
                getNext();
                break;

            case minus:
                rez = new Subtract(new Const(0), end());
                break;
            case sq:
                rez = new Square(end(), new Const(0));
                break;
            case mod:
                rez = new Abs(end(), new Const(0));
                break;
            case bracket1:
                rez = shifts();
                getNext();
                break;

            default:
                rez = null;
                break;
        }
        return rez;
    }

    private TripleExpression mul() {
        TripleExpression left = end();
        while (true) {
            switch (id) {
                case mul:
                    left = new Multiply(left, end());
                    break;

                case div:
                    left = new Divide(left, end());
                    break;

                default:
                    return left;
            }
        }
    }

    private TripleExpression add() {
        TripleExpression left = mul();
        while (true) {
            switch (id) {
                case minus:
                    left = new Subtract(left, mul());
                    break;

                case plus:
                    left = new Add(left, mul());
                    break;

                default:
                    return left;
            }
        }
    }


    private TripleExpression shifts() {
        TripleExpression left = xor();
        while (true) {
            switch (id) {
                case or:
                    left = new Or(left, xor());
                    break;
                default:
                    return left;
            }
        }

    }

    private TripleExpression xor() {
        TripleExpression left = and();
        while (true) {
            switch (id) {
                case xor:
                    left = new XOR(left, and());
                    break;
                default:
                    return left;
            }
        }
    }

    private TripleExpression and() {
        TripleExpression left = add();
        while (true) {
            switch (id) {
                case and:
                    left = new And(left, add());
                    break;
                default:
                    return left;
            }
        }
    }


    public TripleExpression parse(String str) {
        this.str = str;
        return shifts();
    }
}
