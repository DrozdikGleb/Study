package algorithms.secondyear.strings;

import java.io.*;
import java.util.StringTokenizer;

public class CycleSuffix {
    private FastScanner in;
    private PrintWriter out;
    private int[] count;
    private int n;
    private String str;
    private int[] classes;
    private int[] order;
    private int[] lcp;

    public static void main(String[] arg) {
        new CycleSuffix().run();
    }

    private int getValue(int i) {
        return ((int) str.charAt(i) - 96);
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

    private void initialization() {
        str = in.next();
        //str+="`";
        str += str;
        System.out.println(str);
        n = str.length();
        count = new int[27];
        order = new int[n];
        classes = new int[n];
        for (int i = 0; i < 27; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            count[getValue(i)]++;
        }
        for (int i = 1; i < 27; i++) {
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
        for (int i = 0; i < n; i++) {
            System.out.print(order[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(classes[i] + " ");
        }
        /*int ans=0;
        for (int i = 0; i <n ; i++) {
            if(order[i]==0){
                ans=i+1;
                break;
            }
        }*/
        // out.println(ans);
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

