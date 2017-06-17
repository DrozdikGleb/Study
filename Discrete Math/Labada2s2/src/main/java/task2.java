/**
 * Created by Drozdov Gleb on 02.06.2017.
 */



import java.util.*;
import java.io.*;

public class task2 {
    FastScanner in;
    PrintWriter out;
    boolean ans =false;
   ArrayList<Pair> graf = new ArrayList<>();

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
    HashSet<String> checker = new HashSet<>();
    public void solve() throws IOException {
        int n = in.nextInt();
        int s = in.next().charAt(0) - 65;
        char [] ans = new char[26];
        for (int i = 0; i <26 ; i++) {
            ans[i]=(char)(65+i);
        }

        for (int i = 0; i < n; i++) {
            String str = in.nextLine();
            if (str.length() == 4) {
                set.add(str.charAt(0));
            } else {
                boolean check = false;
                for (int j = 5; j < str.length(); j++) {
                    if (str.charAt(j) - 90 > 0) {
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    int a = str.charAt(0) - 65;
                    graf.add(new Pair(a, str.substring(5, str.length())));
                }
            }
        }
            boolean check=true;
            while(check&&(!graf.isEmpty())){
                for (int i = 0; i < graf.size(); i++) {
                    int a = graf.get(i).getSecond();
                    String str = graf.get(i).getChar();
                    int k=0;
                    for (int j = 0; j < str.length(); j++) {
                        if(set.contains(str.charAt(j))) k++;
                    }
                    if (k==str.length()){
                        set.add((char)(a+65));
                        graf.remove(i);
                        check=true;
                        break;
                    }
                    else{
                        check =false;
                    }
                }

            }


        for (int i = 0; i <26 ; i++) {
            if(set.contains(ans[i])){
                out.write(ans[i]+" ");
            }
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("epsilon.in"));
            out = new PrintWriter(new File("epsilon.out"));

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
        new task2().run();
    }
}


