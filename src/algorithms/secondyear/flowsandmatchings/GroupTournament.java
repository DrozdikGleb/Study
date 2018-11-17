package algorithms.secondyear.flowsandmatchings;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GroupTournament {
    int vertexs = 100 + 100 * 100 + 2;
    int[] d;
    int[] p;
    int n;
    int m;
    int allMathces;
    int[] curPoints;
    char[][] answer;
    int INF = Integer.MAX_VALUE;
    HashMap<Integer, Vertex> games = new HashMap<>();
    private FastScanner in;
    private ArrayList<ArrayList<Edge>> edges = new ArrayList<>();

    public static void main(String[] arg) {
        new GroupTournament().run();
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
        answer = new char[n][n];
        d = new int[vertexs];
        p = new int[vertexs];
        curPoints = new int[n];
        for (int i = 0; i < vertexs; i++) {
            edges.add(new ArrayList<>());
        }
        int match = 101;
        for (int i = 0; i < n; i++) {
            answer[i][i] = '#';
            String str = in.next();
            for (int j = i + 1; j < n; j++) {
                switch (str.charAt(j)) {
                    case '.':
                        allMathces++;
                        addEdge(0, match, 3, 0);
                        addEdge(match, i + 1, 3, 0);
                        addEdge(match, j + 1, 3, 0);
                        games.put(match, new Vertex(i + 1, j + 1));
                        match++;
                        break;
                    case 'W':
                        answer[i][j] = 'W';
                        answer[j][i] = 'L';
                        curPoints[i] += 3;
                        break;
                    case 'w':
                        answer[i][j] = 'w';
                        answer[j][i] = 'l';
                        curPoints[i] += 2;
                        curPoints[j] += 1;
                        break;
                    case 'L':
                        answer[i][j] = 'L';
                        answer[j][i] = 'W';
                        curPoints[j] += 3;
                        break;
                    case 'l':
                        answer[i][j] = 'l';
                        answer[j][i] = 'w';
                        curPoints[i] += 1;
                        curPoints[j] += 2;
                        break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int point = in.nextInt();
            addEdge(i + 1, vertexs - 1, point - curPoints[i], 0);
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
        return d[vertexs - 1] != INF;
    }

    private int dfs(int u, int min) {
        if (u == vertexs - 1 || min == 0) {
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

    private char getSymb(int num) {
        char symb;
        switch (num) {
            case 0:
                symb = 'L';
                break;
            case 1:
                symb = 'l';
                break;
            case 2:
                symb = 'w';
                break;
            case 3:
                symb = 'W';
                break;
            default:
                symb = '/';
        }
        return symb;
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
        initialize();
        int a = dinica();
        for (int i = 101; i < 101 + allMathces; i++) {
            ArrayList<Edge> curEdges = edges.get(i);
            for (int j = 1; j < curEdges.size(); j++) {
                Edge curEdge = curEdges.get(j);
                Vertex game = games.get(i);
                if (curEdge.to == game.first) {
                    answer[game.first - 1][game.second - 1] = getSymb(curEdge.flow);
                } else {
                    answer[game.second - 1][game.first - 1] = getSymb(curEdge.flow);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(answer[i][j]);
            }
            System.out.println();
        }

    }

    public void run() {
        try {
            in = new FastScanner();

            solve();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Vertex {
        int first;
        int second;

        Vertex(int first, int second) {
            this.first = first;
            this.second = second;
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

        FastScanner() {

            br = new BufferedReader(new InputStreamReader(System.in));

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