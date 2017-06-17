
import java.util.*;
import java.io.*;

/**
 * Created by Виталий on 16.10.2016.
 */

public class laba {
    FastScanner in;
    PrintWriter out;

















    public void solve() throws IOException {

        int l = in.nextInt();
        int n=in.nextInt();
        int s=0;

        int [] a = new int[2*n];

        for (int i=0;i<2*n;i++) {
            a[i]= in.nextInt();
            if(i%2 ==0){
                s+=a[i];
            }

        }
        int j=0;

        int [] b = new int [s+1];
        b[0]=0;
        for (int i=0;i<2*n;i+=2 ){
            while(a[i]!=0){
                b[j+1]=a[i+1]+b[j];
                j++;
                a[i]--;
            }
        }
        for (int i=0; i<s+1;i++){
            out.print(b[i]+" ");
        }







    }


    public void run() {
        try {
            in = new FastScanner(new File("text.txt "));
            out = new PrintWriter(new File("out.txt"));

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
        new laba().run();
    }


}
