package algorithms.firstyear.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class task1 {
    FastScanner in;
    PrintWriter out;
    boolean[] used;
    Queue<Integer> queue = new LinkedList<Integer>();
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    int[] dist;

    public static void main(String[] arg) {
        new task1().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        used = new boolean[n];
        dist = new int[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int s = in.nextInt() - 1;
            adj.get(f).add(s);
            adj.get(s).add(f);
        }
        bfs(0);
        for (int i = 0; i < n; i++) {
            out.write(String.valueOf(dist[i]) + " ");
        }
        out.close();


    }

    public void bfs(int v) {
        dist[v] = 0;
        queue.add(v);
        used[v] = true;
        while (!queue.isEmpty()) {
            v = queue.poll();
            for (int i = 0; i < adj.get(v).size(); ++i) {
                int w = adj.get(v).get(i);
                if (used[w]) {
                    continue;
                }
                dist[w] = dist[v] + 1;
                queue.add(w);
                used[w] = true;
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("pathbge1.in"));
            out = new PrintWriter(new File("pathbge1.out"));

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
