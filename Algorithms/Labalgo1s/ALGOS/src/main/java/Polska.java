

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Stack2 {
    private int mSize;
    public String[] stackArray;
    private int top;

    public Stack2(int m) {
        this.mSize = m;
        stackArray = new String[mSize];
        top = 0;
    }

    public void push(String element) {
        stackArray[++top] = element;
    }

    public String pop() {
        return stackArray[top--];
    }

    public boolean isEmpty() {
        return (top == 0);
    }

    public String top() {
        return stackArray[top];
    }
}

public class Polska {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("text.txt"));
        FileWriter out = new FileWriter("out.txt");
        Stack2 stack = new Stack2(101);
        int s = 0;
        String[] a = in.nextLine().split(" ");
        stack.push(a[0]);
        for (int i = 1; i < a.length; i++) {


            if ((a[i] == "+") || (a[i]=="-") || (a[i] == "*")) {
                switch (a[i]) {
                    case "+":
                        s = 0;
                        s += Integer.parseInt(stack.top());
                        stack.pop();
                        s += Integer.parseInt(stack.top());
                        stack.pop();
                        stack.push(String.valueOf(s));
                        break;
                    case "-":
                        s = 0;
                        s -= Integer.parseInt(stack.top());
                        stack.pop();
                        s += Integer.parseInt(stack.top());
                        stack.pop();
                        stack.push(String.valueOf(s));
                        break;
                    case "*":
                        s = 1;
                        s *= Integer.parseInt(stack.top());
                        stack.pop();
                        s *= Integer.parseInt(stack.top());
                        stack.pop();
                        stack.push(String.valueOf(s));
                        break;


                }

            } else {
                stack.push(a[i]);
            }

        }

        out.write(stack.stackArray[3]);
        out.close();
    }

}


