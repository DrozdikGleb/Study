package discretemaths.firstyear.cfgrammar; /**
 * Created by Drozdov Gleb on 03.06.2017.
 * <p>
 * Created by Drozdov Gleb on 02.06.2017.
 */
/**
 * Created by Drozdov Gleb on 02.06.2017.
 */


import java.util.*;
import java.io.*;

public class task4 {
    FastScanner in;
    PrintWriter out;
    boolean ans = false;
    ArrayList<ArrayList<Pair>> graf = new ArrayList<>();
    ArrayList<ArrayList<Pair>> graf2 = new ArrayList<>();

    class Pair {

        int second;
        char str;
        char str2;

        Pair(int second, char str, char str2) {
            this.second = second;
            this.str = str;
            this.str2 = str2;
        }

        public int getSecond() {
            return second;
        }

        public char getChar() {
            return str;
        }

        public char getChar2() {
            return str2;
        }


    }

    HashSet<Character> set = new HashSet<>();
    HashSet<String> checker = new HashSet<>();

    public void solve() throws IOException {
        int n = in.nextInt();
        int s = in.next().charAt(0) - 65;
        char[] ans = new char[26];
        for (int i = 0; i < 26; i++) {
            graf.add(new ArrayList<Pair>());
            graf2.add(new ArrayList<>());

        }

        for (int i = 0; i < n; i++) {
            String str = in.next();
            int a = str.charAt(0) - 65;
            in.next();
            String str2 = in.next();
            if (str2.length() == 2) {
                graf2.get(a).add(new Pair(a, str2.charAt(0), str2.charAt(1)));
            } else {
                graf.get(a).add(new Pair(a, str2.charAt(0), ' '));
            }

        }
        String word = in.next();
        int l = word.length();
        long[][][] d = new long[26][l][l];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < word.length(); j++) {
                char charcheck = word.charAt(j);
                for (int k = 0; k < graf.get(i).size(); k++) {
                    if (graf.get(i).get(k).getChar() == charcheck) {
                        d[i][j][j] = 1;
                    }
                }
            }
        }
        int mod = 1000000007;
        for (int add = 1; add < l; add++) {
            for (int st = 0; st < l-add; st++) { // индексы начала подстроки
                for (int k = st; k < st + add; k++) { //конец
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < graf2.get(i).size(); j++) {
                            int a = graf2.get(i).get(j).getChar() - 65;
                            int b = graf2.get(i).get(j).getChar2() - 65;
                            d[i][st][st + add] += d[a][st][k] * d[b][k + 1][st + add];
                            d[i][st][st + add] %= mod;
                        }
                    }
                }
            }
        }


        out.write(String.valueOf(d[s][0][l - 1]));
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("nfc.in"));
            out = new PrintWriter(new File("nfc.out"));

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

        String nextLine() throws IOException {
            return br.readLine();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        new task4().run();
    }
}


