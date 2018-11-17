package algorithms.secondyear.strings;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Refren {
    private FastScanner in;
    private PrintWriter out;
    private int[] count;
    private int n;
    private int[] str;
    private int[] classes;
    private int[] order;
    private int[] lcp;

    public static void main(String[] arg) {
        new Refren().run();
    }

    private void countingSort(int L) {
        int[] count2 = new int[n];
        int[] newOrder = new int[n];
        for (int i = 0; i < n; i++) {
            count2[classes[i]] += 1;
        }
        for (int i = 1; i < n; i++) {
            count2[i] += count2[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int start = (order[i] - L + n) % n;
            int c = classes[start];
            count2[c]--;
            newOrder[count2[c]] = start;
        }
        order = newOrder;
    }

    private void updateClasses(int L) {
        int[] nClasses = new int[n];
        nClasses[order[0]] = 0;
        for (int i = 1; i < n; i++) {
            int pair1_1 = order[i];
            int pair2_1 = order[i - 1];
            int pair1_2 = (pair1_1 + L) % n;
            int pair2_2 = (pair2_1 + L) % n;
            nClasses[pair1_1] = ((classes[pair1_1] == classes[pair2_1]) && (classes[pair1_2] == classes[pair2_2])) ? nClasses[pair2_1] : nClasses[pair2_1] + 1;
        }
        classes = nClasses;
    }

    private void getLCP() {
        lcp = new int[n];
        int[] rp = new int[n];
        for (int i = 0; i < n; i++) {
            rp[order[i]] = i;
        }
        int x = 0;
        for (int i = 0; i < n; i++) {
            x = Math.max(x - 1, 0);
            if (rp[i] == n - 1) {
                x = 0;
                continue;
            }
            while (str[i + x] == str[order[rp[i] + 1] + x]) {
                x++;
            }
            lcp[rp[i]] = x;
        }
    }

    private void initialization() {
        n = in.nextInt();
        int m = in.nextInt();
        str = new int[n + 1];
        for (int i = 0; i < n; i++) {
            str[i] = in.nextInt();
        }
        str[n] = 0;
        n += 1;
        count = new int[11];
        order = new int[n];
        classes = new int[n];
        for (int i = 0; i < 11; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            count[str[i]]++;
        }
        for (int i = 1; i < 11; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int c = str[i];
            count[c]--;
            order[count[c]] = i;
        }
        classes[order[0]] = 0;
        for (int i = 1; i < n; i++) {
            classes[order[i]] = str[order[i]] != str[order[i - 1]] ? classes[order[i - 1]] + 1 : classes[order[i - 1]];
        }
    }

    public void solve() throws IOException {
        initialization();
        int L = 1;
        while (L < n) {
            countingSort(L);
            updateClasses(L);
            L *= 2;
        }
        getLCP();
        ArrayList<Data> stack = new ArrayList<>();
        long maxL = n - 1;
        long maxC = 1;
        int first = 0;
        lcp[n - 1] = 0;
        for (int i = 1; i < n; i++) {
            int kol = 1;
            while (!stack.isEmpty() && lcp[i] <= stack.get(stack.size() - 1).length) {
                long length = stack.get(stack.size() - 1).length;
                kol += stack.get(stack.size() - 1).k;
                if (kol * length > maxL * maxC) {
                    maxC = kol;
                    maxL = length;
                    first = order[stack.get(stack.size() - 1).pos];
                }
                stack.remove(stack.size() - 1);
            }
            stack.add(new Data(kol, i, lcp[i]));
        }
        long ans = maxC * maxL;
        out.print(ans + "\n");
        out.print(maxL + "\n");
        for (int i = first; i < first + maxL; i++) {
            out.print(String.valueOf(str[i]) + " ");
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("refrain.in"));
            out = new PrintWriter(new File("refrain.out"));
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Data {
        long k;
        int pos;
        long length;

        Data(int k, int pos, int length) {
            this.k = k;
            this.pos = pos;
            this.length = length;
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

