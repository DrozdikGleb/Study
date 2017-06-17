/**
 * Created by Drozdov Gleb on 28.05.2017.
 */


import java.util.*;
import java.io.*;

public class task1 {
    FastScanner in;
    PrintWriter out;
    boolean ans =false;
    ArrayList<ArrayList<Pair>> graf = new ArrayList<>();

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
                        if (curr2==26) {
                            ans = true;
                            break;
                        }
                    }
                }
            }
    }


    public void solve() throws IOException {
        int n = in.nextInt();
        int s = in.next().charAt(0) - 65;
        for (int i = 0; i < 27; i++) {
            graf.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < n; i++) {
            int a = in.next().charAt(0) - 65;
            in.next();
            String str = in.next();
            char symbol = str.charAt(0);
            int b;
            if (str.length() == 1) {
                b = 26;
            } else {
                b = str.charAt(1) - 65;
            }

            graf.get(a).add(new Pair(b, symbol));
        }


        int m = in.nextInt();
        while (m != 0) {
            int f = s;
            String str = in.next();
            ans =false;
            dfs(0, f, str);
            if (ans) {
                out.write("yes\n");
            } else {
                out.write("no\n");
            }
            m--;
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("automaton.in"));
            out = new PrintWriter(new File("automaton.out"));

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
        new task1().run();
    }
}

