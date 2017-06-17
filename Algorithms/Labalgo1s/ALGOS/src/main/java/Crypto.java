
import java.util.*;
import java.io.*;

public class Crypto {
    FastScanner in;
    PrintWriter out;

    public class matr {
        public int a;
        public int b;
        public int c;
        public int d;

        matr() {
            a = 1;
            b = 0;
            c = 0;
            d = 1;
        }
    }

    public void buildTree(matr tree[], matr mas[], int k, int tl, int tr, int mod) {

        if (tl == tr)
            tree[k] = mas[tl];
        else {
            int m = (tl + tr) / 2;
            buildTree(tree, mas, k * 2, tl, m, mod);
            buildTree(tree, mas, k * 2 + 1, m + 1, tr, mod);
            tree[k] = count(tree[k * 2], tree[k * 2 + 1], mod);

        }

    }

    public matr mult(matr tree[], int k, int tl, int tr, int l, int r, int mod) {
        if (l > r)
            return new matr();
        if (l == tl && r == tr)
            return tree[k];
        int m = (tl + tr) / 2;
        return count(mult(tree, k * 2, tl, m, l, Math.min(r, m), mod), mult(tree, k * 2 + 1, m + 1, tr, Math.max(l, m + 1), r, mod), mod);
    }

    public matr count(matr mat1, matr mat2, int r) {
        matr matn = new matr();
        matn.a = (mat1.a * mat2.a + mat1.b * mat2.c) % r;
        matn.b = (mat1.a * mat2.b + mat1.b * mat2.d) % r;
        matn.c = (mat1.c * mat2.a + mat1.d * mat2.c) % r;
        matn.d = (mat1.c * mat2.b + mat1.d * mat2.d) % r;
        return matn;
    }

    public void solve() throws IOException {
        int r = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        matr mas[] = new matr[n];
        matr tree[] = new matr[4 * n];
        for (int i = 0; i < n; i++) {
            matr mat = new matr();
            mat.a = in.nextInt();
            mat.b = in.nextInt();
            mat.c = in.nextInt();
            mat.d = in.nextInt();
            mas[i] = mat;
        }

        buildTree(tree, mas, 1, 0, n - 1, r);
        for (int i = 0; i < m; i++) {
            int f = in.nextInt();
            int l = in.nextInt();
            matr mat2 = new matr();
            mat2 = mult(tree, 1, 0, n - 1, f - 1, l - 1, r);
            out.write(mat2.a + " " + mat2.b +"" + '\n');
            out.write(mat2.c + " " + mat2.d + '\n' + '\n');
        }


    }

    public void run() {
        try {
            in = new FastScanner(new File("crypto.in"));
            out = new PrintWriter(new File("crypto.out"));

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
        new Crypto().run();
    }
}
