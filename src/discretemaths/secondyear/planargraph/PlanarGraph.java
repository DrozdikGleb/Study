package discretemaths.secondyear.planargraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Gleb on 24.11.2017.
 */
public class PlanarGraph {
    FastScanner in;
    Edge[] edges;
    int[] hamPath;
    private int[] oX;
    private int[] proportion;
    private boolean[][] unnecessary;
    private int m;
    private boolean check = true;

    public static void main(String[] arg) {
        new PlanarGraph().run();
    }

    private boolean intersection(int u, int v, int a, int b) {
        int nb = Math.max(oX[a], oX[b]);
        int na = Math.min(oX[a], oX[b]);
        int nv = Math.max(oX[u], oX[v]);
        int nu = Math.min(oX[u], oX[v]);
        return ((nb > nv && nv > na && na > nu) || (nv > nb && nb > nu && nu > na));
    }

    private void checkProportion(int v) {
        for (int i = 0; i < m; i++) {
            if (!unnecessary[edges[i].left][edges[i].right]) {
                if (intersection(edges[v].left, edges[v].right, edges[i].left, edges[i].right)) {
                    if (proportion[i] == 0) {
                        proportion[i] = 3 - proportion[v];
                        checkProportion(i);
                    } else if (proportion[i] == proportion[v]) {
                        check = false;
                    }
                }
            }
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        m = in.nextInt();
        hamPath = new int[n];
        edges = new Edge[m];
        proportion = new int[m];
        unnecessary = new boolean[n][n];
        oX = new int[n];
        for (int i = 0; i < m; i++) {
            proportion[i] = 0;
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            edges[i] = new Edge(a, b);
        }
        for (int i = 0; i < n; i++) {
            hamPath[i] = in.nextInt() - 1;
            oX[hamPath[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                unnecessary[hamPath[i - 1]][hamPath[i]] = unnecessary[hamPath[i]][hamPath[i - 1]] = true;
            } else {
                unnecessary[hamPath[i]][hamPath[n - 1]] = unnecessary[hamPath[n - 1]][hamPath[i]] = true;
            }
        }
        for (int i = 0; i < m; i++) {
            if (!unnecessary[edges[i].left][edges[i].right]) {
                if (proportion[i] == 0) {
                    proportion[i] = 1;
                    checkProportion(i);
                }
            }
        }
        if (!check) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            for (int i = 0; i < n; i++) {
                System.out.print(oX[i] + " " + 0 + " ");
            }
            System.out.println();
            for (int i = 0; i < m; i++) {
                int left = edges[i].left;
                int right = edges[i].right;
                double sum = (oX[left] + oX[right]);
                if (proportion[i] == 0) {
                    if ((left == hamPath[0] && right == hamPath[n - 1]) || (left == hamPath[n - 1] && right == hamPath[0])) {
                        System.out.println(sum / 2.0 + " " + n * n);
                    } else {
                        System.out.println(sum / 2.0 + " " + 0);
                    }
                } else {
                    if (proportion[i] == 1) {
                        System.out.println(sum / 2.0 + " " + Math.abs(oX[left] - oX[right]) / 2.0);
                    } else {
                        System.out.println(sum / 2.0 + " " + Math.abs(oX[left] - oX[right]) / -2.0);
                    }
                }
            }
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

    class Edge {
        int left;
        int right;

        Edge(int left, int right) {
            this.left = left;
            this.right = right;
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

