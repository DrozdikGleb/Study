package discretemaths.firstyear.cfgrammar; /**
 * Created by Drozdov Gleb on 02.06.2017.
 */


import java.util.*;
import java.io.*;

public class task3 {
    FastScanner in;
    PrintWriter out;
    ArrayList<Pair> graf = new ArrayList<>();
    ArrayList<Pair> graf2 = new ArrayList<>();

    class Pair {

        int second;
        String str;

        Pair(int second, String str) {
            this.second = second;
            this.str = str;
        }

        public int getSecond() {
            return second;
        }

        public String getChar() {
            return str;
        }
    }

    HashSet<Character> set = new HashSet<>();
    HashSet<Character> set2 = new HashSet<>();
    HashSet<Character> fullset = new HashSet<>();
    HashSet<Character> setcheck = new HashSet<>();

    public void solve() throws IOException {
        int n = in.nextInt();
        char s = in.next().charAt(0);
        boolean ans[] = new boolean[26];
        set2.add(s);
        fullset.add(s);
        for (int i = 0; i < n; i++) {
            String str = in.nextLine();
            if (str.length() == 4) {
                set.add(str.charAt(0));
                fullset.add(str.charAt(0));
                setcheck.add(str.charAt(0));
            } else {
                int k = 0;
                setcheck.add(str.charAt(0));
                fullset.add(str.charAt(0));
                graf2.add(new Pair(str.charAt(0), str.substring(5, str.length())));
                for (int j = 5; j < str.length(); j++) {
                    if (str.charAt(j) - 90 > 0) {
                        k++;
                    } else {
                        fullset.add(str.charAt(j));
                    }
                }
                if (k == str.length() - 5) {
                    set.add(str.charAt(0));
                } else {
                    graf.add(new Pair(str.charAt(0) - 65, str.substring(5, str.length())));
                }

            }
        }
        boolean check = true;
        while (check && (!graf.isEmpty())) {
            for (int i = 0; i < graf.size(); i++) {
                int a = graf.get(i).getSecond();
                String str = graf.get(i).getChar();
                int k = 0;
                for (int j = 0; j < str.length(); j++) {
                    if (set.contains(str.charAt(j)) || (str.charAt(j) - 90 > 0)) k++;
                }
                if ((k == str.length()) && (!set.contains((char) (a + 65)))) {
                    set.add((char) (a + 65));
                    graf.remove(i);
                    check = true;
                    break;
                } else {
                    check = false;
                }
            }
        }
        boolean check2 = true;
        while (check2 && (!graf2.isEmpty())) {
            int k = 0;
            int l = graf2.size();
            for (int i = 0; i < graf2.size(); i++) {
                boolean checker = false;
                int a = graf2.get(i).getSecond();
                String str = graf2.get(i).getChar();
                int count = 0;
                if (set2.contains((char) (a))) {
                    int p = 0;
                    for (int j = 0; j < str.length(); j++) {
                        if ((setcheck.contains(str.charAt(j))) || (str.charAt(j) - 90 > 0)) {
                            p++;
                        }
                    }
                    if (p == str.length()) {
                        for (int j = 0; j < str.length(); j++) {
                            if ((str.charAt(j) - 90 <= 0) && (!set2.contains(str.charAt(j)))) {
                                set2.add(str.charAt(j));
                                check2 = true;
                                checker = true;
                            } else {
                                count++;
                            }
                        }
                    } else {
                        checker = true;
                    }
                    if (count == str.length()) {
                        k++;
                    }
                    if (checker) {
                        graf2.remove(i);
                        break;
                    }
                } else {
                    k++;
                }
            }
            if (k == l) check2 = false;
        }

        for (Iterator<Character> it = fullset.iterator(); it.hasNext(); ) {
            Character f = it.next();
            ans[(int) (f) - 65] = true;
        }
        for (Iterator<Character> it = set.iterator(); it.hasNext(); ) {
            Character f = it.next();
            if (set2.contains(f)) {
                ans[(int) (f) - 65] = false;
            }
        }
        for (Iterator<Character> it = set2.iterator(); it.hasNext(); ) {
            Character f = it.next();
            if (set.contains(f)) {
                ans[(int) (f) - 65] = false;
            }
        }
    /*    if ((set2.contains('S')&&(!set.contains('S')))){
            ans[(int)('S'-65)]=false;
        }*/
        for (int i = 0; i < 26; i++) {
            if (ans[i]) {
                out.write((char) (i + 65) + " ");
            }
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("in"));
            out = new PrintWriter(new File("out"));

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
        new task3().run();
    }
}



