package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SumShortPath {
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
        new SumShortPath().run();
    }

    public void countLog(int n) {
        while ((1 << log) <= n) log++;
    }

    public void dfs(int k, int pr) {
        d[k] = d[pr] + 1;
        for (int i = 0; i < graf.get(k).size(); i++) {
            int to = graf.get(k).get(i).to;
            int weight = graf.get(k).get(i).weight;
            if (to != pr) {
                dfs(to, k);
                p[to] = k;
                dpW[to][0] = weight;
            }
        }
    }

    public int lca(int v, int u) {
        if (d[v] > d[u]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int min = 0;
        for (int i = log - 1; i >= 0; i--) {
            int deg;
            if (i == 0) {
                deg = 1;
            } else {
                deg = 2 << (i - 1);
            }
            if (d[u] - d[v] >= deg) {
                min = min + dpW[u][i];
                u = dp[u][i];
            }
        }
        if (u == v) {
            return min;
        }
        for (int i = log - 1; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                min = min + dpW[v][i];
                v = dp[v][i];
                min = min + dpW[u][i];
                u = dp[u][i];
            }

        }
        min = min + dpW[u][0] + dpW[v][0];
        return min;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        p = new int[n];
        p[0] = 0;
        d = new int[n];
        countLog(n);
        dp = new int[n][log];
        dpW = new int[n][log];
        for (int i = 0; i < n + 1; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int vert = in.nextInt();
            int vert2 = in.nextInt();
            int weght = in.nextInt();
            graf.get(vert).add(new Edge(vert2, weght));
            graf.get(vert2).add(new Edge(vert, weght));
        }
        d[0] = -1;
        dfs(0, 0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = p[i];
        }
        for (int i = 1; i < log; i++) {
            for (int j = 0; j < n; j++) {
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
                dpW[j][i] = dpW[dp[j][i - 1]][i - 1] + dpW[j][i - 1];
            }
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            out.write(lca(in.nextInt(), in.nextInt()) + "\n");
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("tree.in"));
            out = new PrintWriter(new File("tree.out"));

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