package discretemaths.firstyear.automata; /**
 * Created by Drozdov Gleb on 02.05.2017.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class task1 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Pair>> graf = new ArrayList<>();

    public static void main(String[] arg) {
        new task1().run();
    }

    public void solve() throws IOException {
        String str = in.next();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        boolean[] dopusk = new boolean[n];
        for (int i = 0; i < k; i++) {
            dopusk[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            char s = in.next().charAt(0);
            Pair elem = new Pair(b, s);
            graf.get(a).add(elem);
        }
        int f = 0;
        boolean ans = false;
        int curr2 = 0;
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            ans = false;
            for (int j = 0; j < graf.get(f).size(); j++) {
                if (curr == graf.get(f).get(j).getChar()) {
                    ans = true;
                    f = graf.get(f).get(j).getSecond();
                    curr2 = f;
                    break;
                }
            }


            if (!ans) {
                break;
            }
        }
        if (ans && (dopusk[curr2])) {
            out.write("Accepts");
        } else {
            out.write("Rejects");
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem1.in"));
            out = new PrintWriter(new File("problem1.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Pair {
        int second;
        char str;

        Pair(int second, char str) {
            this.second = second;
            this.str = str;
        }

        public int getSecond() {
            return second;
        }

        public char getChar() {
            return str;
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
