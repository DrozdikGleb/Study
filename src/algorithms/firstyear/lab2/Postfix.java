package algorithms.firstyear.lab2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Stack2 {
    private int mSize;
    public int[] stackArray;
    private int top;

    public Stack2(int m) {
        this.mSize = m;
        stackArray = new int[mSize];
        top = 0;
    }

    public void push(int element) {
        stackArray[++top] = element;
    }

    public int pop() {
        return stackArray[top--];
    }

    public boolean isEmpty() {
        return (top == 0);
    }

    public int top() {
        return stackArray[top];
    }
}

public class Postfix {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("postfix.in"));
        FileWriter out = new FileWriter("postfix.out");
        Stack2 stack = new Stack2(101);

        int x = 0;
        int y = 0;
        String a = in.nextLine();
        String[] mas = a.split(" ");
        for (int i = 0; i < mas.length; i++) {

            if (mas[i].matches("[-+]?\\d+")) {

                stack.push(Integer.parseInt(mas[i]));
            } else {
                x = stack.top();
                stack.pop();
                y = stack.top();
                stack.pop();

                switch (mas[i]) {
                    case "+":
                        stack.push(x + y);
                        break;
                    case "-":
                        stack.push(y - x);
                        break;
                    case "*":
                        stack.push(x * y);
                        break;

                }
            }

        }
        out.write(String.valueOf(stack.stackArray[1]));
        out.close();
    }

}

