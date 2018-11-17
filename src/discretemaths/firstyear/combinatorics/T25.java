package discretemaths.firstyear.combinatorics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T25 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextchoose.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("nextchoose.out"));
        int n = in.nextInt();
        int k = in.nextInt();
        int mas[] = new int[k + 1];
        int mas2[] = new int[k + 1];

        for (int i = 0; i < k; i++) {
            mas[i] = in.nextInt();
            mas2[i] = mas[i];
        }
        mas2[k] = n + 1;
        int i = k - 1;
        while ((i >= 0) && (mas2[i + 1] - mas2[i] < 2)) {
            i--;
        }
        if (i >= 0) {
            mas2[i]++;
            for (int j = i + 1; j < k; j++) {
                mas2[j] = mas2[j - 1] + 1;
            }
            for (int j = 0; j < k; j++) {
                mas[j] = mas2[j];
            }
            for (int j = 0; j < k; j++) {
                out.write(String.valueOf(mas[j]) + " ");
            }
        } else out.write("-1");

        out.close();

    }
}
