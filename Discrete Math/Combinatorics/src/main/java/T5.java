import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T5 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("telemetry.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("telemetry.out"));
        int n = in.nextInt();
        int k = in.nextInt();
        int step = (int) Math.pow(k, n);
        int mas[][] = new int[step+1][n + 1];
        int count =1;

        for (int i = 0; i < n; i++) {
            int l = 0;
            int num = 0;
           laba: while (true) {
                while (num < k && l<step) {
                    for (int j=0;j<count;j++) {
                        mas[l][i] = num;
                        l++;
                    }
                    num++;
                }
                num--;
               while (num>= 0 && l<step) {
                   for (int j=0;j<count;j++) {
                       mas[l][i] = num;
                       l++;
                   }
                   num--;
               }
               num++;
               if (l==step){
                   break laba;
               }

            }
            count *=k;

        }
        for (int i=0;i<step;i++){
            for (int j=0;j<n;j++){
                out.write(String.valueOf(mas[i][j]));
            }
            out.write("\n");
        }
        out.close();
    }
}
