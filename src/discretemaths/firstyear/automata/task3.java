package discretemaths.firstyear.automata;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class task3 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    ArrayList<ArrayList<Integer>> graft = new ArrayList<>();
    ArrayList<Integer> topSort = new ArrayList<Integer>();
    boolean[] used;
    boolean[] dopusk;
    int[] color;
    boolean cycl = false;
    boolean[] possible;

    public static void main(String[] arg) {
        new task3().run();
    }

    void dfs(int v) {
        used[v] = true;
        possible[v] = true;
        for (int i = 0; i < graft.get(v).size(); i++) {
            int w = graft.get(v).get(i);
            if (!used[w]) {
                dfs(w);
            }
        }

    }

    void topologicalSort(int v) {
        if (color[v] == 2) {
            return;
        }

        if (cycl) {
            return;
        }

        color[v] = 1;
        for (int i = 0; i < graf.get(v).size(); i++) {
            int w = graf.get(v).get(i);
            if (possible[w]) {
                if (color[w] == 0) {
                    topologicalSort(w);
                }
                if (color[w] == 1) {
                    cycl = true;
                    return;
                }
            }
        }
        color[v] = 2;
        topSort.add(v);

    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        dopusk = new boolean[n];
        color = new int[n];
        used = new boolean[n];
        possible = new boolean[n];
        int[] d = new int[n];
        for (int i = 0; i < k; i++) {
            dopusk[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            d[i] = 0;
            color[i] = 0;
            graf.add(new ArrayList<Integer>());
            graft.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            char s = in.next().charAt(0);
            graf.get(a).add(b);
            graft.get(b).add(a);
        }
        for (int i = 0; i < n; i++) {
            if (dopusk[i] && (!used[i])) {
                dfs(i);
            }
        }
        d[0] = 1;
        int ans = 0;
        topologicalSort(0);
        if (cycl) {
            out.write("-1");
        } else {
            Collections.reverse(topSort);
            for (int i = 0; i < topSort.size(); i++) {
                int v = topSort.get(i);
                for (int j = 0; j < graf.get(v).size(); j++) {
                    if (possible[graf.get(v).get(j)])
                        d[graf.get(v).get(j)] += (d[v] % 1000000007);
                }
            }

            for (int i = 0; i < n; i++) {
                if (dopusk[i])
                    ans += d[i];
            }
            out.write(String.valueOf(ans));
        }


        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem3.in"));
            out = new PrintWriter(new File("problem3.out"));

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
}

