package discretemaths.secondyear.hamiltonpaths; /**
 * Created by GlebDrozdov on 15.10.2017.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TournamentCycle {
    FastScanner in;
    PrintWriter out;
    byte[][] graf;

    public static void main(String[] arg) {
        new TournamentCycle().run();
    }

    public void mergeSort(ArrayList<Integer> mas, int l, int r) {
        if (l < r) {
            int middle = l + (r - l) / 2;
            mergeSort(mas, l, middle);
            mergeSort(mas, middle + 1, r);
            merge(mas, l, middle, r);
        }
    }

    public void merge(ArrayList<Integer> mas, int l, int middle, int r) {
        int i, j, k;
        int n1 = middle - l + 1;
        int n2 = r - middle;
        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();

        for (i = 0; i < n1; i++)
            L.add(mas.get(l + i));
        for (j = 0; j < n2; j++)
            R.add(mas.get(middle + 1 + j));
        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (graf[L.get(i)][R.get(j)] == 1) {
                mas.set(k, L.get(i));
                i++;
            } else {
                mas.set(k, R.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            mas.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            mas.set(k, R.get(j));
            j++;
            k++;
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        graf = new byte[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graf[i][j] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            String str = in.next();
            for (int j = 0; j < i; j++) {
                int c = str.charAt(j) - 48;
                // 0 - входящее ребро 1 - выходящее ребро
                if (c == 1) {
                    graf[i][j] = 1;
                } else {
                    graf[j][i] = 1;
                }
            }
        }
        ArrayList<Integer> vertex = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            vertex.add(i);
        }
        mergeSort(vertex, 0, n - 1);
        while (graf[vertex.get(n - 1)][vertex.get(0)] != 1) {
            Collections.shuffle(vertex);
            mergeSort(vertex, 0, n - 1);
        }
        for (int i = 0; i < n; i++) {
            out.print(String.valueOf(vertex.get(i) + 1) + " ");
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("guyaury.in"));
            out = new PrintWriter(new File("guyaury.out"));

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