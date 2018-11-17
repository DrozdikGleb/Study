package algorithms.secondyear.flowsandmatchings;

import java.io.*;
import java.util.*;

public class SimpleFlow {
    int[] d;
    int[] p;
    int n;
    int m;
    int INF = Integer.MAX_VALUE;
    private FastScanner in;
    private PrintWriter out;
    private ArrayList<ArrayList<Edge>> edges = new ArrayList<>();

    public static void main(String[] arg) {
        new SimpleFlow().run();
    }

    private void addEdge(int from, int to, int maxFlow, int pos) {
        Edge e1 = new Edge(from, to, maxFlow, pos);
        Edge e2 = new Edge(from, to, maxFlow, pos, true);
        e1.back = e2;
        e2.back = e1;
        edges.get(from).add(e1);
        edges.get(to).add(e2);
    }

    private void initialize() {
        d = new int[n];
        p = new int[n];
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            addEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt(), i);
        }
    }

    private boolean bfs() {
        Arrays.fill(d, INF);
        d[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Edge e : edges.get(cur)) {
                if (e.flow < e.maxFlow && d[e.to] == INF) {
                    d[e.to] = d[cur] + 1;
                    queue.add(e.to);
                }
            }
        }
        return d[n - 1] != INF;
    }

    private int dfs(int u, int min) {
        if (u == n - 1 || min == 0) {
            return min;
        }
        for (int v = p[u]; v < edges.get(u).size(); v++) {
            Edge curEdge = edges.get(u).get(v);
            if (d[curEdge.to] == d[u] + 1) {
                int delta = dfs(curEdge.to, Math.min(min, curEdge.maxFlow - curEdge.flow));
                if (delta != 0) {
                    curEdge.flow += delta;
                    curEdge.back.flow -= delta;
                    return delta;
                }
            }
            p[u]++;
        }
        return 0;
    }

    private int dinica() {
        int maxFlow = 0;
        while (bfs()) {
            Arrays.fill(p, 0);
            int flow = dfs(0, INF);
            while (flow != 0) {
                maxFlow += flow;
                flow = dfs(0, INF);
            }
        }
        return maxFlow;
    }

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        initialize();
        int answer = dinica();
        out.println(answer);
        int[] speeds = new int[m];
        for (int i = 0; i < n; i++) {
            for (Edge e : edges.get(i)) {
                if (e.dir) {
                    speeds[e.pos] = e.flow;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            out.println(speeds[i]);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("flow.in"));
            out = new PrintWriter(new File("flow.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Edge {
        int from;
        int to;
        int flow = 0;
        int maxFlow;
        Edge back;
        boolean dir = false;
        int pos;

        Edge(int from, int to, int maxFlow, int pos) {
            this.from = from;
            this.to = to;
            this.maxFlow = maxFlow;
            this.pos = pos;
            this.dir = true;
        }

        Edge(int from, int to, int maxFlow, int pos, boolean reversed) {
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