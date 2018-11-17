package discretemaths.firstyear.probability;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Drozdov Gleb on 13.04.2017.
 */
public class task1 {
    FastScanner in;
    PrintWriter out;

    public static void main(String[] arg) {
        new task1().run();
    }

    public void solve() throws IOException {
        int k = in.nextInt();
        int n = in.nextInt();
        double ans = 0;

        for (int i = 0; i < k; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            ans += a * b;
        }
        ans = ans / n / 100;
        out.write(String.valueOf(ans));
        out.close();

    }

    public void run() {
        try {
            in = new FastScanner(new File("exam.in"));
            out = new PrintWriter(new File("exam.out"));

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
