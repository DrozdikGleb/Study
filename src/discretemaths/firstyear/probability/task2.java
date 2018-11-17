package discretemaths.firstyear.probability;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Drozdov Gleb on 13.04.2017.
 */
public class task2 {
    FastScanner in;
    PrintWriter out;

    public static void main(String[] arg) {
        new task2().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        double[] mas = new double[n];
        double full = 0;

        for (int i = 0; i < n; i++) {
            mas[i] = 1 - in.nextDouble();
            full += Math.pow(mas[i], m);
        }
        double p = Math.pow(mas[k - 1], m);

        double ans = p / full;

        out.write(String.valueOf(ans));
        out.close();

    }

    public void run() {
        try {
            in = new FastScanner(new File("shooter.in"));
            out = new PrintWriter(new File("shooter.out"));

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
