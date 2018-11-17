package algorithms.secondyear.flowsandmatchings;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Matching {
    int n;
    int m;
    boolean[] used;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    int[] matching;
    int size = 0;
    double[][] weights;
    private FastScanner in;

    public static void main(String[] arg) {
        new Matching().run();
    }

    private boolean dfs(int v) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        for (int i = 0; i < graf.get(v).size(); i++) {
            int to = graf.get(v).get(i);
            if (matching[to] == -1 || dfs(matching[to])) {
                matching[to] = v;
                return true;
            }
        }
        return false;
    }

    private void initialize() {
        n = in.nextInt();
        m = in.nextInt();
        matching = new int[m];
        Arrays.fill(matching, -1);
        used = new boolean[n];
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int cur = in.nextInt();
            while (cur != 0) {
                graf.get(i).add(cur - 1);
                cur = in.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            used = new boolean[n];
            size += (dfs(i) ? 1 : 0);
        }
    }

    public void solve() throws IOException {
        initialize();
        System.out.println(size);
        for (int i = 0; i < m; ++i)
            if (matching[i] != -1)
                System.out.println((matching[i] + 1) + " " + (i + 1));
    }

    public void run() {
        try {
            in = new FastScanner();

            solve();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {

            br = new BufferedReader(new InputStreamReader(System.in));

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