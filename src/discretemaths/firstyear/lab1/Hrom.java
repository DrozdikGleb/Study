package discretemaths.firstyear.lab1;

import java.util.Scanner;

public class Hrom {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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
                if (ch == 0) {
                    k++;
                    break;
                }
            }
            m--;
            if (p != N) in.next();
        }
        if (k == m2) {
            System.out.println("NO");
        } else System.out.println("YES");
    }
}
