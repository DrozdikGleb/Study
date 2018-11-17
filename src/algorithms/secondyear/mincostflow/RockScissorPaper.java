package algorithms.secondyear.mincostflow;

/**
 * Created by Gleb on 28.04.2018
 */

import java.io.*;
import java.util.StringTokenizer;

public class RockScissorPaper {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] arg) {
        new RockScissorPaper().run();
    }

    public void solve() throws IOException {
        int r1 = in.nextInt();
        int s1 = in.nextInt();
        int p1 = in.nextInt();
        int r2 = in.nextInt();
        int s2 = in.nextInt();
        int p2 = in.nextInt();
        int max1 = Math.max(0, p1 - p2 - s2);
        int max2 = Math.max(0, s1 - s2 - r2);
        int max3 = Math.max(0, r1 - r2 - p2);
        out.print(Math.max(max1, Math.max(max2, max3)));
    }

    public void run() {
        try {
            in = new FastScanner(new File("rps2.in"));
            out = new PrintWriter(new File("rps2.out"));

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
