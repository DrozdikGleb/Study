package algorithms.secondyear.strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MostCommonSubstring {
    private FastScanner in;
    private PrintWriter out;
    private int[] count;
    private int n;
    private String str;
    private String str2;
    private int[] classes;
    private int[] order;
    private int[] lcp;
    int firstDivPos;
    int secondDivPos;

    private int getValue(int i) {
        return ((int) str.charAt(i) - 95);
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
        str = in.next();
        str2 = in.next();
        firstDivPos = str.length();
        str += "`";
        str+=str2;
        secondDivPos = str.length();
        str+="_";
        n = str.length();
        count = new int[28];
        order = new int[n];
        classes = new int[n];
        for (int i = 0; i < 28; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            count[getValue(i)]++;
        }
        for (int i = 1; i < 28; i++) {
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
        initialization();
        int L = 1;
        while (L < n) {
            countingSort(L);
            updateClasses(L);
            L *= 2;
        }
        int ans=0;
        getLCP();
        int ansStart=0;
        for (int i = 1; i <n-1 ; i++) {
            int firstSuf = order[i];
            int secondSuf = order[i+1];
            if((firstSuf>=firstDivPos&&secondSuf<firstDivPos)||(firstSuf<firstDivPos&&secondSuf>=firstDivPos)){
                if(ans<lcp[i]){
                    ans=lcp[i];
                    ansStart = firstSuf;
                }
            }
        }
        String ansStr = str.substring(ansStart,ansStart+ans);
        out.print(ansStr);
    }

    public void run() {
        try {
            in = new FastScanner(new File("common.in"));
            out = new PrintWriter(new File("common.out"));
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

    public static void main(String[] arg) {
        new MostCommonSubstring().run();
    }
}

