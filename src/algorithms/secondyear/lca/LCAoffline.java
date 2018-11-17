package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCAoffline {
    class Word {
        int a;
        int b;

        Word(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    FastScanner in;
    PrintWriter out;
    ArrayList<Word> get = new ArrayList<>();
    ArrayList<Word> add = new ArrayList<>();
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    int[] p;
    int[] d;
    boolean[] used;
    int[][] dp;
    int log;

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
        for (int i = 0; i < n; i++) {
            String word = in.next();
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            if (word.equals("ADD")) {
                add.add(new Word(a, b));
            } else {
                get.add(new Word(a, b));
            }
        }
        n = add.size() + 1;
        p = new int[n + 1];
        p[0] = 0;
        d = new int[n + 1];
        countLog(n);
        dp = new int[n + 1][log];
        for (int i = 0; i < n + 1; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int vert = add.get(i - 1).a;
            graf.get(vert).add(add.get(i - 1).b);
            p[add.get(i - 1).b] = vert;
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
        for (int i = 0; i < get.size(); i++) {
            out.write(lca(get.get(i).a, get.get(i).b) + "\n");
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

    public static void main(String[] arg) {
        new LCAoffline().run();
    }
}