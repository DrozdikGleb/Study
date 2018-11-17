package discretemaths.firstyear.lab1;

import java.io.*;
import java.util.StringTokenizer;

public class HuffMain {
    FastScanner in;
    PrintWriter out;

    public static void main(String[] arg) {
        new HuffMain().run();
    }

    public void solve() throws IOException {
        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }


    }

    public void run() {
        try {
            in = new FastScanner(new File("huffmanin.txt"));
            out = new PrintWriter(new File("huffmanout.txt"));

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
