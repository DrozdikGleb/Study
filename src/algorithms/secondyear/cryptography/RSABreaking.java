package algorithms.secondyear.cryptography;


import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class RSABreaking {
    private FastScanner in;
    private BufferedWriter out;
    int n;
    int e;
    int C;
    int sgrN;

    private void initialize() {
        n = in.nextInt();
        e = in.nextInt();
        C = in.nextInt();
        sgrN = (int) Math.ceil(Math.sqrt(n));
    }

    private void writeAns() {
        int p = 0;
        int q = 0;

        for (int i = 2; i <= sgrN; i++) {
            if (n % i == 0) {
                p = n / i;
                q = i;
            }
        }
        BigInteger bigE = new BigInteger(String.valueOf(e));
        BigInteger p_1 = new BigInteger(String.valueOf(p - 1));
        BigInteger q_1 = new BigInteger(String.valueOf(q - 1));
        BigInteger f = p_1.multiply(q_1);
        BigInteger d = bigE.modInverse(f);
        if (Integer.parseInt(d.toString()) < 0) {
            d = d.add(f);
        }
        String ans = new BigInteger(String.valueOf(C)).modPow(d, new BigInteger(String.valueOf(n))).toString();
        System.out.println(ans);
    }

    public void solve() throws IOException {
        initialize();
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
        new RSABreaking().run();
    }
}
