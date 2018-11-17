package discretemaths.firstyear.combinatorics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T21 {


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("out.txt"));
        String a = in.nextLine();
        int n = a.length();
        int ans[] = new int[103];
        String numb = "";
        int mas2[] = new int[103];
        int l = 0;
        int sum = 0;
        int e = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != '+') {
                numb += a.charAt(i);
            } else {
                mas2[l] = Integer.parseInt(numb);
                if (mas2[l] == 1) {
                    e++;
                }
                sum += mas2[l];
                l++;
                numb = "";
            }
            if (i == a.length() - 1) {
                mas2[l] = Integer.parseInt(numb);
                if (mas2[l] == 1) {
                    e++;
                }
                sum += mas2[l];
                l++;
                numb = "";
            }
        }

        for (int i = 0; i < sum + 2; i++) {
            ans[i] = 1;
        }
        int size = sum + 2;
        int k = 0;
        int itog = 0;
        if (e == sum) {
            itog = 0;
        } else {
            laba:
            while (ans[2] != sum) {
                ans[size - 1]--;
                ans[size - 2]++;
                if (ans[size - 2] > ans[size - 1]) {
                    ans[size - 2] += ans[size - 1];
                    size--;
                } else {
                    while (ans[size - 2] * 2 <= ans[size - 1]) {
                        ans[size] = ans[size - 1] - ans[size - 2];
                        ans[size - 1] = ans[size - 2];
                        size++;
                    }
                }
                itog++;

                if ((size - 2 == l) && (mas2[l] == ans[l])) {
                    for (int j = 0; j < l; j++) {
                        if (mas2[j] == ans[2 + j]) {
                            k++;
                        } else break;
                    }
                }
                if (k == l) {
                    break laba;
                }
                k = 0;
            }

        }
        out.write(String.valueOf(itog));

        out.close();

    }
}


