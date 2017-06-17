/**
 * Created by Drozdov Gleb on 15.04.2017.
 */

import java.util.*;
import java.io.*;

public class task4 {
    FastScanner in;
    PrintWriter out;

    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    int color[];
    boolean cycl = false;
    ArrayList<Integer> topSort = new ArrayList<Integer>();
    int[] pred;


    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        color = new int[n];
        pred = new int[n];
        for (int i = 0; i < n; i++) {
            color[i] = 0;
            pred[i]=-1;
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
            dfsCycle(i);
        }
        if (cycl) {
            out.write("YES" + "\n");
            for (int i = 0; i < topSort.size(); i++) {
                out.write(String.valueOf(topSort.get(i)+1) + " ");
            }
        } else {
            out.write("NO");
        }
    }

    void dfsCycle(int v) {
        if (color[v] == 2) {
            return;
        }
        if (cycl) {
            return;
        }
        if (color[v] == 1) {
            cycl = true;
            topSort.add(v);
            int ver = pred[v];
            while (ver!=v)
             {
                 topSort.add(ver);
                 ver=pred[ver];
            }
            Collections.reverse(topSort);
            return;

        }
        color[v] = 1;
        for (int i = 0; i < adj.get(v).size(); i++) {
            int w = adj.get(v).get(i);
            pred[w]=v;
            dfsCycle(w);
            if (cycl) {
                return;
            }
        }
        color[v] = 2;

    }

    public void run() {
        try {
            in = new FastScanner(new File("cycle.in"));
            out = new PrintWriter(new File("cycle.out"));

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
        new task4().run();
    }
}

