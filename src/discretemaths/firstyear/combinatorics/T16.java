package discretemaths.firstyear.combinatorics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class T16 {
    public static BigInteger factorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    public static BigInteger choose(int n, int k) {
        return (factorial(n).divide(factorial(k).multiply(factorial(n - k))));
    }

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("choose2num.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("choose2num.out"));
        int n = in.nextInt();
        int k = in.nextInt();
        int soch[] = new int[k + 2];
        for (int i = 1; i < k + 1; i++) {
            soch[i] = in.nextInt();
        }
        soch[0] = 0;
        int ans = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = soch[i - 1] + 1; j < soch[i]; j++) {
                ans += choose(n - j, k - i).intValue();
            }

        }
        out.write(String.valueOf(ans));
        out.close();
    }
}
