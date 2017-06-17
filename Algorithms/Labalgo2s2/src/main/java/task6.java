
/**
 * Created by Drozdov Gleb on 13.05.2017.
 */

import java.util.*;
import java.io.*;

public class task6 {
    FastScanner in;
    PrintWriter out;
    int empty = 1000000000;
    long INF = 100000000;

    class Pair {
        int start;
        int end;
        long w;

        Pair(int start, int end, long w) {
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

    public void solve() throws IOException {
        int n = in.nextInt();
        long[] d = new long[n];
        int[] p = new int[n];
        ArrayList<Pair> graf = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            d[i] = INF;
            p[i] = -1;
        }
        d[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long b = in.nextLong();
                if ((b != empty) ) {
                    Pair elem = new Pair(i, j, b);
                    graf.add(elem);
                }
            }
        }

        int m = graf.size();
        int x = -1;
        for (int i = 0; i < n; i++) {
            x = -1;
            for (int j = 0; j < m; j++) {
                   if (d[graf.get(j).getEnd()] > d[graf.get(j).getStart()] + graf.get(j).getW()) {
                        d[graf.get(j).getEnd()] = Math.max(d[graf.get(j).getStart()] + graf.get(j).getW(), -INF);
                        p[graf.get(j).getEnd()] = graf.get(j).getStart();
                        x = graf.get(j).getEnd();
                    }
            }
        }
        if (x != -1) {
            out.write("YES" + "\n");
            int y = x;
            for (int i=0; i<n; i++)
                y = p[y];
            ArrayList<Integer> ans = new ArrayList<>();
            for (int cur=y; ;cur=p[cur]) {
                ans.add(cur);
                if (cur == y && ans.size() > 1)  break;
            }

            Collections.reverse(ans);
            out.write(String.valueOf(ans.size())+"\n");
            for (int i = 0; i <ans.size() ; i++) {
                out.write(String.valueOf(ans.get(i)+1)+" ");
            }

        } else {
            out.write("NO");
        }

        out.close();

    }


    public void run() {
        try {
            in = new FastScanner(new File("negcycle.in"));
            out = new PrintWriter(new File("negcycle.out"));

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
        new task6().run();
    }
}



