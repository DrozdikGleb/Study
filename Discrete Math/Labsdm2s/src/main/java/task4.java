import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Drozdov Gleb on 13.04.2017.
 */
public class task4 {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int [][]state = new int[m][2];
        double []chance = new double[m];
        boolean []absorbing = new boolean[n];
        double[][] Q = new double[n][n];
        double[][] R = new double[n][n];
        int position[] = new int[n];
        int abs = 0;
        for (int i = 0; i < m; i++) {
            state[i][0] = in.nextInt() - 1;
            state[i][1] = in.nextInt() - 1;
            chance[i] = in.nextDouble();
            if ((state[i][0] == state[i][1]) && (chance[i] == 1)) {
                absorbing[state[i][0]] = true;
                abs++;
            }
        }
        int nonabs = n - abs;
        double G[][] = new double[nonabs][abs];
        double N[][] = new double[nonabs][nonabs];
        double E[][] = new double[nonabs][nonabs];
        int count_q = 0;
        int count_r = 0;
        for (int i = 0; i < n; i++) {
            if (absorbing[i]) {
                position[i] = count_r;
                count_r++;
            } else {
                position[i] = count_q;
                count_q++;
            }
        }
        for (int i = 0; i < m; i++) {
            if (absorbing[state[i][1]] == true) {
                if (!absorbing[state[i][0]]) {
                    R[position[state[i][0]]][position[state[i][1]]] = chance[i];
                }
            } else
                Q[position[state[i][0]]][position[state[i][1]]] = chance[i];
        }
        for (int i = 0; i < nonabs; i++) {
            N[i][i] = 1.0;
            E[i][i] = 1.0;
            for (int j = 0; j < nonabs; j++) {
                E[i][j] -= Q[i][j];
            }
        }
        for (int i = 0; i < nonabs; i++) {

            if (E[i][i] != 1) {
                double mul = E[i][i];
                for (int j = 0; j < nonabs; j++) {

                    E[i][j] /= mul;
                    N[i][j] /= mul;
                }
            }
            for (int row = 0; row < nonabs; row++) {
                if (i != row) {
                    double mul = E[row][i];
                    for (int j = 0; j < nonabs; j++) {

                        E[row][j] -= mul * E[i][j];
                        N[row][j] -= mul * N[i][j];
                    }

                }
            }
        }
        for (int i = 0; i < nonabs; i++) {
            for (int j = 0; j < abs; j++) {
                G[i][j] = 0;
                for (int k = 0; k < nonabs; k++) {
                    G[i][j] += N[i][k] * R[k][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            double prob = 0.0;
            if (absorbing[i]) {
                for (int j = 0; j < nonabs; j++) {
                    prob += G[j][position[i]];
                }
                prob++;
                prob /= n;
            }
            out.println(prob);
        }
        out.close();

    }

    public void run() {
        try {
            in = new FastScanner(new File("absmarkchain.in"));
            out = new PrintWriter(new File("absmarkchain.out"));

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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] arg) {
        new task4().run();
    }
}
