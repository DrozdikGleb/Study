package algorithms.secondyear.flowsandmatchings;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cockroachs {
    int n;
    int w;
    long d[];
    Thing[] things;
    int[][] graf;
    boolean[] used;
    long INF = Long.MAX_VALUE;
    private FastScanner in;

    public static void main(String[] arg) {
        new Cockroachs().run();
    }

    private void dejkstra() {
        Arrays.fill(d, INF);
        d[0] = 0;
        int v;
        for (int i = 0; i < n + 2; i++) {
            v = -1;
            for (int j = 0; j < n + 2; j++) {
                if (!used[j] && (v == -1 || d[j] < d[v])) {
                    v = j;
                }
            }
            if (d[v] == INF) break;
            used[v] = true;
            for (int j = 0; j < n + 2; j++) {
                if (v == j) continue;
                if (d[v] + graf[v][j] < d[j]) {
                    d[j] = d[v] + graf[v][j];
                }
            }
        }

    }

    private int getDistanceBetweenThings(Thing thing1, Thing thing2) {
        int oX;
        if (thing1.x1 >= thing2.x1) {
            oX = thing1.x1 - thing2.x2;
        } else {
            oX = thing2.x1 - thing1.x2;
        }
        int oY;
        if (thing1.y1 >= thing2.y1) {
            oY = thing1.y1 - thing2.y2;
        } else {
            oY = thing2.y1 - thing1.y2;
        }
        return Math.max(0, Math.max(oX, oY));
    }

    private void initialize() {
        n = in.nextInt();
        d = new long[n + 2];
        used = new boolean[n + 2];
        things = new Thing[n];
        w = in.nextInt();
        graf = new int[n + 2][n + 2];
        for (int i = 0; i < n; i++) {
            things[i] = (new Thing(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), i + 1));
        }
    }

    private void makeEdgeBetweenThings(Thing thing1, Thing thing2) {
        int distance = getDistanceBetweenThings(thing1, thing2);
        graf[thing1.pos][thing2.pos] = graf[thing2.pos][thing1.pos] = distance;
    }

    private void makeEdgeBetweenWalls(Thing thing) {
        graf[0][thing.pos] = graf[thing.pos][0] = w - thing.y2;
        graf[n + 1][thing.pos] = graf[thing.pos][n + 1] = thing.y1;
    }

    public void solve() throws IOException {
        initialize();
        graf[0][n + 1] = graf[n + 1][0] = w;
        for (int i = 0; i < n; i++) {
            makeEdgeBetweenWalls(things[i]);
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                makeEdgeBetweenThings(things[i], things[j]);
            }
        }
        dejkstra();
        if (d[n + 1] == INF) {
            System.out.println(0);
        } else {
            System.out.println(d[n + 1]);
        }
    }

    public void run() {
        try {
            in = new FastScanner();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Thing {
        int x1;
        int x2;
        int y1;
        int y2;
        int pos;

        Thing(int x1, int y1, int x2, int y2, int pos) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.pos = pos;
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