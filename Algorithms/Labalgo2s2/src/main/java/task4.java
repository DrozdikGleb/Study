
/**
 * Created by Drozdov Gleb on 13.05.2017.
 */

import java.util.*;
import java.io.*;

public class task4 {
    public class Compare implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            if (a.getEnd() < b.getEnd()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    FastScanner in;
    PrintWriter out;
    boolean[] used;
    ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
    int[] d;
    int INF=300000000;

    class Pair {
        int end;
        int w;

        Pair(int end, int w) {
            this.end = end;
            this.w = w;
        }

        public int getEnd() {
            return end;
        }

        public int getW() {
            return w;
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m=in.nextInt();
        int s=0;
        PriorityQueue<Pair> q = new PriorityQueue<>(new Compare());
        q.add(new Pair(0,s));
        used = new boolean[n];
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = INF;
            adj.add(new ArrayList<Pair>());
        }
        d[s] = 0;
        for (int i = 0; i < m; i++) {
            int a=in.nextInt()-1;
            int b =in.nextInt()-1;
            int w=in.nextInt();
            adj.get(a).add(new Pair(b,w));
            adj.get(b).add(new Pair(a,w));

        }
        while (!q.isEmpty()) {
            int v = q.peek().getW();
            int cur = -q.peek().getEnd();
            q.poll();
            if (cur > d[v])  continue;
            for (int j = 0; j < adj.get(v).size(); j++) {
                int to = adj.get(v).get(j).getEnd();
                int l = adj.get(v).get(j).getW();
                if (d[v] + l < d[to]) {
                    d[to] = d[v] + l;
                    q.add(new Pair(-d[to], to));
                }
            }

        }
        for (int i = 0; i <n ; i++) {
            out.write(String.valueOf(d[i])+" ");
        }

        out.close();

    }



    public void run() {
        try {
            in = new FastScanner(new File("pathbgep.in"));
            out = new PrintWriter(new File("pathbgep.out"));

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
        new task4().run();
    }
}


