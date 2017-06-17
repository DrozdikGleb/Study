package Lab2;


import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;


public class Gemoglobin {

    FastScanner in;
    PrintWriter out;


    public void solve() throws IOException {

        int N = in.nextInt();
        int[] sum = new int[N];
        int[] stack = new int[N];
        int j = 0;
        for (int i = 0; i < N; i++) {
            String temp = in.next();
            int k;
            if (temp.charAt(0) == '+') {
                temp.replace("+","");
                k = Integer.parseInt(temp);
                stack[j] = k;
                if (j > 0) sum[j] = sum[j - 1] + stack[j];
                else sum[j] = stack[j];
                j++;


            } else if (temp.charAt(0) == '-') {
                out.write(stack[j - 1] + "\n");
                j--;

            } else {

                temp = temp.replace("?", "");
                k= Integer.parseInt(temp);
                if (k == j) out.write(sum[j - 1]+"\n");
                else
                    out.write(sum[j - 1] - sum[j - 1 - k] + "\n");

            }

        }
        out.close();
    }


    public void run() {
        try {
            in = new FastScanner(new File("hemoglobin.in"));
            out = new PrintWriter(new File("hemoglobin.out"));

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

    public static void main(String[] arg) {
        new Lab2.Gemoglobin().run();
    }


}

