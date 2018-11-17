package algorithms.secondyear.flowsandmatchings;

import java.io.*;
import java.util.*;

public class Snails {
    long[] d;
    int[] p;
    int n;
    int m;
    int s;
    int t;
    boolean[] used;
    long INF = Long.MAX_VALUE;
    private FastScanner in;
    private PrintWriter out;
    private ArrayList<ArrayList<Edge>> edges = new ArrayList<>();

    public static void main(String[] arg) {
        new Snails().run();
    }

    private void addEdge(int from, int to, int maxFlow, int pos) {
        Edge e1 = new Edge(from, to, maxFlow, pos);
        Edge e2 = new Edge(from, to, 0, pos, true);
        e1.back = e2;
        e2.back = e1;
        edges.get(from).add(e1);
        edges.get(to).add(e2);
    }

    private void initialize() {
        d = new long[n];
        p = new int[n];
        used = new boolean[n];
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            if (from == to) continue;
            addEdge(from, to, 1, i);
        }
    }

    private boolean bfs() {
        Arrays.fill(d, INF);
        d[s] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Edge e : edges.get(cur)) {
                if (e.flow < e.maxFlow && d[e.to] == INF) {
                    d[e.to] = d[cur] + 1;
                    queue.add(e.to);

                }
            }
        }
        return d[t] != INF;
    }

    private long dfs(int u, long min) {
        if (u == t || min == 0) {
            return min;
        }
        for (int v = p[u]; v < edges.get(u).size(); v++) {
            Edge curEdge = edges.get(u).get(v);
            if (d[curEdge.to] == d[u] + 1) {
                long delta = dfs(curEdge.to, Math.min(min, curEdge.maxFlow - curEdge.flow));
                if (delta > 0) {
                    curEdge.flow += delta;
                    curEdge.back.flow -= delta;
                    return delta;
                }
            }
            p[u]++;
        }
        return 0;
    }


    private void simpleDfs(int v, List<Integer> list) {
        if (v == t) {
            return;
        }
        list.add(v + 1);
        for (int i = 0; i < edges.get(v).size(); i++) {
            Edge cur = edges.get(v).get(i);
            if (!cur.visited && cur.flow == 1) {
                cur.visited = true;
                simpleDfs(cur.to, list);
                break;
            }
        }
    }

    private long dinica() {
        long maxFlow = 0;
        while (bfs()) {
            Arrays.fill(p, 0);
            long flow = dfs(s, INF);
            while (flow > 0) {
                maxFlow += flow;
                flow = dfs(s, INF);
            }
        }
        return maxFlow;
    }

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        s = in.nextInt() - 1;
        t = in.nextInt() - 1;
        initialize();
        long answer = dinica();
        if (answer < 2) {
            out.println("NO");
        } else {
            out.println("YES");
            ArrayList<Integer> ans1 = new ArrayList<>();
            simpleDfs(s, ans1);
            ans1.add(t + 1);
            ans1.forEach((x) -> out.print(x + " "));
            ArrayList<Integer> ans2 = new ArrayList<>();
            out.println();
            simpleDfs(s, ans2);
            ans2.add(t + 1);
            ans2.forEach((x) -> out.print(x + " "));
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("snails.in"));
            out = new PrintWriter(new File("snails.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Edge {
        int from;
        int to;
        long flow = 0;
        long maxFlow;
        Edge back;
        boolean dir = false;
        int pos;
        boolean visited;

        Edge(int from, int to, long maxFlow, int pos) {
            this.from = from;
            this.to = to;
            this.maxFlow = maxFlow;
            this.pos = pos;
            this.dir = true;
        }

        Edge(int from, int to, long maxFlow, int pos, boolean reversed) {
            this.from = to;
            this.to = from;
            this.maxFlow = maxFlow;
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
    }
}