package discretemaths.secondyear.hamiltonpaths; /**
 * Created by GlebDrozdov on 12.10.2017.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HamiltonCycle {
    FastScanner in;
    PrintWriter out;

    public static void main(String[] arg) {
        new HamiltonCycle().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        boolean[][] graf = new boolean[n][n];
        for (int i = 1; i < n; i++) {
            String str = in.next();
            for (int j = 0; j < i; j++) {
                int c = str.charAt(j) - 48;
                if (c == 1) {
                    graf[i][j] = graf[j][i] = true;
                }
            }
        }
        ArrayList<Integer> vertex = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            vertex.add(i);
            if (i < n - 1 && graf[i][i + 1]) {
                count++;
            }
        }
        if (graf[0][n - 1]) count++;
        for (int k = 0; k < n * (n - 1); k++) {
            int vert1 = vertex.get(0);
            int vert2 = vertex.get(1);
            if (!graf[vert1][vert2]) {
                count++;
                int i = 2;
                while ((!graf[vert1][vertex.get(i)]) || (!graf[vert2][vertex.get(i + 1)])) {
                    i++;
                }
                int a = 0;
                int b = i;
                while (1 + a < b - a) {
                    int temp = vertex.get(1 + a);
                    vertex.set(1 + a, vertex.get(b - a));
                    vertex.set(b - a, temp);
                    a++;
                }
                if (!graf[vertex.get(1)][vertex.get(b + 1)]) {
                    count++;
                }
            }
            vertex.add(vertex.get(0));
            vertex.remove(0);
            if (count == n) {
                break;
            }

        }

        for (int i = 0; i < n; i++) {
            out.print(String.valueOf(vertex.get(i) + 1) + " ");
        }


        out.close();


    }

    public void run() {
        try {
            in = new FastScanner(new File("fullham.in"));
            out = new PrintWriter(new File("fullham.out"));

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
}