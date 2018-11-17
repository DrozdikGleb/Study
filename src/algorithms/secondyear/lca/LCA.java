package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    int[] p;
    int[] d;
    boolean[] used;
    int[][] dp;
    int log;

    public static void main(String[] arg) {
        new LCA().run();
    }

    public void countLog(int n) {
        while ((1 << log) <= n) log++;
    }

    public void dfs(int k, int pr) {
        d[k] = d[pr] + 1;
        for (int i = 0; i < graf.get(k).size(); i++) {
            int to = graf.get(k).get(i);
            if (to != pr) {
                dfs(to, k);
            }
        }
    }

    public int lca(int v, int u) {
        if (d[v] > d[u]) {
            int temp = u;
            u = v;
            v = temp;
        }
        for (int i = log - 1; i >= 0; i--) {
            if (d[u] - d[v] >= Math.pow(2, i)) {
                u = dp[u][i];
            }
        }
        if (u == v) {
            return v + 1;
        }
        for (int i = log - 1; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return p[v] + 1;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        p = new int[n];
        p[0] = 0;
        d = new int[n];
        used = new boolean[n];
        countLog(n);
        dp = new int[n][log];
        for (int i = 0; i < n + 1; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int vert = in.nextInt();
            graf.get(vert - 1).add(i);
            p[i] = vert - 1;
        }
        dfs(0, 0);
        for (int i = 0; i < n; i++) {
            dp[i][0] = p[i];
        }
        for (int i = 1; i < log; i++) {
            for (int j = 0; j < n; j++) {
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
            }
        }

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            out.write(lca(in.nextInt() - 1, in.nextInt() - 1) + "\n");
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("lca.in"));
            out = new PrintWriter(new File("lca.out"));

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