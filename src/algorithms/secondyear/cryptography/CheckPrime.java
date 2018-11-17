package algorithms.secondyear.cryptography;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CheckPrime {
    private FastScanner in;
    private BufferedWriter out;
    int n;
    boolean[] prime;

    private void initialize() {
        n = in.nextInt();
        prime = new boolean[1000001];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
    }

    private void count() {
        for (int i = 2; i <= 1000; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= 1000000; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

    private void writeAns() throws IOException {
        //long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            out.write(prime[in.nextInt()] ? "YES\n" : "NO\n");
        }
        //out.write(String.valueOf(System.currentTimeMillis() - start));
    }

    public void solve() throws IOException {
        initialize();
        count();
        writeAns();
    }

    public void run() {
        try {
            in = new FastScanner();
            out = new BufferedWriter(new OutputStreamWriter(System.out));
            solve();
            out.close();

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

    public static void main(String[] arg) {
        new CheckPrime().run();
    }
}
