package discretemaths.secondyear.matroids;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Cycles {
    FastScanner in;
    PrintWriter out;
    ArrayList<Element> elements = new ArrayList<>();
    HashMap<Integer, ArrayList<Integer>> checkSet = new HashMap<>();

    public static void main(String[] arg) {
        new Cycles().run();
    }

    private boolean checkSubsets(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return true;
        }
        int c = arrayList.size();
        for (int i = 1; i < (1 << c) - 1; i++) {
            int cur = 0;
            for (int j = 0; j < c; j++) {
                if ((i & (1 << j)) > 0) {
                    cur += (1 << arrayList.get(j));
                }
            }
            if (checkSet.containsKey(cur)) {
                return false;
            }
        }
        return true;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < n; i++) {
            int weight = in.nextInt();
            elements.add(new Element(i, weight));
        }

        elements.sort(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return Integer.compare(o2.weight, o1.weight);
            }
        });
        for (int i = 0; i < m; i++) {
            int len = in.nextInt();
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                arrayList.add(in.nextInt() - 1);
            }
            int set = 0;
            for (int j = 0; j < len; j++) {
                set += (1 << arrayList.get(j));
            }
            checkSet.put(set, arrayList);
        }
        int set = 0;
        long answer = 0;
        ArrayList<Integer> currentSub = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!checkSet.containsKey(set + (1 << elements.get(i).pos))) {
                currentSub.add(elements.get(i).pos);
                if (!checkSubsets(currentSub)) {
                    currentSub.remove(currentSub.size() - 1);
                    continue;
                }
                set += (1 << elements.get(i).pos);
                answer += elements.get(i).weight;
            }
        }
        out.println(answer);
    }

    public void run() {
        try {
            in = new FastScanner(new File("cycles.in"));
            out = new PrintWriter(new File("cycles.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Element {
        int pos;
        int weight;

        Element(int pos, int weight) {
            this.pos = pos;
            this.weight = weight;
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

