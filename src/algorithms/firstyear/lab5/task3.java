package algorithms.firstyear.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class task3 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    int color[];
    boolean cycl = false;
    ArrayList<Integer> topSort = new ArrayList<Integer>();

    public static void main(String[] arg) {
        new task3().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        color = new int[n];
        for (int i = 0; i < n; i++) {
            color[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int s = in.nextInt() - 1;
            adj.get(f).add(s);
        }
        for (int i = 0; i < n; i++) {
            topologicalSort(i);
        }
        if (cycl) {
            out.write(String.valueOf(-1));
        } else {
            Collections.reverse(topSort);
            for (int i = 0; i < n; i++) {
                out.write(String.valueOf(topSort.get(i) + 1) + " ");
            }

        }
    }

    void topologicalSort(int v) {
        if (color[v] == 2) {
            return;
        }
        if (cycl) {
            return;
        }
        if (color[v] == 1) {
            cycl = true;
            return;
        }
        color[v] = 1;
        for (int i = 0; i < adj.get(v).size(); i++) {
            int w = adj.get(v).get(i);
            topologicalSort(w);
            if (cycl) {
                return;
            }
        }
        color[v] = 2;
        topSort.add(v);

    }

    public void run() {
        try {
            in = new FastScanner(new File("topsort.in"));
            out = new PrintWriter(new File("topsort.out"));

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
