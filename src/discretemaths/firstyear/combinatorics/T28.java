package discretemaths.firstyear.combinatorics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T28 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextmultiperm.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("nextmultiperm.out"));
        int n = in.nextInt();
        int mas[] = new int[n + 3];
        for (int i = 0; i < n; i++) {
            mas[i] = in.nextInt();
        }
        int j = 0;
        int i = n - 2;
        while ((i >= 0) && (mas[i] >= mas[i + 1]))
            i--;
        if (i >= 0) {
            j = i + 1;
            while ((j < n - 1) && (mas[j + 1] > mas[i]))
                j++;
            int temp = mas[i];
            mas[i] = mas[j];
            mas[j] = temp;
            for (int f = i + 1; f < (n + i + 1) / 2; f++) {
                int temp2 = mas[f];
                mas[f] = mas[n - (f - i)];
                mas[n - (f - i)] = temp2;
            }
            for (int t = 0; t < n; t++) {
                out.write(String.valueOf(mas[t]) + " ");
            }

        } else {
            for (int k = 0; k < n; k++) {
                out.write(0 + " ");
            }
        }
        out.close();
    }
}
