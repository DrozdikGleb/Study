/**
 * Created by Drozdov Gleb on 16.04.2017.
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by Drozdov Gleb on 13.04.2017.
 */
public class task8 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();
    boolean[] used;
    int[] tin;
    int[] fup;
    int timer = 0;
    int[] ans;
    int k = 0;
    ArrayList<Integer> num[];
    boolean vertex[];

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        used = new boolean[n];
        ans = new int[m];
        tin = new int[n];
        fup = new int[n];
        num = new ArrayList[n];
        vertex = new boolean[n];
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int s = in.nextInt() - 1;
            g.get(f).add(s);
            g.get(s).add(f);
        }
        for (int i = 0; i < n; i++) {
            used[i] = false;
            vertex[i]=false;
        }
        for (int i = 0; i < n; i++) {
            if (!used[i])
                dfs(i, -1);
        }
        int k=0;
        for (int i = 0; i <n ; i++) {
            if (vertex[i]){
                k++;
            }
        }
        out.write(String.valueOf(k)+"\n");
            for (int i = 0; i < n; i++) {
                if (vertex[i]) {
                    out.write(String.valueOf(i + 1) + " ");
                }
            }

        out.close();


    }

    void dfs(int v, int p) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        int child=0;
        for (int i = 0; i < g.get(v).size(); i++) {
            int to = g.get(v).get(i);
            if (to == p) continue;
            if (used[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if ((fup[to] >= tin[v])&&(p!=-1)){
                    k++;
                    vertex[v] = true;
                }
                child++;

            }
        }
        if (p == -1 && child > 1)
            vertex[v]=true;

    }

    public void run() {
        try {
            in = new FastScanner(new File("points.in"));
            out = new PrintWriter(new File("points.out"));

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
        new task8().run();
    }
}
