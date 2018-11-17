package discretemaths.firstyear.combinatorics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T15 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("num2choose.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("num2choose.out"));


        int n = in.nextInt();
        int k = in.nextInt();
        long m = in.nextLong();
        long[][] C = new long[n + 1][n + 1];
        C[0][1] = 1;
        String ans = "";
        int next = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++)
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
        }
        while (k > 0) {
            if (m < (C[n - 1][k])) {
                ans += String.valueOf(next);
                ans += " ";
                k--;
            } else {
                m -= (C[n - 1][k]);
            }
            n--;
            next++;
        }
        out.write(ans);
        out.close();


    }
}
