import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Drozdov Gleb on 13.04.2017.
 */
public class task3 {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        double[] a = new double[m];
        double[] b = new double[m];
        double ans = 0;
        double total = 1;

        for (int i = 0; i < m; i++) {
            a[i] = in.nextDouble();
            b[i] = in.nextDouble();
            total *= a[i];
            if (i == 0) {
                ans += (1 / total) * b[i];
            } else {
                ans += (1 / total) * (b[i] - b[i - 1]);
            }
        }
        ans = n-ans;

        out.write(String.valueOf(ans));
        out.close();

    }

    public void run() {
        try {
            in = new FastScanner(new File("lottery.in"));
            out = new PrintWriter(new File("lottery.out"));

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

    public static void main(String[] arg) {
        new task3().run();
    }
}
