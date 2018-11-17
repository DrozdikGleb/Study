package algorithms.secondyear.cryptography;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Gleb on 27.05.2018
 */

public class CheckBigPrime {
    int n;
    BigInteger ONE = new BigInteger("1");
    BigInteger TWO = new BigInteger("2");
    private FastScanner in;
    private BufferedWriter out;

    public static void main(String[] arg) {
        new CheckBigPrime().run();
    }

    private void initialize() {
        n = in.nextInt();
    }

    private boolean testMillerRabin(BigInteger bigInteger) {
        int k;
        int sizeInBits = bigInteger.bitLength();
        if (sizeInBits < 1024) {
            k = 4;
        } else {
            k = 2;
        }
        if (bigInteger.equals(new BigInteger("2"))) {
            return true;
        }
        //чётное или 1
        if (!bigInteger.testBit(0) || bigInteger.equals(new BigInteger("1"))) {
            return false;
        }

        BigInteger thisMinusOne = bigInteger.subtract(ONE);
        BigInteger m = thisMinusOne;
        int a = m.getLowestSetBit();
        m = m.shiftRight(a);
        for (int i = 0; i < k; i++) {
            BigInteger b;
            do {
                b = new BigInteger(bigInteger.bitLength(), new Random());
            } while (b.compareTo(ONE) <= 0 || b.compareTo(bigInteger) >= 0);

            int j = 0;
            BigInteger z = b.modPow(m, bigInteger);
            while (!((j == 0 && z.equals(ONE)) || z.equals(thisMinusOne))) {
                if (j > 0 && z.equals(ONE) || ++j == a)
                    return false;
                z = z.modPow(TWO, bigInteger);
            }
        }
        return true;
    }

    private void writeAns() throws IOException {
        for (int i = 0; i < n; i++) {
            BigInteger bigInteger = new BigInteger(in.next());
            out.write(testMillerRabin(bigInteger) ? "YES\n" : "NO\n");
        }
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
}

