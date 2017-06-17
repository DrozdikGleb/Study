var vars = ['x', 'y', 'z'];

function binary(left, right, f) {
    function evaluate() {
        return f(left.apply(null, arguments), right.apply(null, arguments));
    }

    return evaluate;
}

function add(left, right) {
    return binary(left, right, function (a, b) {
        return a + b;
    });
}

function subtract(left, right) {
    return binary(left, right, function (a, b) {
        return a - b;
    });
}

function multiply(left, right) {
    return binary(left, right, function (a, b) {
        return a * b;
    });
}

function divide(left, right) {
    return binary(left, right, function (a, b) {
        return a / b;
    });
}

function cnst(val) {
    function evaluate() {
        return val;
    }

    return evaluate;
}

function pi() {
    return Math.PI;
}
function e() {
    return Math.E;
}

function variable(name) {
    function evaluate() {
        for (var i = 0; i < vars.length; i++) {
            if (vars[i] == name) {
                return arguments[i];
            }
        }
    }

    return evaluate;
}

function minmax(arg, op) {
    function evaluate() {
        var rez = arg[0].apply(this, arguments);
        for (var i = 1; i < arg.length; i++)
            rez = op(arg[i].apply(this,arguments), rez);
        return rez;
    }

    return evaluate;
}

function min3() {
    return minmax(arguments, function (x, y) {
            return Math.min(x, y);
        }
    );
}

function max5() {
    return minmax(arguments, function (x, y) {
            return Math.max(x, y);
        }
    );
}

var x = variable('x');
var y = variable('y');
var z = variable('z');

function unary(x, f) {
    function evaluate() {
        return f(x.apply(this, arguments));
    }
    return evaluate;
}

function negate(x) {
    return unary(x, function (a) {
        return -a;
    });
}


function abs(x) {
    return unary(x, function (a) {
        return Math.abs(a);
    });
}