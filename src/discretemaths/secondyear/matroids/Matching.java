package discretemaths.secondyear.matroids;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Matching {
    FastScanner in;
    PrintWriter out;
    ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    boolean[] used;
    int[] matching;
    ArrayList<Vertex> leftVert = new ArrayList<>();

    public static void main(String[] arg) {
        new Matching().run();
    }

    private boolean dfs(int v) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        for (int i = 0; i < graf.get(v).size(); i++) {
            int to = graf.get(v).get(i);
            if (matching[to] == -1 || dfs(matching[to])) {
                matching[to] = v;
                return true;
            }
        }
        return false;
    }

    public void solve() throws IOException {
        int leftN = in.nextInt();
        int n = 2 * leftN;
        matching = new int[leftN];
        for (int i = 0; i < leftN; i++) {
            matching[i] = -1;
        }
        for (int i = 0; i < leftN; i++) {
            leftVert.add(new Vertex(i, in.nextInt()));
        }
        for (int i = 0; i < leftN; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < leftN; i++) {
            int k = in.nextInt();
            for (int j = 0; j < k; j++) {
                graf.get(i).add(in.nextInt() - 1);
            }
        }
        leftVert.sort(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return Integer.compare(o2.weight, o1.weight);
            }
        });
        for (int i = 0; i < leftN; i++) {
            used = new boolean[n];
            dfs(leftVert.get(i).pos);
        }
        int[] ans = new int[leftN];
        for (int i = 0; i < leftN; i++) {
            ans[i] = -1;
        }
        for (int i = 0; i < leftN; i++) {
            if (matching[i] == -1) {
                continue;
            }
            ans[matching[i]] = i;
        }
        for (int i = 0; i < leftN; i++) {
            out.print((ans[i] + 1) + " ");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("matching.in"));
            out = new PrintWriter(new File("matching.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Vertex {
        int weight;
        int pos;

        Vertex(int pos, int weight) {
            this.weight = weight;
            this.pos = pos;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}

