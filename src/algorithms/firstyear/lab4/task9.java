package algorithms.firstyear.lab4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class task9 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("lis.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("lis.out"));
        int n = in.nextInt();
        int l = in.nextInt();
        int d[] = new int[n];
        int h[] = new int[n];
        int w[] = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
            w[i] = in.nextInt();
        }
        d[0] = h[0];
        for (int i = 1; i < n; i++) {
            int hh = h[i];
            int wi = w[i];
            int hi = d[i - 1] + h[i];
            for (int j = i - 1; j >= 0; j--) {
                wi += w[j];
                hh = Math.max(hh, h[j]);
                if (wi > l) {
                    break;
                }
                if (j > 0) {
                    if (d[j - 1] + hh < hi) {
                        hi = d[j - 1] + hh;
                    }
                } else {
                    if (hh < hi) {
                        hi = hh;
                    }
                }
            }

            d[i] = hi;
        }

        out.write(String.valueOf(d[n - 1]));
        out.close();
    }
}
