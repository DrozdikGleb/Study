package algorithms.secondyear.dynamictree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Gleb on 14.11.2017
 */
public class HeavyLightDecomposition2 {

    private SegmentTree[] Trees;
    private int[] height;
    private ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    private FastScanner in;
    private PrintWriter out;
    private int[] subTreeSize;
    private int[] depth;
    private int[] parent;
    private int[] chain;
    private int[] positionSegmentTree;
    private int curr_chain = 0;
    private int[] array;
    private int count = 0;
    private int pos = 0;
    private int start = 0;

    public static void main(String[] arg) throws IOException {
        new HeavyLightDecomposition2().run();
    }

    private int sizeDfs(int v, int parentDFS, int depthDFS) {
        subTreeSize[v] = 1;
        parent[v] = parentDFS;
        depth[v] = depthDFS;
        for (int i = 0; i < graf.get(v).size(); i++) {
            int to = graf.get(v).get(i);
            if (to != parentDFS) {
                subTreeSize[v] += sizeDfs(to, v, depthDFS + 1);
            }
        }
        return subTreeSize[v];
    }

    private void init(int n) {
        height = new int[n];
        subTreeSize = new int[n];
        positionSegmentTree = new int[n];
        chain = new int[n];
        array = new int[n];
        depth = new int[n];
        parent = new int[n];
        Trees = new SegmentTree[n];
        for (int i = 0; i < n; i++) {
            height[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graf.get(a).add(b);
            graf.get(b).add(a);
        }
        sizeDfs(0, 0, 0);
        buildHLD(0, -1);
    }

    private void buildHLD(int v, int parent) {
        if (parent == -1) {
            start = 0;
        }
        chain[v] = curr_chain;
        array[count] = height[v];
        positionSegmentTree[v] = count;
        count++;
        if (subTreeSize[v] == 1) {
            Trees[pos] = new SegmentTree(count, start);
            Trees[pos].buildTree(array, 0, 0, count - 1);
            pos++;
            count = 0;
            return;
        }
        int sc = -1;
        int max = -1;
        for (int i = 0; i < graf.get(v).size(); i++) {
            int to = graf.get(v).get(i);
            if (to != parent) {
                if (subTreeSize[to] > max) {
                    max = subTreeSize[to];
                    sc = graf.get(v).get(i);
                }
            }
        }
        if (sc != -1) {
            buildHLD(sc, v);
        }
        for (int i = 0; i < graf.get(v).size(); i++) {
            int to = graf.get(v).get(i);
            if (to != sc && to != parent) {
                curr_chain++;
                start = to;
                buildHLD(to, v);
            }
        }

    }

    public void solve() throws IOException {
        int n = in.nextInt();
        init(n);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            char sign = in.readOne();
            switch (sign) {
                case '!':
                    int tower = in.nextInt() - 1;
                    int h = in.nextInt();
                    Trees[chain[tower]].setValue(positionSegmentTree[tower], h);
                    break;
                case '?':
                    int a = in.nextInt() - 1;
                    int b = in.nextInt() - 1;
                    int max = -1;
                    while (chain[a] != chain[b]) {
                        if (depth[Trees[chain[a]].first] < depth[Trees[chain[b]].first]) {
                            max = Math.max(max, Trees[chain[b]].getMax(positionSegmentTree[Trees[chain[b]].first], positionSegmentTree[b]));
                            b = parent[Trees[chain[b]].first];
                        } else {
                            max = Math.max(max, Trees[chain[a]].getMax(positionSegmentTree[Trees[chain[a]].first], positionSegmentTree[a]));
                            a = parent[Trees[chain[a]].first];
                        }
                    }
                    if (depth[a] < depth[b]) {
                        max = Math.max(max, Trees[chain[a]].getMax(positionSegmentTree[a], positionSegmentTree[b]));
                    } else {
                        max = Math.max(max, Trees[chain[a]].getMax(positionSegmentTree[b], positionSegmentTree[a]));
                    }
                    out.print(String.valueOf(max + "\n"));
                    break;
            }
        }
    }

    public void run() throws IOException {
        in = new FastScanner(new File("lca.in"));
        out = new PrintWriter(new File("lca.out"));
        solve();
        out.close();
    }

    class SegmentTree {
        int[] treeArr;
        int first;
        int size;

        SegmentTree(int size, int first) {
            this.size = size;
            this.first = first;
            treeArr = new int[4 * size];
        }

        private void buildTree(int[] arrayT, int node, int left, int right) {
            if (left == right) {
                treeArr[node] = arrayT[left];
            } else {
                int mid = (left + right) / 2;
                buildTree(arrayT, 2 * node + 1, left, mid);
                buildTree(arrayT, 2 * node + 2, mid + 1, right);
                treeArr[node] = Math.max(treeArr[2 * node + 1], treeArr[2 * node + 2]);
            }
        }

        void setValue(int pos, int value) {
            set(0, 0, size - 1, pos, value);
        }

        private void set(int node, int left, int right, int pos, int value) {
            if (left == right) {
                treeArr[node] = value;
            } else {
                int m = (right + left) / 2;
                if (pos <= m) {
                    set(node * 2 + 1, left, m, pos, value);
                } else {
                    set(node * 2 + 2, m + 1, right, pos, value);
                }
                treeArr[node] = Math.max(treeArr[node * 2 + 1], treeArr[node * 2 + 2]);
            }
        }

        private int get(int node, int treeLeft, int treeRight, int left, int right) {
            if (left > treeRight || right < treeLeft) {
                return -1;
            }
            if (treeLeft >= left && treeRight <= right) {
                return treeArr[node];
            } else {
                int m = (treeLeft + treeRight) / 2;
                return Math.max(get(2 * node + 1, treeLeft, m, left, right), get(2 * node + 2, m + 1, treeRight, left, right));
            }
        }

        private int getMax(int left, int right) {
            return get(0, 0, size - 1, left, right);
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

        Character readOne() throws IOException {
            return (char) br.read();
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
