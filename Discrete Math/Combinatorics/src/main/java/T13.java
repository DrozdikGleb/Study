import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T13 {
    public static long fact(long n) {
        long k = 1;
        while (n != 0) {
            k *= n;
            n--;
        }
        return k;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("out.txt"));
        int n = in.nextInt();
        long k = in.nextLong();
        long fill = 0;
        int ans[] = new int[n + 1];
        long was[] = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            was[i] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            fill = k / (fact(n - i));
            k %= fact(n - i);
            long free = 0;
            for (int j = 1; j < n + 1; j++) {
                if (was[j] == 0) {
                    free++;
                    if (free == fill + 1) {
                        ans[i] = j;
                        was[j] = 1;
                    }

                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            out.write(String.valueOf(ans[i]) + " ");
        }
        out.close();
    }
}
