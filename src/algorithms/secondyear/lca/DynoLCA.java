package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DynoLCA {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    int[] d;
    int[][] dp;
    int log;
    int unique_number = 0;
    ArrayList<Dyno> p = new ArrayList<>();

    public static void main(String[] arg) {
        new DynoLCA().run();
    }

    public void countLog(int n) {
        while ((1 << log) <= n) log++;
    }

    public void addDyno(int v) {
        unique_number++;
        p.set(unique_number, new Dyno(true, unique_number, p.get(v).v));
        d[unique_number] = d[v] + 1;
        dp[unique_number][0] = v;
        for (int i = 1; i < log; i++) {
            dp[unique_number][i] = dp[dp[unique_number][i - 1]][i - 1];
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
            return getParent(v);
        }
        for (int i = log - 1; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return getParent(p.get(v).parent);
    }

    public int getParent(int v) {
        ArrayList<Integer> dieDyno = new ArrayList<>();
        while (!p.get(v).live) {
            dieDyno.add(v);
            v = p.get(v).parent;
        }
        for (int i = 0; i < dieDyno.size(); i++) {
            p.set(dieDyno.get(i), new Dyno(false, dieDyno.get(i), v));
        }
        return v;
    }

    public void solve() throws IOException {
        int n = 200_001;
        p = new ArrayList<>();
        for (int i = 0; i < 200_001; i++) {
            p.add(new Dyno(false, -1, -1));
        }
        p.set(0, new Dyno(true, 0, 0));
        d = new int[n];
        d[0] = 0;
        countLog(n);
        dp = new int[n][log];
        for (int i = 0; i < n + 1; i++) {
            graf.add(new ArrayList<>());
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            String str = in.next();
            switch (str) {
                case "+":
                    int addChild = in.nextInt();
                    addDyno(addChild - 1);
                    break;
                case "-":
                    int dieDyno = in.nextInt() - 1;
                    p.set(dieDyno, new Dyno(false, dieDyno, p.get(dieDyno).parent));
                    break;
                case "?":
                    out.write((lca(in.nextInt() - 1, in.nextInt() - 1) + 1) + "\n");
                    break;
            }
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("carno.in"));
            out = new PrintWriter(new File("carno.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Dyno {
        int parent;
        int v;
        boolean live;

        Dyno(boolean live, int v, int parent) {
            this.live = live;
            this.parent = parent;
            this.v = v;
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