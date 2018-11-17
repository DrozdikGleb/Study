package discretemaths.firstyear.automata; /**
 * Created by Drozdov Gleb on 04.05.2017.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class task4 {
    FastScanner in;
    PrintWriter out;
    int INF = 1000000007;
    ArrayList<Pair> graf = new ArrayList<>();
    int l;
    boolean[] dopusk;
    long count;

    public static void main(String[] arg) {
        new task4().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        l = in.nextInt();
        count = 0;
        dopusk = new boolean[n];
        for (int i = 0; i < k; i++) {
            dopusk[in.nextInt() - 1] = true;
        }
        int[][] d = new int[n + 1][l + 1];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            char s = in.next().charAt(0);
            Pair elem = new Pair(a, b);
            graf.add(elem);
        }
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < l + 1; j++) {
                d[i][j] = 0;
            }
        }
        d[1][0] = 1;
        int ans = 0;
        for (int i = 1; i <= l; i++)
            for (int j = 0; j < m; j++)
                d[graf.get(j).getSecond()][i] = (d[graf.get(j).getSecond()][i] + d[graf.get(j).getFirst()][i - 1]) % INF;

        for (int i = 0; i < n; i++) {
            if (dopusk[i]) {
                ans = (ans + d[i + 1][l]) % INF;
            }
        }
        out.write(String.valueOf(ans));
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem4.in"));
            out = new PrintWriter(new File("problem4.out"));

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
            this.second = second;
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public int getFirst() {
            return first;
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

