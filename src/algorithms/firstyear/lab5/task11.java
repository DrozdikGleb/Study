package algorithms.firstyear.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class task11 {
    FastScanner in;
    PrintWriter out;
    boolean[] used;
    ArrayList<ArrayList<Pair>> g = new ArrayList<>();
    ArrayList<Integer> order = new ArrayList<>();
    int[] component;
    boolean check = false;

    public static void main(String[] arg) {
        new task11().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        int t = in.nextInt() - 1;
        used = new boolean[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int second = in.nextInt() - 1;
            int weight = in.nextInt();
            Pair elem = new Pair(second, weight);
            g.get(f).add(elem);
        }
        d[s] = 0;
        dfs(s, t);
        if (!check) {
            out.write("Unreachable");
        } else {
            int size = order.size();
            for (int i = 0; i < size; i++) {
                int v = order.get(order.size() - 1);
                order.remove(order.size() - 1);
                for (int j = 0; j < g.get(v).size(); j++) {
                    int to = g.get(v).get(j).getSecond();
                    int w = g.get(v).get(j).getWeight();
                    d[to] = Math.min(d[to], d[v] + w);
                }
            }


            out.write(String.valueOf(d[t]) + "\n");
        }
        out.close();
    }

    public void dfs(int v, int t) {
        if (v == t) {
            check = true;
        }
        used[v] = true;
        for (int i = 0; i < g.get(v).size(); i++) {
            if (!used[g.get(v).get(i).getSecond()]) {
                dfs(g.get(v).get(i).getSecond(), t);
            }
        }
        order.add(v);
    }


    public void run() {
        try {
            in = new FastScanner(new File("shortpath.in"));
            out = new PrintWriter(new File("shortpath.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Pair {
        int second;
        int weight;

        Pair(int second, int weight) {
            this.second = second;
            this.weight = weight;
        }

        public int getSecond() {
            return second;
        }

        public int getWeight() {
            return weight;
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


