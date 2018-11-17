package algorithms.secondyear.cryptography;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Factorization {
    private FastScanner in;
    private BufferedWriter out;
    int n;
    boolean[] prime;
    int[] divider;

    private void initialize() {
        n = in.nextInt();
        prime = new boolean[1000001];
        divider = new int[1000001];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
    }

    private void count() {
        for (int i = 2; i <= 1000; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= 1000000; j += i) {
                    if (prime[j]) {
                        prime[j] = false;
                        divider[j] = i;
                    }
                }
            }
        }
    }

    private void getFactorization(int n) throws IOException {
        while (!prime[n]) {
            out.write(divider[n] + " ");
            n /= divider[n];
        }
        out.write(n + "\n");
    }

    private void writeAns() throws IOException {
        for (int i = 0; i < n; i++) {
            getFactorization(in.nextInt());
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
        new Factorization().run();
    }
}
