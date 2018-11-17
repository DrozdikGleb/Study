package discretemaths.firstyear.cfgrammar;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Drozdov Gleb on 07.06.2017.
 */
public class taskL {
    static int max = 10001;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("selectw.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("selectw.out"));
        int n = in.nextInt();
        int[] w = new int[n + 1];
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[] c = new int[n + 1];
        used = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }
        int root = 0;
        for (int i = 0; i < n; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            w[i + 1] = q;
            if (p == 0) {
                root = i + 1;
            }

            tree.get(p).add(i + 1);

        }
        dfs(root, a, b, c, w, tree);
        int ans = Math.max(b[root], a[root]);
        out.write(String.valueOf(ans));
        out.close();

    }
    public static void dfs(int x, int[] a, int[] b, int[] c, int[] w, ArrayList<ArrayList<Integer>> tree) {
        a[x] = w[x];
        b[x] = 0;
        for (int i = 0; i < tree.get(x).size(); i++) {
            int l = tree.get(x).get(i);
            dfs(l, a, b, c, w, tree);
            b[x] += a[l];
            a[x] += b[l];
        }
        a[x] = Math.max(a[x], b[x]);

    }
}
