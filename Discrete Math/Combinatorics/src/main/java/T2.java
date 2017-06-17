import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T2 {
    public static int calc (int n) {
        return n ^ (n >> 1);
    }
    public static void main(String[] args)throws IOException {
        Scanner in = new Scanner(new File("gray.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("gray.out"));
        int n = in.nextInt();
        int l=(int)Math.pow(2, n);
        for (int i = 0; i <l ; i++) {
            String a;
            a=String.valueOf(Integer.toString(calc(i),2));
            if (a.length()<n){
                int k=a.length();
                String b=a;
                a="";
                for (int j=0;j<n-k;j++){
                    a+='0';
                }
                a+=b;
            }
            out.write(a+"\n");
        }
        out.close();

    }
}
