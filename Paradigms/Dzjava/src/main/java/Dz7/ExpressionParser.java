package Dz7;


/**
 * Created by Drozdov Gleb on 25.03.2017.
 */

public class ExpressionParser implements Parser {
    private int i, num;
    private String str;
    private char v;

    private enum Char {number, plus, minus, mul, div, bracket1, bracket2, var, or, and, xor, mod, sq,none}

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

    private void getNext() throws Exception {
        deleteWhitespace();
        char ch = next();
        id = Char.none;
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
                    if (str.length() >= i + 10 && str.substring(i, i + 10).equals("2147483648")) {
                        num = Integer.parseInt(str.substring(i - 1, i + 10));
                        i += 10;
                        id = Char.number;
                    } else {
                        id = Char.minus;
                    }
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
                    if (!str.substring(i-1,i+3).equals("abs ")){
                        throw new ParsingException("Wrong expression with abs");
                    }
                    id = Char.mod;
                    i += 3;
                    break;
                case 's':
                    id = Char.sq;
                    i += 3;
                    break;
            }

        }
        if (ch == 'x' || ch == 'y' || ch == 'z') {
            id = Char.var;
            v = ch;
        }
        if (id==Char.none&&(ch!=' ')) {
            throw new ParsingException("unexpected char: \"" + ch + "\" at index: " + (i - 1));
        }



            deleteWhitespace();

    }


    private TripleExpression end()throws Exception{
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
                rez = new CheckedNegate(end());
                break;
            case sq:
                rez = new CheckedSquare(end(), new Const(0));
                break;
            case mod:
                rez = new CheckedAbs(end(), new Const(0));
                break;

            case bracket1:
                rez = add();
                getNext();
                break;

            default:
                rez = null;
                throw new ParsingException("unknown symbol at position"+ i);
        }
        return rez;
    }

    private TripleExpression mul() throws Exception{
        TripleExpression left = end();
        while (true) {
            switch (id) {
                case mul:
                    left = new CheckedMultiply(left, end());
                    break;

                case div:
                    left = new CheckedDivide(left, end());
                    break;

                default:
                    return left;
            }
        }
    }

    private TripleExpression add()throws Exception {
        TripleExpression left = mul();
        while (true) {
            switch (id) {
                case minus:
                    left = new CheckedSubtract(left, mul());
                    break;

                case plus:
                    left = new CheckedAdd(left, mul());
                    break;

                default:
                    return left;
            }
        }
    }


    /*private TripleExpression shifts() {
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
    }*/


    public TripleExpression parse(String str) throws Exception{
        this.str = str;
        String expr = str;
        int bb = 0;
        for (int i = 0; i < expr.length() ; i++) {
            if (expr.charAt(i) == '(') {
                bb++;
            } else if(expr.charAt(i) == ')') {
                bb--;
            }
            if (bb < 0) {
                throw new ParsingException("unexpected ) at position: " + i);
            }
        }
        if (bb != 0) {
            throw new ParsingException("expected ) at end");
        }
        i = 0;

        return add();
    }
}
