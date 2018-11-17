package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DynamicLCA {

    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    int[] p;
    int[] d;
    boolean[] used;
    int[][] dp;
    int log;

    public static void main(String[] arg) {
        new DynamicLCA().run();
    }

    public void countLog(int n) {
        log = 0;
        while ((1 << log) <= n) log++;
    }

    public void dfs(int k, int pr) {
        d[k] = d[pr] + 1;
        for (int i = 0; i < graf.get(k).size(); i++) {
            int to = graf.get(k).get(i);
            if (to != pr) {
                p[to] = k;
                dfs(to, k);
            }
        }
    }

    /*
    * number++;
            parent[number] = v;
            edges[v].add(number);
            depth[number] = depth[v] + 1;

            dp[number][0] = v;
            for (int j = 1; j < logn; j++) {
                dp[number][j] = dp[dp[number][j - 1]][j - 1];
            }*/
    public int lca(int v, int u) {
        if (d[v] > d[u]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = log - 1; i >= 0; i--) {
            int deg;
            if (i == 0) {
                deg = 1;
            } else {
                deg = 2 << (i - 1);
            }
            if (d[u] - d[v] >= deg) {
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
        while (n != 0) {
            p = new int[n + 1];
            p[0] = 0;
            d = new int[n + 1];
            countLog(n);
            dp = new int[n + 1][log];
            for (int i = 0; i < n + 1; i++) {
                graf.add(new ArrayList<>());
            }
            for (int i = 1; i < n; i++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                graf.get(a).add(b);
                graf.get(b).add(a);
            }
            dfs(0, 0);
            for (int i = 0; i < n; i++) {
                dp[i][0] = p[i];
            }
            for (int i = 1; i < log; i++) {
                for (int j = 1; j < n; j++) {
                    dp[j][i] = dp[dp[j][i - 1]][i - 1];
                }
            }
            int m = in.nextInt();
            int currentRoot = 1;
            for (int i = 0; i < m; i++) {
                String str = in.next();
                if (str.equals("!")) {
                    currentRoot = in.nextInt();
                }
                if (str.equals("?")) {
                    int a = in.nextInt();
                    int b = in.nextInt();
                    int LCA = lca(a - 1, b - 1);
                    int fLCA = lca(a - 1, currentRoot - 1);
                    int sLCA = lca(currentRoot - 1, b - 1);
                    int answer = LCA - 1;
                    if (d[fLCA - 1] > d[answer]) {
                        answer = fLCA - 1;
                    }
                    if (d[sLCA - 1] > d[answer]) {
                        answer = sLCA - 1;
                    }
                    out.write(String.valueOf(answer + 1) + "\n");
                }

            }
            graf.clear();
            n = in.nextInt();
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("dynamic.in"));
            out = new PrintWriter(new File("dynamic.out"));

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