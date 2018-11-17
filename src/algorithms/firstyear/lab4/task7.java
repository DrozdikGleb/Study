package algorithms.firstyear.lab4;

import java.io.IOException;
import java.util.Scanner;

public class task7 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String s1, s2;
        s1 = in.nextLine();
        s2 = in.nextLine();
        boolean b = false;
        if ((s1.length() > 0) && (s2.length() > 0)) {
            b = true;
        }
        String ans = "";

        int l1 = s1.length();
        int l2 = s2.length();
        if (b) {
            boolean d[][] = new boolean[l1 + 1][l2 + 1];
            d[0][0] = true;
            for (int i = 1; i < l2 + 1; i++) {
                d[0][i] = false;
            }
            for (int i = 1; i < l1 + 1; i++) {
                d[i][0] = false;
            }
            for (int i = 1; i < l1 + 1; i++) {
                if (s1.charAt(i - 1) == '*') {
                    d[i][0] = d[i - 1][0];
                }
            }

            for (int i = 1; i < l1 + 1; i++) {
                for (int j = 1; j < l2 + 1; j++) {
                    if (s1.charAt(i - 1) == '*') {
                        d[i][j] = (d[i - 1][j - 1] || d[i - 1][j] || d[i][j - 1]);

                    } else if (s1.charAt(i - 1) == '?') {
                        d[i][j] = d[i - 1][j - 1];
                    } else {
                        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                            d[i][j] = d[i - 1][j - 1];
                        }
                    }
                }
            }
            if ((d[l1][l2])) {
                ans = "YES";
            } else {
                ans = "NO";
            }
        } else {
            if (s2.length() == 0 && s1.length() > 0) {
                boolean check = false;
                for (int i = 0; i < s1.length(); i++)
                    if (s1.charAt(i) != '*') {
                        check = true;
                        break;
                    }
                if (!check)
                    ans = "YES";
                else ans = "NO";
            } else if (s2.length() > 0 && s1.length() == 0) {
                ans = "NO";
            } else ans = "YES";
        }

        System.out.println(ans);
    }
}
