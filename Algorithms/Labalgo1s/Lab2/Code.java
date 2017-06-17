package Lab2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Stack1 {
    private int mSize;
    public char[] stackArray;
    private int top;

    public Stack1(int m) {
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
    public boolean isEmpty(){
        return (top==0);
    }

    public char top() {
        return stackArray[top];
    }
}

public class Code {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("decode.in"));
        FileWriter out = new FileWriter("decode.out");
        Stack1 stack = new Stack1(200005);
        String a = in.nextLine();
        stack.push(a.charAt(0));
        char c=' ';
        int n=a.length();
        for(int i=1;i<a.length();i++){
            c=stack.top();
            stack.push(a.charAt(i));
            if (c==stack.top()){
                stack.pop();
                stack.pop();
                n-=2;
            }

        }

        for(int j=1;j<n+1;j++){
            out.write(stack.stackArray[j]);
        }
        out.close();
    }

}
