package discretemaths.firstyear.combinatorics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("allvectors.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("allvectors.out"));
        int n = in.nextInt();
        int list[] = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = 0;
        }
        for (int j = 0; j < Math.pow(2, n); j++) {
            for (int i = 0; i < n; i++) {
                out.write(String.valueOf(list[i]));
            }
            out.write("\n");
            list[n - 1]++;
            for (int i = n - 1; i > 0; i--)
                if (list[i] == 2) {
                    list[i - 1]++;
                    list[i] = 0;
                }

        }
        out.close();
    }

}

