package Lab2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*class Stack {
    private int mSize;
    private char[] stackArray;
    private int top;

    public Stack(int m) {
        this.mSize = m;
        stackArray = new char[mSize];
        top = 0;
    }

    public void push(char element) {

        stackArray[++top] = element;
    }

    public char pop() {

        return stackArray[top--];
    }

    public boolean isEmpty() {

        return (top == 0);
    }

    public char top() {
        return stackArray[top];
    }
}

public class Skobka {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("brackets.in"));
        FileWriter out = new FileWriter("brackets.out");
        String a = in.nextLine();
        Stack stack = new Stack(10010);
        char c = ' ';
    int k=0;


        for (int i = 0; i < a.length(); i++) {
            {
                if ((a.charAt(i) == '(') || (a.charAt(i) == '{') || (a.charAt(i) == '[')) {
                    stack.push(a.charAt(i));

                } else {
                    c = stack.top();
                       if (stack.isEmpty()) k=-1;
                    else
                       if ((c == '(' && a.charAt(i) == ')') ||
                            (c == '{' && a.charAt(i) == '}') ||
                            (c == '[' && a.charAt(i) == ']')) {
                        stack.pop();
                    } else k=-1;

                }
            }
        }
        if ( (a.length() != 1) && (k!=-1) &&(stack.isEmpty())) {
            out.write("YES");
        } else out.write("NO");
        out.close();
    }

}*/
