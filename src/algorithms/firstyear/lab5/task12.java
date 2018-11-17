package algorithms.firstyear.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Drozdov Gleb on 23.04.2017.
 */


public class task12 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();
    ArrayList<Integer> order = new ArrayList<>();
    int k = 0;
    boolean check = false;
    boolean[] used;

    public static void main(String[] arg) {
        new task12().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
        }
        used = new boolean[n];
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int s = in.nextInt() - 1;
            g.get(f).add(s);
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs(i);
            }
        }
        dfs2(order.get(n - 1), n);

        if (check) {
            out.write("YES");
        } else {
            out.write("NO");
        }

        out.close();

    }

    public void dfs(int v) {
        used[v] = true;
        for (int i = 0; i < g.get(v).size(); i++) {
            if (!used[g.get(v).get(i)]) {
                dfs(g.get(v).get(i));
            }
        }
        order.add(v);
    }

    public void dfs2(int v, int n) {
        if (check) return;
        if (k == n - 1 && order.get(0) == v) {
            check = true;
            return;
        }
        for (int i = 0; i < g.get(v).size(); i++) {
            int u = g.get(v).get(i);
            if (order.get(n - 1 - k) == v && order.get(n - 1 - k - 1) == u) {
                k++;
                dfs2(u, n);
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("hamiltonian.in"));
            out = new PrintWriter(new File("hamiltonian.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

