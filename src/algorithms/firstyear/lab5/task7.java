package algorithms.firstyear.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class task7 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();
    boolean[] used;
    int[] tin;
    int[] fup;
    int timer = 0;
    int[] ans;
    int k = 0;
    ArrayList<Integer> num[];
    boolean bridge[];

    public static void main(String[] arg) {
        new task7().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        used = new boolean[n];
        ans = new int[m];
        tin = new int[n];
        fup = new int[n];
        num = new ArrayList[n];
        bridge = new boolean[m];
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
            num[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int s = in.nextInt() - 1;
            g.get(f).add(s);
            g.get(s).add(f);
            num[f].add(i);
            num[s].add(i);
        }
        for (int i = 0; i < n; ++i)
            used[i] = false;
        for (int i = 0; i < n; i++) {
            if (!used[i])
                dfs(i, -1);
        }
        out.write(String.valueOf(k) + "\n");
        if (k != 0) {
            for (int i = 0; i < m; i++) {
                if (bridge[i]) {
                    out.write(String.valueOf(i + 1) + " ");
                }
            }
        }
        out.close();


    }

    void dfs(int v, int p) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        for (int i = 0; i < g.get(v).size(); i++) {
            int to = g.get(v).get(i);
            int find = num[v].get(i);
            if (to == p) continue;
            if (used[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    k++;
                    bridge[find] = true;
                }

            }
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("in"));
            out = new PrintWriter(new File("out"));

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
