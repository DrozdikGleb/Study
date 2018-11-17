package discretemaths.secondyear.matroids;

import java.io.*;
import java.util.*;

public class Checking {
    FastScanner in;
    PrintWriter out;
    boolean aks1 = false;
    boolean aks2 = true;
    boolean aks3 = true;
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    ArrayList<ArrayList<Integer>> arrayOfSets = new ArrayList<>();

    public static void main(String[] arg) {
        new Checking().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int m1 = m;
        for (int i = 0; i < m1; i++) {
            int kol = in.nextInt();
            if (kol == 0) {
                aks1 = true;
                m--;
                map.put(0, null);
                continue;
            }
            ArrayList<Integer> arrayList = new ArrayList<>();
            int pos = 0;
            for (int j = 0; j < kol; j++) {
                int num = in.nextInt();
                arrayList.add(num);
                pos += (1 << (num));
            }
            map.put(pos, arrayList);
            arrayOfSets.add(arrayList);
        }
        if (aks1) {
            Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = map.entrySet().iterator();
            int t = 0;
            while (t < map.size()) {
                Map.Entry<Integer, ArrayList<Integer>> pair = it.next();
                ArrayList<Integer> array = pair.getValue();
                int k = 0;
                if (array == null) {
                    t++;
                    continue;
                }
                int c = array.size();
                for (int i = 0; i < (1 << c); i++) {
                    int cur = 0;
                    for (int j = 0; j < c; j++) {
                        if ((i & (1 << j)) > 0) {
                            cur += (1 << array.get(j));
                        }
                    }
                    if (map.containsKey(cur)) {
                        k++;
                    }
                }
                if (k != (1 << c)) {
                    aks2 = false;
                    break;
                }
                t++;
            }
            if (aks2) {
                for (int i = 0; i < m - 1; i++) {
                    ArrayList<Integer> firstSet = arrayOfSets.get(i);
                    for (int j = i + 1; j < m; j++) {
                        ArrayList<Integer> secondSet = arrayOfSets.get(j);
                        if (secondSet.size() > firstSet.size()) {
                            ArrayList<Integer> A_bez_B = new ArrayList<>();
                            boolean[] mas = new boolean[11];
                            for (int k = 0; k < secondSet.size(); k++) {
                                mas[secondSet.get(k)] = true;
                            }
                            for (int k = 0; k < firstSet.size(); k++) {
                                mas[firstSet.get(k)] = false;
                            }
                            for (int k = 0; k < 11; k++) {
                                if (mas[k]) {
                                    A_bez_B.add(k);
                                }
                            }
                            int c = firstSet.size();
                            int cur = 0;
                            for (int i1 = 0; i1 < c; i1++) {
                                cur += (1 << firstSet.get(i1));
                            }
                            int f = 0;
                            for (int k = 0; k < A_bez_B.size(); k++) {
                                if (!map.containsKey(cur + (1 << A_bez_B.get(k)))) {
                                    f++;
                                }
                            }
                            if (f == A_bez_B.size()) {
                                aks3 = false;
                                break;
                            }
                            continue;
                        }
                        if (secondSet.size() < firstSet.size()) {
                            ArrayList<Integer> A_bez_B = new ArrayList<>();
                            boolean[] mas = new boolean[11];
                            for (int k = 0; k < firstSet.size(); k++) {
                                mas[firstSet.get(k)] = true;
                            }
                            for (int k = 0; k < secondSet.size(); k++) {
                                mas[secondSet.get(k)] = false;
                            }
                            for (int k = 0; k < 11; k++) {
                                if (mas[k]) {
                                    A_bez_B.add(k);
                                }
                            }
                            int c = secondSet.size();
                            int cur = 0;
                            for (int i1 = 0; i1 < c; i1++) {
                                cur += (1 << secondSet.get(i1));
                            }
                            int f = 0;
                            for (int k = 0; k < A_bez_B.size(); k++) {
                                if (!map.containsKey(cur + (1 << A_bez_B.get(k)))) {
                                    f++;
                                }
                            }
                            if (f == A_bez_B.size()) {
                                aks3 = false;
                                break;
                            }
                        }
                    }
                    if (!aks3) {
                        break;
                    }
                }
                if (aks3) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            } else {
                out.println("NO");
            }

        } else {
            out.println("NO");
        }


    }

    public void run() {
        try {
            in = new FastScanner(new File("check.in"));
            out = new PrintWriter(new File("check.out"));

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

