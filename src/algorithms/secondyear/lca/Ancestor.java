package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ancestor {
    FastScanner in;
    PrintWriter out;

    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    int[] tin;
    int[] tout;
    int[] p;
    int[] d;
    boolean[] used;
    int[][] dp;
    int timer = 0;
    int log = 1;

    public static void main(String[] arg) {
        new Ancestor().run();
    }

    public void countLog(int n) {
        while ((1 << log) <= n) log++;
    }

    public void dfs(int k, int p) {
        timer++;
        tin[k] = timer;
        dp[k][0] = p;
        for (int i = 1; i <= log; i++)
            dp[k][i] = dp[dp[k][i - 1]][i - 1];
        for (int i = 0; i < graf.get(k).size(); i++) {
            int to = graf.get(k).get(i);
            if (to != p) {
                dfs(to, k);
            }
        }
        timer++;
        tout[k] = timer;
    }

    public boolean ancestor(int v, int u) {
        if (tin[v] <= tin[u] && tout[u] <= tout[v]) {
            return true;
        }
        return false;
    }

    public int lca(int v, int u) {
        if (ancestor(v, u)) return v;
        if (ancestor(u, v)) return u;
        for (int i = log; i >= 0; i--) {
            if (!ancestor(dp[v][i], u))
                v = dp[v][i];
        }
        return dp[v][0];
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        tin = new int[n + 1];
        tout = new int[n + 1];
        countLog(n);
        //p[0] = 0;
        d = new int[n + 1];
        used = new boolean[n];
        dp = new int[n + 1][log + 1];
        for (int i = 0; i < n + 1; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            int vert = in.nextInt();
            graf.get(vert).add(i);

        }
        dfs(0, 0);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (a == (lca(a, b))) {
                out.write("1\n");
            } else {
                out.write("0\n");
            }
        }

        out.close();

    }

    public void run() {
        try {
            in = new FastScanner(new File("ancestor.in"));
            out = new PrintWriter(new File("ancestor.out"));

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