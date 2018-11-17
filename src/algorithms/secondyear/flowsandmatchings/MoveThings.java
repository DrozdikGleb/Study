package algorithms.secondyear.flowsandmatchings;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MoveThings {
    int n;
    int m;
    double INF = Integer.MAX_VALUE;
    double EPS = 0.0001;
    boolean[] used;
    int[] matching;
    int size = 0;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    ArrayList<Three> points;
    ArrayList<Pair> rightSide;
    private FastScanner in;

    public static void main(String[] arg) {
        new MoveThings().run();
    }

    private boolean check(double time) {
        graf.clear();
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist(points.get(i), rightSide.get(j)) <= time) {
                    graf.get(i).add(j);
                }
            }
        }
        Arrays.fill(matching, -1);
        size = 0;
        for (int i = 0; i < n; i++) {
            used = new boolean[n];
            size += (dfs(i) ? 1 : 0);
        }
        return size == n;
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

    private double dist(Three three, Pair pair) {
        return Math.sqrt((three.x - pair.first) * (three.x - pair.first) + (three.y - pair.second) * (three.y - pair.second)) / three.time;
    }

    private void initialize() {
        n = in.nextInt();
        matching = new int[n];
        used = new boolean[n];
        points = new ArrayList<>();
        rightSide = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Three(in.nextInt(), in.nextInt(), in.nextInt()));
        }
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            rightSide.add(new Pair(x, y));
        }
    }

    public void solve() throws IOException {
        initialize();
        double left = 0;
        double right = INF;
        while (right - left > EPS) {
            double m = (right + left) / 2;
            if (check(m)) {
                right = m;
            } else {
                left = m;
            }
        }
        System.out.println(right);
    }

    public void run() {
        try {
            in = new FastScanner();

            solve();

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
    }

    class Three {
        int x;
        int y;
        int time;

        Three(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
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