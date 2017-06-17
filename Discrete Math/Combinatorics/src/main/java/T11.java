import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T11 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("out.txt"));
        out.write("\n");
        int n = in.nextInt();
        int mas[] = new int[n + 1];
        for (int i = 0; i < n; i++) {
            mas[i] = i + 1;
        }

        out.close();
    }
}
