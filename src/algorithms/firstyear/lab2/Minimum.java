package algorithms.firstyear.lab2;


import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Minimum {
    FastScanner in;
    PrintWriter out;
    public static int m = Integer.MAX_VALUE;
    public static Stack<Integer> minstack = new Stack<>();
    public static Stack<Integer> stack = new Stack<>();

    public void solve() throws IOException {
        int N = in.nextInt();

        for (int i = 0; i < N; i++) {
            int temp = in.nextInt();

            if (temp == 1) {

                pushn(in.nextInt());

            } else if (temp == 2) {
                popn();

            } else {
                out.write(minstack.peek() + "\n");

            }
        }
        out.close();
    }

    public static void pushn(int element) {
        stack.push(element);
        if (m > element) {
            m = element;
        }
        minstack.push(m);

    }

    public static void popn() {
        stack.pop();
        minstack.pop();
        if (minstack.size() > 0)
            m = minstack.peek();
        else m = Integer.MAX_VALUE;
    }


    public void run() {
        try {
            in = new FastScanner(new File("stack-min.in"));
            out = new PrintWriter(new File("stack-min.out"));

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
        new Minimum().run();
    }
}
