package algorithms.secondyear.cryptography;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by Gleb on 27.05.2018
 */

public class CheckBigPrime2 {
    private FastScanner in;
    private BufferedWriter out;
    int n;
    boolean[] prime;
    int h = 0;
    int x;
    int sgrN;
    int s = 50000;
    int[] primes = new int[100000];
    int k = 0;
    boolean[] block = new boolean[s];

    private void initialize() {
        n = in.nextInt();
        x = in.nextInt();
        sgrN = (int) Math.ceil(Math.sqrt(n));
        prime = new boolean[sgrN + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
    }

    private void count() {
        for (int i = 2; i <= sgrN; i++) {
            if (prime[i]) {
                primes[k++] = i;
                for (int j = i * i; j <= sgrN; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

    private void writeAns() throws IOException {
        //long start = System.currentTimeMillis();
        int max = n / s;
        for (int i = 0; i <= max; i++) {
            block = new boolean[s];
            int start = i * s;
            for (int j = 0; j < k; j++) {
                int start_id = (start + primes[j] - 1) / primes[j];
                int q = Math.max(start_id, 2) * primes[j] - start;
                while (q < s) {
                    block[q] = true;
                    q += primes[j];
                }
            }
            if (i == 0) {
                block[0] = true;
                block[1] = true;
            }
            for (int j = 0; j < s && start + j <= n; ++j)
                if (!block[j]) {
                    h = h * x + (j + i * s);
                }
        }
        System.out.println(h);
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
        new CheckBigPrime2().run();
    }
}

