import java.io.File;
import java.util.*;
import java.io.*;

public class T8 {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int n=in.nextInt();
        int k=in.nextInt();
        long mas[]=new long [k+1];
        for (int i=1;i<k+1;i++){
            mas[i]=i;
        }
        int p = k;
        if (n==k){
            for (int i=1;i<k+1;i++){
                out.write(String.valueOf(mas[i])+" ");
            }
        }
        else{

        while (p>0) {
            for (int i = 1; i < k + 1; i++) {
                out.write(String.valueOf(mas[i]) + " ");
            }
            out.write("\n");
            if (mas[k] == n) {
                p--;
            } else p = k;
            if (p > 0) {
                for (int i = k; i >= p; i--) {
                    mas[i] = mas[p] + i - p + 1;
                }
            }
        }
        }
        out.close();

    }

    public void run() {
        try {
            in = new FastScanner(new File("choose.in"));
            out = new PrintWriter(new File("choose.out"));

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
        new T8().run();
    }
}


