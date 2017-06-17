/**
 * Created by Drozdov Gleb on 26.04.2017.
 */
import java.util.*;
import java.io.*;
public class task13 {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();

    public void solve() throws IOException {
        int n=in.nextInt();
        int m=in.nextInt();
        int v=in.nextInt()-1;
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int s = in.nextInt() - 1;
            g.get(f).add(s);
        }

        boolean ans=dfs(v,true);
        if(ans){
            out.write("First player wins"+"\n");
        }
        else{
            out.write("Second player wins"+"\n");
        }
        out.close();
    }
    boolean dfs(int v,boolean check){
        for (int i = 0; i <g.get(v).size() ; i++) {
            if(dfs(g.get(v).get(i),!check)==check){
                return check;
            }
        }
        //System.out.println(!check);
        return !check;
    }


    public void run() {
        try {
            in = new FastScanner(new File("game.in"));
            out = new PrintWriter(new File("game.out"));

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
        new task13().run();
    }
}

