import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T3 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("antigray.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("antigray.out"));
        int n = in.nextInt();
        int step = (int) Math.pow(3, n - 1);
        int mas[] = new int[n];
        int mas2[] = new int[n];
        for (int i = 0; i < n; i++) {
            mas[i] = 0;
        }
        for (int i = 0; i < step; i++) {
            for (int j = 0; j < n; j++) {
                out.write(String.valueOf(mas[j]));
            }
            out.write("\n");
            for (int j = 0; j < n; j++)
            mas2[j] = mas[j];
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < n; j++) {
                    if (mas2[j] == 2) {
                        out.write("0");
                        mas2[j] = 0;
                    }
                    else {
                        out.write(String.valueOf(mas2[j]+1));
                        mas2[j]++;
                    }

                }
                out.write("\n");
            }
            mas[n-1]++;
            for (int j = n-1; j > 0; j--) {
                if (mas[j]==3){
                    mas[j]=0;
                    mas[j-1]++;
                }
            }


        }


        out.close();

    }
}
