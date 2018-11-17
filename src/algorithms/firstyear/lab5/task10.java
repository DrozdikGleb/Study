package algorithms.firstyear.lab5;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class task10 {
    FastScanner in;
    PrintWriter out;

    public static void main(String[] arg) {
        new task10().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = n * (n - 1) / 2;
        double ans1 = 0.0;
        double ans2 = 0.0;
        vert[] graf = new vert[m];
        int[] tree = new int[n];
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            y[i] = in.nextInt();
        }
        double R = in.nextDouble();
        double A = in.nextDouble();
        int k = 0;
        int t = 0;
        for (int i = 0; i < m; i++) {
            t++;
            double w = Math.sqrt(Math.pow((double) (x[k] - x[t]), 2) + Math.pow((double) (y[k] - y[t]), 2)) * R;
            graf[i] = new vert(w, k, t);
            if (t == n - 1) {
                k++;
                t = k;
            }

        }
        vert[] graf2 = new vert[m + n];
        for (int i = 0; i < m; i++) {
            graf2[i] = graf[i];
        }
        for (int i = 0; i < n; i++) {
            graf2[m + i] = new vert(A, i, n);
        }
        int[] tree2 = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            if (i != n) tree[i] = i;
            tree2[i] = i;
        }
        Arrays.sort(graf);
        for (int i = 0; i < m; i++) {
            int a = graf[i].f, b = graf[i].s;
            double w = graf[i].w;
            if (tree[a] != tree[b]) {
                ans1 += w;
                int old_id = tree[b], new_id = tree[a];
                for (int j = 0; j < n; j++)
                    if (tree[j] == old_id)
                        tree[j] = new_id;
            }
        }
        Arrays.sort(graf2);
        for (int i = 0; i < m + n; i++) {
            int a = graf2[i].f, b = graf2[i].s;
            double w = graf2[i].w;
            if (tree2[a] != tree2[b]) {
                ans2 += w;
                int old_id = tree2[b], new_id = tree2[a];
                for (int j = 0; j < n + 1; j++)
                    if (tree2[j] == old_id)
                        tree2[j] = new_id;
            }
        }

        out.write(String.valueOf(Math.min(ans1, ans2)));
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("transport.in"));
            out = new PrintWriter(new File("transport.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class vert implements Comparable {
        public double w;
        public int f;
        public int s;

        public vert(double w, int f, int s) {
            this.w = w;
            this.f = f;
            this.s = s;
        }

        public int compareTo(Object obj) {
            vert tmp = (vert) obj;
            if (this.w < tmp.w) {
                return -1;
            } else if (this.w > tmp.w) {
                return 1;
            }
            return 0;
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}

