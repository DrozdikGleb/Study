package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA_3 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    int[] d;
    int[][] dp;
    int log;
    int[] dsu;
    boolean[] isRoot;

    public static void main(String[] arg) {
        new LCA_3().run();
    }

    public void countLog(int n) {
        while ((1 << log) <= n) log++;
    }

    public void dfs(int k, int pr) {
        if (k == 0 && pr == 0) {
            d[k] = 0;
        } else {
            d[k] = d[pr] + 1;
        }
        dp[k][0] = pr;
        for (int i = 1; i < log; i++) {
            dp[k][i] = dp[dp[k][i - 1]][i - 1];
        }
        for (int i = 0; i < graf.get(k).size(); i++) {
            int to = graf.get(k).get(i);
            if (to != pr) {
                dfs(to, k);
            }
        }
    }

    public int whatDsu(int v) {
        if (v == dsu[v]) {
            return v;
        }
        dsu[v] = whatDsu(dsu[v]);
        return dsu[v];
    }

    public void unionDsu(int u, int v) {
        u = whatDsu(u);
        v = whatDsu(v);
        if (u != v) {
            dsu[v] = u;
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
        return dp[u][0] + 1;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        d = new int[n];
        countLog(n);
        isRoot = new boolean[n];
        dp = new int[n][log];
        dsu = new int[n];
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            dsu[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int vertex = in.nextInt();
            if (vertex != 0) {
                graf.get(vertex - 1).add(i);
                unionDsu(vertex - 1, i);
            } else {
                isRoot[i] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (isRoot[i]) {
                dfs(i, i);
            }
        }
        int m = in.nextInt();
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int number = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int u = ((x - 1 + ans) % n) + 1;
            int v = ((y - 1 + ans) % n) + 1;
            if (number == 1) {
                u = whatDsu(u - 1);
                unionDsu(v - 1, u);
                graf.get(v - 1).add(u);
                dfs(u, v - 1);
            } else {
                if (whatDsu(u - 1) == whatDsu(v - 1)) {
                    ans = lca(u - 1, v - 1);
                } else {
                    ans = 0;
                }
                out.write(String.valueOf(ans) + "\n");
            }
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("lca3.in"));
            out = new PrintWriter(new File("lca3.out"));

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
