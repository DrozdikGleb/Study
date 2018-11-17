package algorithms.secondyear.mincostflow;

/**
 * Created by Gleb on 28.04.2018
 */


import java.io.*;
import java.util.*;

public class MinCostFlow {
    long[] d;
    int[] p;
    int n;
    int m;
    long INF = Long.MAX_VALUE;
    Edge[] minCostWay;
    Queue<Pair> queue;
    private FastScanner in;
    private PrintWriter out;
    private ArrayList<Edge>[] edges = new ArrayList[101];

    public static void main(String[] arg) {
        new MinCostFlow().run();
    }

    private void addEdge(int from, int to, int maxFlow, int price) {
        Edge e1 = new Edge(from, to, maxFlow, price);
        Edge e2 = new Edge(from, to, 0, -price, true);
        e1.back = e2;
        e2.back = e1;
        edges[from].add(e1);
        edges[to].add(e2);
    }

    private void initialize() {
        n = in.nextInt();
        m = in.nextInt();
        minCostWay = new Edge[n];
        d = new long[n];
        p = new int[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            addEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt(), in.nextInt());
        }
    }

    private boolean bfs() {
        Arrays.fill(d, INF);
        d[0] = 0;
        queue = new PriorityQueue<>();
        queue.add(new Pair(0, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (-pair.first > d[pair.second]) continue;
            if (d[pair.second] == INF) continue;
            for (int i = 0; i < edges[pair.second].size(); i++) {
                Edge e = edges[pair.second].get(i);
                if (e.flow < e.maxFlow && d[e.from] != INF && d[e.to] > d[pair.second] + e.price) {
                    d[e.to] = d[pair.second] + e.price;
                    p[e.to] = pair.second;
                    queue.add(new Pair(-d[e.to], e.to));
                    minCostWay[e.to] = e;
                }
            }
        }
        return d[n - 1] != INF;
    }

    public void solve() throws IOException {
        initialize();
        long answer = 0;
        while (bfs()) {
            long maxFlow = Long.MAX_VALUE;
            for (int v = n - 1; v != 0; v = p[v]) {
                Edge curEdge = minCostWay[v];
                maxFlow = Math.min(maxFlow, curEdge.maxFlow - curEdge.flow);
            }
            for (int v = n - 1; v != 0; v = p[v]) {
                Edge curEdge = minCostWay[v];
                answer += maxFlow * curEdge.price;
                curEdge.flow += maxFlow;
                curEdge.back.flow -= maxFlow;
            }
        }
        out.print(answer);
    }

    public void run() {
        try {
            in = new FastScanner(new File("mincost.in"));
            out = new PrintWriter(new File("mincost.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Pair implements Comparable<Pair> {
        long first;
        int second;

        Pair(long first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.first > o.first) {
                return 1;
            } else if (this.first < o.first) {
                return -1;
            }
            return 0;
        }
    }

    class Edge {
        int from;
        int to;
        int flow = 0;
        int maxFlow;
        Edge back;
        int price;

        Edge(int from, int to, int maxFlow, int price) {
            this.from = from;
            this.to = to;
            this.maxFlow = maxFlow;
            this.price = price;
        }

        Edge(int from, int to, int maxFlow, int price, boolean reversed) {
            this.from = to;
            this.to = from;
            this.maxFlow = maxFlow;
            this.price = price;
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