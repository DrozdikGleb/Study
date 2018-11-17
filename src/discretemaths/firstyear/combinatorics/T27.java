package discretemaths.firstyear.combinatorics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T27 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextbrackets.in "));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("nextbrackets.out"));
        String s = in.nextLine();
        int l = s.length();
        char[] ans = s.toCharArray();
        int open = 0;
        int close = 0;

        for (int i = l - 1; i >= 0; i--) {
            if (ans[i] == '(') {
                open++;
                if (close > open) break;
            } else close++;
        }
        if (l == open + close) {
            out.write("-");
        } else {
            ans[s.length() - open - close] = ')';
            for (int i = s.length() - open - close + 1; i < s.length() - close + 1; i++) {
                ans[i] = '(';
            }
            for (int i = s.length() - close + 1; i < s.length(); i++) {
                ans[i] = ')';
            }
            for (int i = 0; i < l; i++) {
                out.write(ans[i]);
            }
        }

        out.close();

    }
}
