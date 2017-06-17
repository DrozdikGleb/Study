import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class task6 {
    public static int countPal(int[][] d, int left, int right, String str) {
        if (d[left][right] == -1) {
            if (str.charAt(left) == str.charAt(right)) {
                d[left][right] = countPal(d, left + 1, right - 1, str) + 2;
            } else {
                d[left][right] = Math.max(countPal(d, left + 1, right, str), countPal(d, left, right - 1, str));
            }
        }
        return d[left][right];
    }

    public static void pal(int[][] d, int left, int right, int leftP, int rightP, String[] pal, String str) {
        while (left <= right) {
            if ((left == right) && d[left][right] == 1) {
                pal[leftP++] = Character.toString(str.charAt(left++));
            } else {
                if (str.charAt(left) == str.charAt(right)) {
                    pal[leftP++] = Character.toString(str.charAt(left++));
                    pal[rightP--] = Character.toString(str.charAt(right--));
                } else {
                    if (d[left + 1][right] > d[left][right - 1]) {
                        left++;
                    } else {
                        right--;
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("palindrome.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("palindrome.out"));
        String str = in.nextLine();
        int l = str.length();
        int[][] d = new int[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (i == j) d[i][j] = 1;
                if (i > j) d[i][j] = 0;
                if (i < j) d[i][j] = -1;
            }
        }
        countPal(d, 0, l - 1, str);
        int lenP = d[0][l - 1];
        String[] pal = new String[lenP];
        pal(d, 0, l - 1, 0, lenP - 1, pal, str);
        out.write(String.valueOf(lenP)+"\n");
        for (int i = 0; i < lenP; i++) {
            out.write(String.valueOf(pal[i]) + "");
        }

        out.close();
    }
}
