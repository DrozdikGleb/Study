package discretemaths.firstyear.automata;
/**
 * Created by Drozdov Gleb on 02.05.2017.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class task2 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Pair>> graf = new ArrayList<>();
    boolean ans = false;
    boolean[] dopusk;

    public static void main(String[] arg) {
        new task2().run();
    }

    void dfs(int i, int curr, String s) {
        int curr1 = s.charAt(i);
        if (!ans)
            for (int j = 0; j < graf.get(curr).size(); j++) {
                char check = graf.get(curr).get(j).getChar();
                if (curr1 == check) {
                    int curr2 = graf.get(curr).get(j).getSecond();
                    if (i != s.length() - 1) {
                        dfs(i + 1, curr2, s);
                    } else {
                        if (dopusk[curr2]) {
                            ans = true;
                            break;
                        }
                    }
                }
            }
    }


    public void solve() throws IOException {
        String str = in.next();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        dopusk = new boolean[n];
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
        char c = str.charAt(str.length() - 1);
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graf.get(i).size(); j++) {
                if (!flag) {
                    if ((graf.get(i).get(j).getChar() == c) && (dopusk[graf.get(i).get(j).getSecond()])) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        if (flag) {
            dfs(0, 0, str);
        }
        if (ans) {
            out.write("Accepts");
        } else {
            out.write("Rejects");
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem2.in"));
            out = new PrintWriter(new File("problem2.out"));

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

