package algorithms.firstyear.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class KORD {
    public static String dvm(long a) {
        String ch = "";
        if (a == 0) {
            return "00";
        }

        long k = 0;
        while (a != 0) {
            k = a % 2;
            ch += String.valueOf(k);
            a /= 2;
        }
        return ch;
    }

    public static String dv(long a, int l) {
        String ch = "";
        if (a == 0) {
            for (int i = 0; i < l; i++) {
                ch += "0";
            }
        }

        long k = 0;
        int flag = 0;
        while (a != 0) {
            k = a % 2;
            ch += String.valueOf(k);
            a /= 2;
            flag++;
        }
        if (flag < l) {
            for (int i = flag; i < l; i++) {
                ch += "0";
            }
        }
        return ch;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer t = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(t.nextToken());
        StringTokenizer t1 = new StringTokenizer(in.readLine());
        long numb[] = new long[n];
        long max = -1;
        String ss = "";
        String[] ch = new String[n];
        String[] comp = new String[33];
        for (int i = 0; i < 33; i++) {
            comp[i] = "";
        }
        for (int i = 0; i < n; i++) {
            numb[i] = Long.parseLong(t1.nextToken());
            if (numb[i] > max) {
                max = numb[i];
            }
        }
        String a = dvm(max);
        int length = a.length() + 1;
        for (int i = 0; i < n; i++) {
            ch[i] = dv(numb[i], length);
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < n; j++) {
                comp[i] += ch[j].charAt(i);
            }

        }
        StringTokenizer t2 = new StringTokenizer(in.readLine());
        long s = Long.parseLong(t2.nextToken());
        ss = dv(s, length);


        boolean check = true;
        boolean check2 = true;
        int k = 0;


        if (s == 0) {
            System.out.println("1&~1");
            check2 = false;

        } else {
            exit:
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    if ((comp[i].equals(comp[j])) && (ss.charAt(i) != ss.charAt(j))) {
                        check = false;
                        break exit;
                    }

                }
            }
        }
        if (dvm(s).length() > dvm(max).length()) {
            check = false;
        }
        String ans = "";

        if (!check) {
            System.out.println("Impossible");
        } else {

            laba:
            {
                if (check2 == false) break laba;

                for (int i = 0; i < length - 1; i++) {
                    if (ss.charAt(i) == '1') {
                        if (!(ans.length() == 0)) ans += "|";
                        for (int j = 0; j < n; j++) {
                            if (comp[i].charAt(j) == '0') {
                                ans += (char) 126;
                                ans += (j + 1);
                            }
                            if (comp[i].charAt(j) == '1') {
                                ans += (j + 1);
                            }
                            if (j != n - 1) {
                                ans += "&";
                            }
                        }
                    }
                }
                System.out.println(ans);
            }
        }


    }
}
