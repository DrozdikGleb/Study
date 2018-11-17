package algorithms.firstyear.lab6;
/**
 * Created by Drozdov Gleb on 14.05.2017.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class task5 {
    FastScanner in;
    PrintWriter out;
    int empty = 1000000000;
    boolean[] used;
    long INF = (long) 3e18;
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();

    public static void main(String[] arg) {
        new task5().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        long[] d = new long[n];
        int[] p = new int[n];
        used = new boolean[n];
        ArrayList<Integer> change = new ArrayList<>();
        ArrayList<Triple> graf = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            d[i] = INF;
            p[i] = -1;
            g.add(new ArrayList<Integer>());
        }
        d[s] = 0;

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            long w = in.nextLong();
            g.get(a).add(b);
            graf.add(new Triple(a, b, w));
        }


        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (d[graf.get(j).getStart()] < INF) {
                    if (d[graf.get(j).getEnd()] > d[graf.get(j).getStart()] + graf.get(j).getW()) {
                        d[graf.get(j).getEnd()] = Math.max(d[graf.get(j).getStart()] + graf.get(j).getW(), -INF);
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (d[graf.get(i).getStart()] < INF) {
                if (d[graf.get(i).getEnd()] > d[graf.get(i).getStart()] + graf.get(i).getW()) {
                    change.add(graf.get(i).getEnd());
                }
            }
        }
        for (int i = 0; i < change.size(); i++) {
            dfs(change.get(i));
        }

        for (int i = 0; i < n; i++) {
            if (used[i]) {
                out.write("-" + "\n");
            } else {
                if (d[i] == INF) {
                    out.write("*" + "\n");
                } else {
                    out.write(String.valueOf(d[i]) + "\n");
                }
            }
        }
        out.close();

    }

    public void dfs(int v) {
        used[v] = true;
        for (int i = 0; i < g.get(v).size(); i++) {
            if (!used[g.get(v).get(i)]) {
                dfs(g.get(v).get(i));
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("path.in"));
            out = new PrintWriter(new File("path.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }

    class Triple {
        int start;
        int end;
        long w;

        Triple(int start, int end, long w) {
            this.start = start;
            this.end = end;
            this.w = w;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public long getW() {
            return w;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}




