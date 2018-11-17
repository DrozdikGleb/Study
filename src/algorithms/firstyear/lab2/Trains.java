package algorithms.firstyear.lab2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Trains {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("trains.in "));
        FileWriter out = new FileWriter("trains.out");
        int distance = in.nextInt();
        int part = in.nextInt();
        int[] a = new int[1001];
        int[] b = new int[1001];
        int count = 0;
        int n = 0;
        int m = 0;
        int s = 0;
        while (in.hasNext()) {
            if (count % 2 == 0) {
                a[n] = in.nextInt();
                s += a[n];
                if (n == part) break;

                n++;
            } else {
                b[m] = in.nextInt();
                if (m == part) break;
                m++;
            }
            count++;

        }
        double ans = 0, s1, s2, f1, f2;
        double left = 0;
        double right = s;
        b[part] = 0;
        while (right - left > 0.001) {
            ans = (right + left) / 2;
            s1 = 0;
            s2 = 0;
            int i = 0;
            int j = part;
            f1 = a[0];
            f2 = ans;
            while (i < part) {
                if (f1 < f2) {
                    s1 += f1 * b[i];
                    s2 += f1 * b[j];
                    f2 -= f1;
                    i++;
                    f1 = a[i];
                } else {
                    s1 += f2 * b[i];
                    s2 += f2 * b[j];
                    f1 -= f2;
                    j++;
                    j = j % (part + 1);
                    f2 = a[j];
                }
                if ((s1 - s2) < distance && j < part)
                    break;
            }
            if ((s1 - s2) < distance && j < part)
                left = ans;
            else
                right = ans;
        }

        double d = ans;
        d = d * 1000;
        int i = (int) Math.round(d);
        d = (double) i / 1000;
        out.write(Double.toString(d));

        out.close();
    }

}
