package discretemaths.firstyear.probability;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Drozdov Gleb on 13.04.2017.
 */
public class task5 {
    FastScanner in;
    PrintWriter out;

    public static void main(String[] arg) {
        new task5().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        double[][] chain = new double[n][n];
        double[][] total = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chain[i][j] = in.nextDouble();
            }
        }

        while (true) {
            int count = 0;
            double[][] ans = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        ans[i][j] += chain[i][k] * chain[k][j];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (Math.abs(ans[i][j] - chain[i][j]) <= 0.0001) {
                        count++;
                    }
                }
            }
            if (count == n * n) {
                total = ans;
                break;
            }
            chain = ans;

        }
        for (int i = 0; i < n; i++) {
            out.write(total[i][i] + "\n");
        }
        out.close();


    }

    public void run() {
        try {
            in = new FastScanner(new File("markchain.in"));
            out = new PrintWriter(new File("markchain.out"));

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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
