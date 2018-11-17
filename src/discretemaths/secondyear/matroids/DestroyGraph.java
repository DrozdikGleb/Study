package discretemaths.secondyear.matroids;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DestroyGraph {
    FastScanner in;
    PrintWriter out;
    //ArrayList<Edge> edges = new ArrayList<>();
    ArrayList<Edge> edges1 = new ArrayList<>();
    int[] set;

    public static void main(String[] arg) {
        new DestroyGraph().run();
    }

    private int get_set(int v) {
        if (v == set[v]) {
            return v;
        } else {
            set[v] = get_set(set[v]);
            return set[v];
        }
    }

    private void union_set(int a, int b) {
        a = get_set(a);
        b = get_set(b);
        if ((int) (Math.random() * 1000) % 2 == 0) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (a != b) {
            set[a] = b;
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        set = new int[n];
        long s = in.nextLong();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            long weight = in.nextLong();
            //edges.add(new Edge(from,to,weight));
            edges1.add(new Edge(from, to, -weight, i + 1));
        }
        for (int i = 0; i < n; i++) {
            set[i] = i;
        }
        ArrayList<Edge> answer = new ArrayList<>();
        edges1.sort(Comparator.comparingLong(o -> o.weight));
        for (int i = 0; i < m; i++) {
            int from = edges1.get(i).from;
            int to = edges1.get(i).to;
            long weight = edges1.get(i).weight;
            int number = edges1.get(i).number;
            if (get_set(from) != get_set(to)) {
                union_set(from, to);
            } else {
                answer.add(new Edge(from, to, -weight, number));
            }
        }
        long k = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        answer.sort(Comparator.comparingLong(o -> o.weight));
        for (Edge anAnswer : answer) {
            long add = anAnswer.weight;
            if (k + add <= s) {
                k += add;
                ans.add(anAnswer.number);
            } else {
                break;
            }
        }
        ans.sort(Comparator.comparingInt(o -> o));
        out.print(ans.size() + "\n");
        for (Integer an : ans) {
            out.print(String.valueOf(an) + " ");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("destroy.in"));
            out = new PrintWriter(new File("destroy.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Edge {
        int from;
        int to;
        long weight;
        int number;

        Edge(int from, int to, long weight, int number) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.number = number;
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

