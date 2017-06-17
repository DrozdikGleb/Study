import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T9 {
    public static void gen(int n,int open,int close,String ans,OutputStreamWriter out) throws IOException{
        if (open+close==2*n){
           out.write(ans+"\n");
        }
        if (open < n)
        gen(n, open + 1, close, ans + '(',out);
        if (open > close)
        gen(n, open, close + 1, ans + ')',out);
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("brackets.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("brackets.out"));
        int n=in.nextInt();
        gen(n,0,0,"",out);
out.close();
    }
}
