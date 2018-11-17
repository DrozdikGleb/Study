package algorithms.secondyear.dynamictree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Gleb on 11.11.2017
 */
public class HeavyLightDecomposition1 {
    private SegmentTree[] Trees;
    private int curr_chain = 0;
    private int[] chain;
    private int[] parent;
    private int[] depth;
    private int[] subTreeSize;
    private int[] positionSegmentTree;
    private FastScanner in;
    private ArrayList<ArrayList<Integer>> graf = new ArrayList<>();
    private int pos = 0;
    private int start;
    private int count = 0;

    public static void main(String[] arg) {
        new HeavyLightDecomposition1().run();
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

    private void buildHLD(int v, int parent) {
        chain[v] = curr_chain;
        positionSegmentTree[v] = count;
        count++;
        if (subTreeSize[v] == 1) {
            Trees[pos] = new SegmentTree(count, start);
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


    private void init(int n) {
        subTreeSize = new int[n];
        positionSegmentTree = new int[n];
        chain = new int[n];
        depth = new int[n];
        parent = new int[n];
        Trees = new SegmentTree[n];
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

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        init(n);
        for (int i = 0; i < m; i++) {
            char sign = in.readOne();
            switch (sign) {
                case 'Q':
                    int f = in.nextInt() - 1;
                    int l = in.nextInt() - 1;
                    int query;
                    if (depth[f] > depth[l]) {
                        query = f;
                    } else {
                        query = l;
                    }
                    System.out.println(Trees[chain[query]].getSum(positionSegmentTree[query]));
                    break;
                case 'P':
                    int a = in.nextInt() - 1;
                    int b = in.nextInt() - 1;
                    if (a == b) {
                        continue;
                    }
                    while (chain[a] != chain[b] && (parent[a] != 0 || parent[b] != 0)) {
                        if (depth[Trees[chain[a]].first] > depth[Trees[chain[b]].first]) {
                            Trees[chain[a]].setValue(positionSegmentTree[Trees[chain[a]].first], positionSegmentTree[a]);
                            a = parent[Trees[chain[a]].first];
                        } else {
                            Trees[chain[b]].setValue(positionSegmentTree[Trees[chain[b]].first], positionSegmentTree[b]);
                            b = parent[Trees[chain[b]].first];
                        }
                    }
                    if (parent[a] == 0 && parent[b] == 0) {
                        if (a != 0) {
                            Trees[chain[a]].setValue(positionSegmentTree[a], positionSegmentTree[a]);
                        }
                        if (b != 0) {
                            Trees[chain[b]].setValue(positionSegmentTree[b], positionSegmentTree[b]);
                        }
                    } else {
                        if (depth[a] < depth[b]) {
                            Trees[chain[a]].setValue(positionSegmentTree[a] + 1, positionSegmentTree[b]);
                        } else {
                            Trees[chain[b]].setValue(positionSegmentTree[b] + 1, positionSegmentTree[a]);
                        }
                    }
                    break;
            }
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

    class SegmentTree {
        int[] treeArr;
        int first;
        int size;

        SegmentTree(int size, int first) {
            this.size = size;
            this.first = first;
            treeArr = new int[4 * size];
            for (int i = 0; i < 4 * size; i++) {
                treeArr[i] = 0;
            }
        }


        void setValue(int left, int right) {
            set(0, 0, size - 1, left, right, 1);
        }

        private void set(int node, int treeLeft, int treeRight, int left, int right, int value) {
            if (left > treeRight || right < treeLeft) {
                return;
            }
            if (treeLeft >= left && treeRight <= right) {
                treeArr[node] += value;
            } else {
                int m = (treeRight + treeLeft) / 2;
                set(node * 2 + 1, treeLeft, m, left, right, value);
                set(node * 2 + 2, m + 1, treeRight, left, right, value);
            }

        }

        private int get(int node, int treeLeft, int treeRight, int pos) {
            if (treeLeft == treeRight) {
                return treeArr[node];
            } else {
                int m = (treeLeft + treeRight) / 2;
                if (pos <= m) {
                    return treeArr[node] + get(2 * node + 1, treeLeft, m, pos);
                } else {
                    return treeArr[node] + get(2 * node + 2, m + 1, treeRight, pos);
                }
            }
        }

        private int getSum(int pos) {
            return get(0, 0, size - 1, pos);
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
