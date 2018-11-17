package discretemaths.firstyear.lab1;

import java.io.PrintWriter;
import java.util.Scanner;


public class Krom {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);

        int N = in.nextInt();
        int m = in.nextInt();
        int ch;
        int k = 0;
        int m2 = m;
        while (m != 0) {
            int p = 0;
            for (int i = 0; i < N; i++) {
                ch = in.nextInt();
                p++;
                if (ch < 0) {
                    k++;
                    break;
                }
            }
            m--;
            if (p != N) in.next();
        }
        if (k == m2) {
            out.println("NO");
        } else out.println("YES");
    }
}




