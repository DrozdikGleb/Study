
/**
 * Created by Drozdov Gleb on 13.05.2017.
 */

import java.util.*;
import java.io.*;

public class task2 {
    FastScanner in;
    PrintWriter out;
    boolean[] used;
    ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
    long[] d;

    class Pair {
        int end;
        long w;

        Pair(int end, long w) {
            this.end = end;
            this.w = w;
        }

        public int getEnd() {
            return end;
        }

        public long getW() {
            return w;
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;
        used = new boolean[n];
        d = new long[n];
        for (int i = 0; i < n; i++) {
            d[i] = Long.MAX_VALUE;
            adj.add(new ArrayList<Pair>());
        }
        d[s] = 0;
        for (int i = 0; i < n; i++) {
            int a = i;
            for (int j = 0; j < n; j++) {
                long b = in.nextLong();
                if ((b != 0) && (b != -1)) {
                    Pair elem = new Pair(j, b);
                    adj.get(a).add(elem);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int v = -1;
            for (int j = 0; j < n; j++) {
                if ((!used[j]) && ((v == -1) || (d[j] < d[v]))) {
                    v = j;
                }
            }
            if (d[v] == Long.MAX_VALUE) {
                break;
            }
            used[v] = true;
            for (int j = 0; j < adj.get(v).size(); j++) {
                int to = adj.get(v).get(j).getEnd();
                long l = adj.get(v).get(j).getW();
                if (d[v] + l < d[to]) {
                    d[to] = d[v] + l;
                }
            }
        }
        if (d[f] == Long.MAX_VALUE) {
            out.write("-1");
        } else {
            out.write(String.valueOf(d[f]));
        }
        out.close();


    }


    public void run() {
        try {
            in = new FastScanner(new File("pathmgep.in"));
            out = new PrintWriter(new File("pathmgep.out"));

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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] arg) {
        new task2().run();
    }
}

