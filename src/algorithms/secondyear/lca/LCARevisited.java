package algorithms.secondyear.lca;

/**
 * Created by Gleb on 28.10.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCARevisited {

    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    int[] arrayLog;
    ArrayList<Integer> order = new ArrayList<>();
    int[] first;
    int[][] sparseTable;
    int[] depth;

    public static void main(String[] arg) {
        new LCARevisited().run();
    }

    public int countArrayLog(int n) {
        int logA = 0;
        while ((1 << logA) <= n) logA++;
        return logA - 1;
    }

    public void dfs(int k, int pr) {
        depth[k] = depth[pr] + 1;
        order.add(k);
        for (int i = 0; i < graf.get(k).size(); i++) {
            int to = graf.get(k).get(i);
            if (to != pr) {
                dfs(to, k);
                order.add(k);
            }
        }
    }

    public int lca(int v, int u) {
        int fv = first[v];
        int fu = first[u];
        if (fv > fu) {
            int temp = fu;
            fu = fv;
            fv = temp;
        }
        if (fu == fv) {
            return v;
        }
        int curLog;
        curLog = arrayLog[(fu - fv + 1)];
        int deg = (int) Math.pow(2, curLog);
        int ans;
        if (depth[sparseTable[curLog][fv]] > depth[sparseTable[curLog][fu - deg + 1]]) {
            ans = sparseTable[curLog][fu - deg + 1];
        } else {
            ans = sparseTable[curLog][fv];
        }
        return ans;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        depth = new int[n];
        for (int i = 0; i < n + 1; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int vert = in.nextInt();
            graf.get(vert).add(i);
        }
        long a1 = in.nextLong();
        long a2 = in.nextLong();
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        depth[0] = -1;
        dfs(0, 0);
        arrayLog = new int[order.size() + 1];
        first = new int[order.size()];
        for (int i = 0; i < order.size(); i++) {
            first[i] = -1;
        }
        for (int i = 0; i < order.size(); ++i) {
            int v = order.get(i);
            if (first[v] == -1) {
                first[v] = i;
            }
        }
        for (int i = 1; i < order.size() + 1; i++) {
            arrayLog[i] = countArrayLog(i);
        }
        int orderSize = order.size();
        int sparseLog = arrayLog[orderSize] + 1;
        sparseTable = new int[sparseLog][orderSize];
        for (int i = 0; i < orderSize; i++) {
            sparseTable[0][i] = order.get(i);
        }
        for (int j = 1; j < sparseLog; j++) {
            for (int i = 0; i < orderSize - (int) Math.pow(2, j) + 1; i++) {
                if (depth[sparseTable[j - 1][i]] > depth[sparseTable[j - 1][i + (int) Math.pow(2, j - 1)]]) {
                    sparseTable[j][i] = sparseTable[j - 1][i + (int) Math.pow(2, j - 1)];
                } else {
                    sparseTable[j][i] = sparseTable[j - 1][i];
                }

            }
        }

        long left = a1;
        long sum = 0;

        for (int i = 0; i < m; i++) {
            int v = lca((int) left, (int) a2);
            sum += v;
            long new_a1 = (x * a1 + y * a2 + z) % n;
            left = (new_a1 + v) % n;
            a1 = new_a1;
            a2 = (x * a2 + y * new_a1 + z) % n;
        }

        out.write(String.valueOf(sum));

        out.close();

    }

    public void run() {
        try {
            in = new FastScanner(new File("lca_rmq.in"));
            out = new PrintWriter(new File("lca_rmq.out"));

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
}
