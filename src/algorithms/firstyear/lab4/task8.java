package algorithms.firstyear.lab4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class task8 {
    public static int INF = 2000000000;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("salesman.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("salesman.out"));
        int n = in.nextInt();
        int m = in.nextInt();
        int way[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    way[i][j] = 0;
                } else {
                    way[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            way[b - 1][a - 1] = way[a - 1][b - 1] = c;

        }
        int end = (int) Math.pow(2, n);
        int d[][] = new int[n][end];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < end; j++) {
                d[i][j] = INF;
            }
        }
        for (int i = 0; i < n; i++)
            d[i][0] = 0;
        for (int j = 1; j < end; j++) {
            for (int i = 0; i < n; i++) {
                int pow = (1 << i);
                if ((pow & j) == pow) {
                    for (int k = 0; k < n; k++) {
                        if (way[i][k] != -1) {
                            d[i][j] = Math.min(d[i][j], d[k][j - pow] + way[i][k]);
                        }
                    }

                }
            }
        }
        int ans = INF;
        for (int i = 0; i < n; i++) {
            int check = d[i][end - 1];
            if (check != 0) {
                ans = Math.min(ans, check);
            }
        }
        if (ans == INF) {
            ans = -1;
        }
        if (n == 1) {
            ans = 0;
        }
        out.write(String.valueOf(ans));
        out.close();
    }


}
