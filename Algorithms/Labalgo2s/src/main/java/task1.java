/**
 * Created by Drozdov Gleb on 15.04.2017.
 */

import java.io.*;
import java.util.*;

public class task1  {
    FastScanner in;
    PrintWriter out;
    public class vert implements Comparable{
        public int w;
        public int f;
        public int s;
         public vert(int w,int f,int s){
             this.w=w;
             this.f=f;
             this.s=s;
         }
        public int compareTo(Object obj)
        {
            vert tmp = (vert)obj;
            if(this.w < tmp.w)
            {
                return -1;
            }
            else if(this.w > tmp.w)
            {
                return 1;
            }
            return 0;
        }
    }


    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int ans=0;
        vert [] graf=new vert[m];
        int []tree=new int [n];
        for (int i = 0; i < m; i++) {
            int f=in.nextInt()-1;
            int s=in.nextInt()-1;
            int w=in.nextInt();
            graf[i]=new vert(w,f,s);

        }
        for (int i = 0; i < n; i++) {
            tree[i]=i;
        }
        Arrays.sort(graf);
        for (int i=0; i<m; i++) {
            int a = graf[i].f, b = graf[i].s, w = graf[i].w;
            if (tree[a] != tree[b]) {
                ans += w;
                int old_id = tree[b], new_id = tree[a];
                for (int j = 0; j < n; j++)
                    if (tree[j] == old_id)
                        tree[j] = new_id;
            }
        }

        out.write(String.valueOf(ans));
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("spantree2.in"));
            out = new PrintWriter(new File("spantree2.out"));

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
