package algorithms.secondyear.dynamictree;


/**
 * Created by Gleb on 06.11.2017
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CentroidDecomposition {
    int n;
    private FastScanner in;
    private ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    private boolean[] isCentroid;
    private int size = 0;
    private int[] ans;
    private int[] subTreeSize;

    public static void main(String[] arg) {
        new CentroidDecomposition().run();
    }

    private void dfsForSize(int v, int parent) {
        subTreeSize[v] = 1;
        size++;
        for (int i = 0; i < graf.get(v).size(); i++) {
            int to = graf.get(v).get(i);
            if (to != parent && !isCentroid[to]) {
                dfsForSize(to, v);
                subTreeSize[v] = subTreeSize[v] + subTreeSize[to];
            }
        }
    }

    private int getCentroid(int v, int parent) {
        for (int i = 0; i < graf.get(v).size(); i++) {
            int to = graf.get(v).get(i);
            if (to != parent && !isCentroid[to]) {
                if (subTreeSize[to] + 1 > size / 2) {
                    return getCentroid(to, v);
                }
            }
        }
        return v;
    }

    private void makeTree(int root, int p) {
        size = 0;
        dfsForSize(root, root);
        int curCentroid = getCentroid(root, root);
        isCentroid[curCentroid] = true;
        if (p == -1) {
            ans[curCentroid] = 0;

        }
        ans[curCentroid] = p;
        for (int i = 0; i < graf.get(curCentroid).size(); i++) {
            int to = graf.get(curCentroid).get(i);
            if (!isCentroid[to]) {
                makeTree(to, curCentroid);
            }
        }
    }

    public void solve() throws IOException {
        n = in.nextInt();
        ans = new int[n];
        isCentroid = new boolean[n];
        subTreeSize = new int[n];
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graf.get(a).add(b);
            graf.get(b).add(a);
        }
        makeTree(0, -1);
        for (int i = 0; i < n; i++) {
            System.out.print((ans[i] + 1) + " ");
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