import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T23 {
    public static void main(String[] args) throws IOException {


        Scanner in = new Scanner(new File("in.txt"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("out.txt"));
        String str = in.nextLine();
        int n = str.length();
        char ch[] = new char[n];
        int list[] = new int[n];
        int k = 0;
        int list2[]=new int [n];


        for (int i = 0; i < n; i++) {
            ch[i] = str.charAt(i);
            if (ch[i] == '1') {
                k++;
            }
            list[i] = (int) ch[i] - '0';
            list2[i] = (int) ch[i] - '0';
        }
        int t=0;
        if (k==0){
           out.write("-"+"\n");
        }

        else{
            list[n - 1]--;
            for (int i = n - 1; i > 0; i--)
                if (list[i] == -1) {
                    list[i - 1]--;
                    list[i] = 1;
                }
            for (int i = 0; i < n; i++) {
                out.write(String.valueOf(list[i]));
            }
            out.write("\n");

        }


        if (k == n) {
            out.write("-");
        } else {


            list2[n - 1]++;
            for (int i = n - 1; i > 0; i--) {
                if (list2[i] == 2) {
                    list2[i - 1]++;
                    list2[i] = 0;
                }
            }
            for (int i = 0; i < n; i++) {
                out.write(String.valueOf(list2[i]));
            }
        }



        out.close();
    }

}
