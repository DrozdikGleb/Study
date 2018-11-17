package discretemaths.firstyear.combinatorics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T19 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("out.txt"));
        int n = in.nextInt();
        long k = in.nextLong();
        k++;
        String ans = "";
        long d[][] = new long[42][42];
        d[0][1] = 1;

        for (int i = 1; i < 2 * n + 1; i++) {
            for (int j = 1; j < 2 * n + 1; j++) {
                d[i][j] = d[i - 1][j - 1] + d[i - 1][j + 1];
            }
        }
        int deep = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (d[2 * n - (i + 1)][deep + 2] >= k) {
                ans += "(";
                deep++;
            } else {
                k -= d[2 * n - (i + 1)][deep + 2];
                ans += ")";
                deep--;
            }
        }
        out.write(ans);
        out.close();
    }
}

