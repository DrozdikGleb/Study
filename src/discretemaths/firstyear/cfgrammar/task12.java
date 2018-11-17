package discretemaths.firstyear.cfgrammar; /**
 * Created by Drozdov Gleb on 31.05.2017.
 */

import java.util.*;
import java.io.*;

public class task12 {
    FastScanner in;
    PrintWriter out;
    int[][] map1;
    int[][] map2;
    boolean[] dopusk;
    boolean[] dopusk2;
    boolean[] used1;
    boolean[] used2;
    PriorityQueue<Pair> Q = new PriorityQueue<Pair>();


    class Pair implements Comparable<Pair> {
        int second;
        int str;

        Pair(int second, int str) {
            this.second = second;
            this.str = str;
        }

        public int getFirst() {
            return second;
        }

        public int getSecond() {
            return str;
        }

        @Override
        public int compareTo(Pair o) {
            return 0;
        }
    }

    boolean bfs() {
        Q.add(new Pair(0, 0));
        while (!Q.isEmpty()) {
            int u = Q.peek().getFirst();
            int v = Q.peek().getSecond();
            Q.poll();
            if ((dopusk[u] && (!dopusk2[v])) || ((!dopusk[u]) && (dopusk2[v]))) {
                return false;
            }
            used1[u] = true;
            used2[v] = true;
            for (int i = 0; i < 26; i++) {
                if ((!used1[map1[u][i]] || !used2[map2[v][i]])) {
                    Q.add(new Pair(map1[u][i], map2[v][i]));
                }

            }
        }
        return true;

    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        dopusk = new boolean[n + 1];
        map1 = new int[n+1][26];
        used1 = new boolean[n + 1];
        for (int i = 0; i < k; i++) {
            dopusk[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < 26; j++) {
                map1[i][j] = n;
            }
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int s = (int) in.next().charAt(0) - 97;
            map1[a][s] = b;
        }
        int n2 = in.nextInt();
        int m2 = in.nextInt();
        int k2 = in.nextInt();
        dopusk2 = new boolean[n2 + 1];
        used2 = new boolean[n2 + 1];
        for (int i = 0; i < k2; i++) {
            dopusk2[in.nextInt() - 1] = true;
        }
        map2 = new int[n2+1][26];
        for (int i = 0; i < n2+1; i++) {
            for (int j = 0; j < 26; j++) {
                map2[i][j] = n2;
            }
        }
        for (int i = 0; i < m2; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int s = (int) in.next().charAt(0) - 97;
            map2[a][s] = b;
        }
        if (bfs()) {
            out.write("YES");
        } else {
            out.write("NO");
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("equivalence.in"));
            out = new PrintWriter(new File("equivalence.out"));

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
        new task12().run();
    }
}

