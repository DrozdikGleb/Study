import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class T6 {

    public static void gen(int k, int l, int n, String[] ans,OutputStreamWriter out)throws IOException {
        if (k == n) {
            for (int i = 0; i < n; i++) {
                out.write(ans[i]);
            }
            out.write("\n");
            return;
        }
        if (l == 0) {
            ans[k] = "0";
            gen(k + 1, 0, n, ans,out);
            ans[k] = "1";
            gen(k + 1, 1, n, ans,out);
        } else {
            ans[k] = "0";
            gen(k + 1, 0, n, ans,out);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("vectors.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("vectors.out"));
        int n = in.nextInt();
        int func[] = new int[18];
        func[0] = 1;
        func[1] = 1;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            ans[i] = "0";
        }
        for (int i = 2; i <= n + 1; i++)
            func[i] = func[i - 1] + func[i - 2];

        out.write(String.valueOf(func[n + 1])+"\n");
        gen(0, 0, n, ans,out);
        out.close();
    }

}