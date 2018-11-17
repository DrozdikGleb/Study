package algorithms.firstyear.lab4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class Num {
    int l, r, m;
}

public class task5 {
    public static String str = "";

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("matrix.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("matrix.out"));
        int n = in.nextInt();
        int[] matr = new int[n + 1];
        int d[][] = new int[n + 2][n + 2];
        Num s[][] = new Num[n + 2][n + 2];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == j) {
                    d[i][j] = 0;
                } else {
                    d[i][j] = -1;
                }
            }
        }
        int i2 = 0;
        for (int i = 0; i < 2 * n; i++) {
            int a = in.nextInt();
            if ((i % 2 == 0) || (i == 2 * n - 1)) {
                matr[i2] = a;
                i2++;
            }
        }

        int a = matrMult(d, matr, 0, n, s);
        ans(0, n, s);
        out.write(str);
        out.close();

    }

    public static void ans(int l, int r, Num s[][]) {
        if (s[l][r] != null) {
            str = str.concat("(");
            ans(s[l][r].l, s[l][r].m, s);
            ans(s[l][r].m, s[l][r].r, s);
            str = str.concat(")");

        } else {
            str = str.concat("A");
        }
    }

    public static int matrMult(int[][] d, int[] matr, int l, int r, Num[][] s) {
        if (d[l][r] == -1) {
            if (l == r - 1) {
                d[l][r] = 0;
            } else {
                d[l][r] = Integer.MAX_VALUE;
                for (int i = l + 1; i < r; i++) {
                    int cost = matr[l] * matr[i] * matr[r] + matrMult(d, matr, l, i, s) + matrMult(d, matr, i, r, s);
                    if (cost < d[l][r]) {
                        d[l][r] = cost;
                        s[l][r] = new Num();
                        s[l][r].l = l;
                        s[l][r].m = i;
                        s[l][r].r = r;

                    }
                }

            }
        }
        return d[l][r];

    }
}
