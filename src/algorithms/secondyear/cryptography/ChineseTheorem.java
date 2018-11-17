package algorithms.secondyear.cryptography;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChineseTheorem {
    private FastScanner in;
    int a;
    int b;
    int n;
    int m;

    private void initialize() {
        a = in.nextInt();
        b = in.nextInt();
        n = in.nextInt();
        m = in.nextInt();
    }

    private void count() {
        int i = 1;
        while (i * m % n != a) {
            i++;
        }
        int j = 1;
        while (j * n % m != b) {
            j++;
        }
        int ans = (i * m + j * n) % (m * n);
        System.out.println(ans);
    }

    public void solve() throws IOException {
        initialize();
        count();
    }

    public void run() {
        try {
            in = new FastScanner();
            //out = new BufferedWriter(new OutputStreamWriter(System.out));
            solve();
            //out.close();

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
        new ChineseTheorem().run();
    }
}

