import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T10 {


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("out.txt"));
        int n = in.nextInt();
        int ans[] = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            out.write(String.valueOf(ans[i]));
            if (i != n - 1) {
                out.write("+");
            }
        }
        out.write("\n");
        int size = n;
        int k=0;

        while (ans[0] != n) {
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
            boolean check=false;
            for (int i = 0; i <size ; i++) {
                if (ans[i]%2==0){
                    check=true;
                    break;
                }
            }
if (!check) {
    k++;
    for (int i = 0; i < size; i++) {
        out.write(String.valueOf(ans[i]));
        if (i != size - 1) {
            out.write("+");
        }
    }
    out.write("\n");
}
        }

   out.write(String.valueOf(k+1));
        out.close();

    }
}

