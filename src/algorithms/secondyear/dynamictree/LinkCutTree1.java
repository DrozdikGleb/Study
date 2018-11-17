package algorithms.secondyear.dynamictree;

/**
 * Created by Gleb on 13.11.2017
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class LinkCutTree1 {
    private ArrayList<HashMap<Integer, Node>> edgePos;
    private Node[] Nodes;
    private FastScanner in;

    public static void main(String[] arg) {
        new LinkCutTree1().run();
    }

    private void set_parent(Node child, Node parent) {
        if (child != null) {
            child.parent = parent;
        }
    }

    private void keep_parent(Node v) {
        set_parent(v.left, v);
        set_parent(v.right, v);
    }

    private void rotate(Node parent, Node child) {
        Node g = parent.parent;
        if (g != null) {
            if (g.left == parent) {
                g.left = child;
            } else {
                g.right = child;
            }
        }
        if (parent.left == child) {
            parent.left = child.right;
            child.right = parent;
        } else {
            parent.right = child.left;
            child.left = parent;
        }
        keep_parent(child);
        keep_parent(parent);
        child.parent = g;
    }

    private void splay(Node v) {
        if (v.parent == null) {
            return;
        }
        Node parent = v.parent;
        Node gparent = parent.parent;
        if (gparent == null) {
            rotate(parent, v);
            return;
        } else {
            //zig-zig
            if ((gparent.left == parent) && (parent.left == v)) {
                rotate(gparent, parent);
                rotate(parent, v);
            }
            //zig-zag
            else {
                rotate(parent, v);
                rotate(gparent, v);
            }
        }
        splay(v);
    }

    private TreesAfterSplit split(Node v) {
        splay(v);
        Node T1;
        Node T2;
        T1 = v;
        if (v.right == null) {
            T2 = null;
        } else {
            T2 = T1.right;
            T2.parent = null;
            T1.right = null;
        }
        return new TreesAfterSplit(T1, T2);
    }

    private Node findMax(Node u) {
        Node v = u;
        while (v.right != null) {
            v = v.right;
        }
        return v;
    }

    private Node merge(Node T1, Node T2) {
        if (T2 == null) return T1;
        if (T1 == null) return T2;
        Node maxT1 = findMax(T1);
        splay(maxT1);
        maxT1.right = T2;
        T2.parent = maxT1;
        return maxT1;
    }

    private boolean isConnected(Node a, Node b) {
        Node rootA = a;
        while (rootA.parent != null) {
            rootA = rootA.parent;
        }
        Node rootB = b;
        while (rootB.parent != null) {
            rootB = rootB.parent;
        }
        return rootA == rootB;
    }

    private boolean XLessY(Node x, Node y) {
        splay(y);
        while (x.parent != y) {
            x = x.parent;
        }
        return x.parent.left == x;
    }

    private void link(int a, int b) {
        Node ab = new Node(a, b);
        Node ba = new Node(b, a);
        edgePos.get(a).put(b, ab);
        edgePos.get(b).put(a, ba);
        TreesAfterSplit AT = split(Nodes[a]);
        Node A1 = AT.T1;
        Node A2 = AT.T2;
        TreesAfterSplit BT = split(Nodes[b]);
        Node B1 = BT.T1;
        Node B2 = BT.T2;
        Node T = merge(A1, ab);
        T = merge(T, B2);
        T = merge(T, B1);
        T = merge(T, ba);
        merge(T, A2);
    }

    private void cut(int a, int b) {
        Node x = edgePos.get(a).get(b);
        Node y = edgePos.get(b).get(a);
        boolean check = XLessY(x, y);
        if (check) {
            TreesAfterSplit A = split(x);
            Node A1 = A.T1;//A1
            TreesAfterSplit AA = split(y);
            Node A2 = AA.T1;//A2
            Node A3 = AA.T2;//A3
            Node Tree = merge(A1, A3);
        } else {
            TreesAfterSplit ATCUT = split(y);
            Node A1cut = ATCUT.T1;//A1
            TreesAfterSplit A2TCUT = split(x);
            Node A2cut = A2TCUT.T1;//A2
            Node A3cut = A2TCUT.T2;//A3
            //merge A1A3
            Node Tree = merge(A1cut, A3cut);
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        edgePos = new ArrayList<>();
        Nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            Nodes[i] = new Node(i, i);
            edgePos.add(new HashMap<>());
        }
        for (int i = 0; i < m; i++) {
            String query = in.next();
            int a;
            int b;
            switch (query) {
                case "link":
                    a = in.nextInt() - 1;
                    b = in.nextInt() - 1;
                    link(a, b);
                    break;
                case "cut":
                    a = in.nextInt() - 1;
                    b = in.nextInt() - 1;
                    cut(a, b);
                    break;
                case "connected":
                    a = in.nextInt() - 1;
                    b = in.nextInt() - 1;
                    if (isConnected(Nodes[a], Nodes[b])) {
                        System.out.println(1);
                    } else {
                        System.out.println(0);
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

    class TreesAfterSplit {
        Node T1;
        Node T2;

        TreesAfterSplit(Node T1, Node T2) {
            this.T1 = T1;
            this.T2 = T2;
        }
    }

    class Node {
        Node left;
        Node right;
        Node parent;
        int a;
        int b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
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

