package algorithms.secondyear.strings;

/**
 * Created by Gleb on 27.12.2017
 */

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MostCommonSubstring3 {
    StringBuilder string;
    int globalM;
    private FastScanner in;
    private PrintWriter out;
    private int[] count;
    private int n;
    private String str;
    // private int [] checkKolSuf;
    private String str2;
    private int[] classes;
    private int[] order;
    private int[] lcp;
    private int[] symbolOfWord;
    private int[] treeArr;
    private int size;

    public static void main(String[] arg) {
        new MostCommonSubstring3().run();
    }

    private void buildTree(int[] arrayT, int node, int left, int right) {
        if (left == right) {
            treeArr[node] = arrayT[left];
        } else {
            int mid = (left + right) / 2;
            buildTree(arrayT, 2 * node + 1, left, mid);
            buildTree(arrayT, 2 * node + 2, mid + 1, right);
            treeArr[node] = Math.min(treeArr[2 * node + 1], treeArr[2 * node + 2]);
        }
    }

    private int get(int node, int treeLeft, int treeRight, int left, int right) {
        if (left > treeRight || right < treeLeft) {
            return Integer.MAX_VALUE;
        }
        if (treeLeft >= left && treeRight <= right) {
            return treeArr[node];
        } else {
            int m = (treeLeft + treeRight) / 2;
            return Math.min(get(2 * node + 1, treeLeft, m, left, right), get(2 * node + 2, m + 1, treeRight, left, right));
        }
    }

    private int getMin(int left, int right) {
        return get(0, 0, size - 2, left, right);
    }

    private int getValue(int i) {
        return ((int) string.charAt(i) - (97 - globalM));
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
        lcp = new int[n - 1];
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
            while (getValue(i + x) == getValue(order[rp[i] + 1] + x)) {
                x++;
            }
            lcp[rp[i]] = x;
        }
    }

    private void initialization() {
        int m = in.nextInt();
        string = new StringBuilder();
        globalM = m;
       /* for (int i = 0; i <m ; i++) {
            checkNumber+=(1<<(i));
        }
        checkKolSuf = new int[m];*/
        //str="";
        str = in.next();
        string.append(str);
        for (int i = 1; i < m; i++) {
            str2 = in.next();
            //str+=(char)(97-i);
            string.append((char) (97 - i));
            //str+=str2;
            string.append(str2);
        }
        //str+=(char)(97-m);
        string.append((char) (97 - m));
        symbolOfWord = new int[string.length()];
        int kk = 1;
        for (int i = 0; i < string.length(); i++) {
            if ((int) string.charAt(i) >= 97) {
                symbolOfWord[i] = kk;
            } else {
                symbolOfWord[i] = -1;
                kk++;
            }
        }
        //str2 = in.next();
        n = string.length();
        size = n;
        treeArr = new int[4 * size];
        count = new int[36];
        order = new int[n];
        classes = new int[n];
        for (int i = 0; i < 36; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            count[getValue(i)]++;
        }
        for (int i = 1; i < 36; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int c = getValue(i);
            count[c]--;
            order[count[c]] = i;
        }
        classes[order[0]] = 0;
        for (int i = 1; i < n; i++) {
            classes[order[i]] = getValue(order[i]) != getValue(order[i - 1]) ? classes[order[i - 1]] + 1 : classes[order[i - 1]];
        }
    }

    public void solve() throws IOException {
        int pos = 0;
        initialization();
        int L = 1;
        while (L < n) {
            countingSort(L);
            updateClasses(L);
            L *= 2;
        }
        int ans = 0;
        getLCP();
        buildTree(lcp, 0, 0, size - 2);
        int start = globalM;
        HashMap<Integer, Integer> checkUnique = new HashMap<>();
        for (int i = globalM; i < n - 1; i++) {
            int firstSuf = order[i];
            if (symbolOfWord[firstSuf] == -1) continue;
            if (!checkUnique.containsKey(symbolOfWord[firstSuf])) {
                checkUnique.put(symbolOfWord[firstSuf], 1);
            }
            boolean check = false;
            while (checkUnique.size() != globalM) {
                start++;
                if (start >= n) {
                    check = true;
                    break;
                }
                int next = order[start];
                if (!checkUnique.containsKey(symbolOfWord[next])) {
                    checkUnique.put(symbolOfWord[next], 1);
                } else {
                    checkUnique.put(symbolOfWord[next], checkUnique.get(symbolOfWord[next]) + 1);
                }
            }
            if (!check) {
                int min = getMin(i, start - 1);
                if (ans < min) {
                    ans = min;
                    pos = firstSuf;
                }
            } else {
                break;
            }
            if (checkUnique.get(symbolOfWord[firstSuf]) == 1) {
                checkUnique.remove(symbolOfWord[firstSuf]);
            } else {
                checkUnique.put(symbolOfWord[firstSuf], checkUnique.get(symbolOfWord[firstSuf]) - 1);
            }
            // System.out.println(ans);
        }
        //System.out.println(ans);
        out.print(string.substring(pos, pos + ans));
    }

    public void run() {
        try {
            in = new FastScanner(new File("substr3.in"));
            out = new PrintWriter(new File("substr3.out"));
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
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


