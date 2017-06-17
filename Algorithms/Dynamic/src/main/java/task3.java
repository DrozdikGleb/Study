import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class task3 {
    public static void find(ArrayList<Integer> a, int n, int s, int[][] d, int[] m) {
        if (d[n][s] == 0) {
            return;
        }
        if (d[n - 1][s] == d[n][s]) {
            find(a, n - 1, s, d, m);
        } else {
            a.add(n);
            find(a, n - 1, s - m[n], d, m);

        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("knapsack.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("knapsack.out"));
        int n = in.nextInt();
        int s = in.nextInt();
        int[] m = new int[n + 1];
        int[] c = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            m[i] = in.nextInt();
        }
        for (int i = 1; i < n + 1; i++) {
            c[i] = in.nextInt();
        }
        int d[][] = new int[n + 1][s + 1];
        for (int i = 0; i < n + 1; i++) {
            d[i][0] = 0;
        }
        for (int i = 0; i < s + 1; i++) {
            d[0][i] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < s + 1; j++) {
                if (j >= m[i]) {
                    d[i][j] = Math.max(d[i - 1][j], (d[i - 1][j - m[i]] + c[i]));
                } else {
                    d[i][j] = d[i - 1][j];
                }
            }
        }
        ArrayList<Integer> a = new ArrayList<Integer>();
        find(a, n, s, d, m);
        out.write(String.valueOf(a.size()) + "\n");
        for (int i = a.size() - 1; i >= 0; i--) {
            out.write(String.valueOf(a.get(i)) + " ");
        }
        out.close();
    }
}
