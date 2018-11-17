package algorithms.secondyear.flowsandmatchings;

import java.io.*;
import java.util.*;

public class MinCut {
    long[] d;
    int[] p;
    int n;
    int m;
    int[][] grafMatrix;
    boolean[] used;
    long INF = Long.MAX_VALUE;
    private FastScanner in;
    private PrintWriter out;
    private ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> graf = new ArrayList<>();

    public static void main(String[] arg) {
        new MinCut().run();
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
        d = new long[n];
        p = new int[n];
        used = new boolean[n];
        grafMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            addEdge(from, to, in.nextInt(), i);
            grafMatrix[from][to] = grafMatrix[to][from] = i + 1;
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

    private long dfs(int u, long min) {
        if (u == n - 1 || min == 0) {
            return min;
        }
        for (int v = p[u]; v < edges.get(u).size(); v++) {
            Edge curEdge = edges.get(u).get(v);
            if (d[curEdge.to] == d[u] + 1) {
                long delta = dfs(curEdge.to, Math.min(min, curEdge.maxFlow - curEdge.flow));
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

    private void simpleDfs(int v) {
        used[v] = true;
        for (int i = 0; i < edges.get(v).size(); i++) {
            Edge cur = edges.get(v).get(i);
            if (!used[cur.to] && cur.flow < cur.maxFlow) {
                simpleDfs(cur.to);
            }
        }
    }

    private long dinica() {
        long maxFlow = 0;
        while (bfs()) {
            Arrays.fill(p, 0);
            long flow = dfs(0, INF);
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
        long answer = dinica();
        simpleDfs(0);
        ArrayList<Integer> leftVertex = new ArrayList<>();
        ArrayList<Integer> rightVertex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (used[i]) {
                leftVertex.add(i);
            } else {
                rightVertex.add(i);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < leftVertex.size(); i++) {
            for (int j = 0; j < rightVertex.size(); j++) {
                if (grafMatrix[leftVertex.get(i)][rightVertex.get(j)] > 0) {
                    result.add(grafMatrix[leftVertex.get(i)][rightVertex.get(j)]);
                }
            }
        }
        Collections.sort(result);
        out.println(result.size() + " " + answer);
        result.forEach((x) -> out.print(x + " "));
    }

    public void run() {
        try {
            in = new FastScanner(new File("cut.in"));
            out = new PrintWriter(new File("cut.out"));

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