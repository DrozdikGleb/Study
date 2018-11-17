package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CheapEdge {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Edge>> graf = new ArrayList<>();
    int[] p;
    int[] d;
    int[][] dp;
    int[][] dpW;
    int log;
    int[] w;

    public static void main(String[] arg) {
        new CheapEdge().run();
    }

    public void countLog(int n) {
        while ((1 << log) <= n) log++;
    }

    public void dfs(int k, int pr) {
        d[k] = d[pr] + 1;
        for (int i = 0; i < graf.get(k).size(); i++) {
            int to = graf.get(k).get(i).to;
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
        int min = Integer.MAX_VALUE;
        for (int i = log - 1; i >= 0; i--) {
            if (d[u] - d[v] >= Math.pow(2, i)) {
                min = Math.min(min, dpW[u][i]);
                u = dp[u][i];
            }
        }
        if (u == v) {
            return min;
        }
        for (int i = log - 1; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                min = Math.min(min, dpW[v][i]);
                v = dp[v][i];
                min = Math.min(min, dpW[u][i]);
                u = dp[u][i];
            }

        }
        min = Math.min(min, Math.min(w[u], w[v]));
        return min;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        p = new int[n];
        w = new int[n];
        p[0] = 0;
        d = new int[n];
        countLog(n);
        dp = new int[n][log];
        dpW = new int[n][log];
        dpW[0][0] = Integer.MAX_VALUE;
        for (int i = 0; i < n + 1; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int vert = in.nextInt();
            int weght = in.nextInt();
            graf.get(vert - 1).add(new Edge(i, weght));
            p[i] = vert - 1;
            w[i] = weght;
        }
        dfs(0, 0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = p[i];
            dpW[i][0] = w[i];
        }
        for (int i = 1; i < log; i++) {
            for (int j = 1; j < n; j++) {
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
                dpW[j][i] = Math.min(dpW[dp[j][i - 1]][i - 1], dpW[j][i - 1]);
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
            in = new FastScanner(new File("minonpath.in"));
            out = new PrintWriter(new File("minonpath.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
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