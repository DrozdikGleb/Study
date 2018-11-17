package algorithms.firstyear.lab3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Parking {
    public static void build(int[] tree, int k, int tl, int tr) {
        if (tl == tr) {
            tree[k] = tl;

        } else {
            int m = (tl + tr) / 2;
            build(tree, 2 * k, tl, m);
            build(tree, 2 * k + 1, m + 1, tr);
            tree[k] = Math.min(tree[2 * k], tree[2 * k + 1]);
        }

    }

    public static void set(int[] tree, int k, int tl, int tr, int pos) {
        if (tl == tr) {
            tree[k] = Integer.MAX_VALUE;
        } else {
            int m = (tl + tr) / 2;
            if (pos <= m) {
                set(tree, k * 2, tl, m, pos);
            } else {
                set(tree, k * 2 + 1, m + 1, tr, pos);
            }
            tree[k] = Math.min(tree[k * 2], tree[k * 2 + 1]);
        }

    }

    public static void exit(int[] tree, int k, int tl, int tr, int pos) {
        if (tl == tr) {
            tree[k] = tl;
        } else {
            int m = (tl + tr) / 2;
            if (pos <= m)
                exit(tree, 2 * k, tl, m, pos);
            else
                exit(tree, 2 * k + 1, m + 1, tr, pos);
            tree[k] = Math.min(tree[k * 2], tree[k * 2 + 1]);
        }

    }

    public static int min(int tree[], int k, int tl, int tr, int l, int r) {
        if (l > r)
            return Integer.MAX_VALUE;
        if (l == tl && r == tr)
            return tree[k];
        int m = (tl + tr) / 2;
        return Math.min(min(tree, k * 2, tl, m, l, Math.min(r, m)), min(tree, k * 2 + 1, m + 1, tr, Math.max(l, m + 1), r));
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("parking.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("parking.out"));
        int n = in.nextInt();
        int m = in.nextInt();
        int tree[] = new int[4 * n];

        build(tree, 1, 0, n - 1);
        for (int i = 0; i < m + 1; i++) {
            String[] line = in.nextLine().split(" ");
            if (line[0].equals("enter")) {
                int numb = Integer.parseInt(line[1]);
                numb--;
                int ans = min(tree, 1, 0, n - 1, numb, n - 1);
                if (ans == Integer.MAX_VALUE) {
                    ans = min(tree, 1, 0, n - 1, 0, numb - 1);
                }
                out.write(String.valueOf(ans + 1) + '\n');
                set(tree, 1, 0, n - 1, ans);

            }
            if (line[0].equals("exit")) {
                int numb = Integer.parseInt(line[1]);
                numb--;
                exit(tree, 1, 0, n - 1, numb);

            }
        }

        out.close();
    }
}
