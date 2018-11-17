package algorithms.secondyear.strings;

/**
 * Created by Gleb on 28.12.2017
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MultiSearch {
    private static final int SIZE = 26;
    FastScanner in;
    PrintWriter out;
    int[] patternNum;
    ArrayList<Vertex> bor;
    ArrayList<String> patterns;
    int n;

    public static void main(String[] arg) {
        new MultiSearch().run();
    }

    private void addString(String string) {
        int pos = 0;
        for (int i = 0; i < string.length(); i++) {
            int posInAlphabet = string.charAt(i) - (int) 'a';
            if (bor.get(pos).nextVertex[posInAlphabet] != -1) {
                bor.add(new Vertex(pos, posInAlphabet));
                bor.get(pos).nextVertex[posInAlphabet] = bor.size() - 1;
            }
            pos = bor.get(pos).nextVertex[posInAlphabet];
        }
        bor.get(pos).terminate = true;
        patterns.add(string);
        patternNum[pos] = bor.size() - 1;
    }

    private int getSuffLink(int v) {
        if (bor.get(v).suff_link == -1) {
            if (v == 0 || bor.get(v).parent == 0) {
                bor.get(v).suff_link = 0;
            } else {
                bor.get(v).suff_link = goTo(getSuffLink(bor.get(v).parent), bor.get(v).charToParent);
            }
        }
        return bor.get(v).suff_link;
    }

    private int goTo(int v, int ch) {
        if (bor.get(v).nextVertex[ch] != -1) {
            bor.get(v).move[ch] = bor.get(v).nextVertex[ch];
        } else {
            if (v == 0) {
                bor.get(v).move[ch] = 0;
            } else {
                bor.get(v).move[ch] = goTo(getSuffLink(v), ch);
            }
        }
        return bor.get(v).move[ch];
    }

    private void initialisation() {
        bor = new ArrayList<>();
        patterns = new ArrayList<>();
        bor.add(new Vertex(0, 0));
        n = in.nextInt();
        patternNum = new int[n];
        for (int i = 0; i < n; i++) {
            patternNum[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            addString(in.next());
        }

    }

    public void solve() throws IOException {
        initialisation();

    }

    public void run() {
        try {
            in = new FastScanner(new File("in"));
            out = new PrintWriter(new File("outt"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Vertex {
        int[] nextVertex;
        int[] move;
        int parent;
        int charToParent;
        boolean terminate;
        int suff_link;

        Vertex(int parent, int charToParent) {
            nextVertex = new int[SIZE];
            move = new int[SIZE];
            terminate = false;
            suff_link = -1;
            this.parent = parent;
            this.charToParent = charToParent;
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
