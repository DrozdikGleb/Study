package discretemaths.firstyear.cfgrammar; /**
 * Created by Drozdov Gleb on 02.05.2017.
 */

import java.util.*;
import java.io.*;

public class task11 {
    FastScanner in;
    PrintWriter out;
    int[][] map1;
    int[][] map2;
    boolean[] dopusk;
    boolean[] dopusk2;
    boolean[] used;


    class Pair {
        int second;
        int str;

        Pair(int second, int str) {
            this.second = second;
            this.str = str;
        }

        public int getChar() {
            return second;
        }

        public int getSecond() {
            return str;
        }
    }

    boolean dfs(int v, int u) {
        boolean ans = true;
        if (dopusk[v] != dopusk2[u]) {
            return false;
        }
        used[v] = true;
        for (int i = 0; i < 26; i++) {
            if (map1[v][i] == -1 && map2[u][i] == -1) {
                continue;
            }
            if (map1[v][i] != -1 && map2[u][i] != -1) {
                if (!used[map1[v][i]]) {
                    ans = ans & (dfs(map1[v][i], map2[u][i]));
                }
            } else {
                return false;
            }
        }
        return ans;


    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        dopusk = new boolean[n];
        map1 = new int[n][26];
        for (int i = 0; i < k; i++) {
            dopusk[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                map1[i][j] = -1;
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
        dopusk2 = new boolean[n2];
        used = new boolean[n];
        for (int i = 0; i < k2; i++) {
            dopusk2[in.nextInt() - 1] = true;
        }
        map2 = new int[n2][26];
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < 26; j++) {
                map2[i][j] = -1;
            }
        }
        for (int i = 0; i < m2; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int s = (int) in.next().charAt(0) - 97;
            map2[a][s] = b;
        }
        if (dfs(0, 0)) {
            out.write("YES");
        } else {
            out.write("NO");
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("isomorphism.in"));
            out = new PrintWriter(new File("isomorphism.out"));

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
        new task11().run();
    }
}
