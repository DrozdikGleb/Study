import java.io.*;
import java.util.Scanner;


class Numb {
    int a;
    int b;

    Numb(int a, int b) {
        this.a = a;
        this.b = b;
    }

}

public class task2 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("lcs.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("lcs.out"));


        int n = in.nextInt();
        int a[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        int m = in.nextInt();
        int b[] = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            b[i] = in.nextInt();
        }
        int d[][] = new int[n + 1][m + 1];
        Numb p[][] = new Numb[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                p[i][j] = new Numb(0, 0);
            }
        }
        for (int i = 1; i <= n; i++) {
            d[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            d[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i] == b[j]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                    p[i][j].a = i - 1;
                    p[i][j].b = j - 1;
                } else {
                    if (d[i][j - 1] >= d[i - 1][j]) {
                        d[i][j] = d[i][j - 1];
                        p[i][j].a = i;
                        p[i][j].b = j - 1;
                    } else {
                        d[i][j] = d[i - 1][j];
                        p[i][j].a = i - 1;
                        p[i][j].b = j;
                    }

                }
            }
        }
        int n2 = n;
        int m2 = m;
        int itog = d[n][m];
        out.write(String.valueOf(itog)+"\n");
        int ans[]=new int [itog];
        itog--;
        while (m2 != 0 && n2 != 0) {
            if ((p[n2][m2].a == n2-1) && (p[n2][m2].b == m2-1)) {
                ans[itog]=a[n2];
                itog--;//out.write(String.valueOf(a[n2]) + " ");
                n2--;
                m2--;
            } else if ((p[n2][m2].a == n2-1) && (p[n2][m2].b == m2)) {
                n2--;
            } else {
                m2--;
            }
        }
        for (int i = 0; i < d[n][m] ; i++) {
            out.write(String.valueOf(ans[i])+" ");
        }

        out.close();

    }
}
