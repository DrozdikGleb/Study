package algorithms.firstyear.lab4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("lis.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("lis.out"));
        int n = in.nextInt();
        int a[] = new int[n];
        int d[] = new int[n];
        int p[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            d[i] = 1;
            p[i] = 0;
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    if (1 + d[j] > d[i]) {
                        d[i] = 1 + d[j];
                        p[i] = j;
                    }
                }
            }
        }
        int pos = 0;
        int ans = d[0];
        for (int i = 0; i < n; i++) {
            if (d[i] > ans) {
                ans = d[i];
                pos = i;
            }
        }
        out.write(String.valueOf(ans) + "\n");
        int otv[] = new int[ans];
        int k = ans - 1;
        while (pos != 0) {
            otv[k] = pos;
            k--;
            pos = p[pos];
        }
        for (int i = 0; i < ans; i++) {
            out.write(a[otv[i]] + " ");
        }
        out.close();
    }
}
