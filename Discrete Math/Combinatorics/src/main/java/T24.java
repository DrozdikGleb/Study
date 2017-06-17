import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T24 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextperm.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("nextperm.out"));
        int n = in.nextInt();
        int[] a = new int[n + 1];
        int[] a2 = new int[n + 1];
        int b=0;
        int m=0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            a2[i]=a[i];
        }
        for (int i = 1; i < n; i++) {
            if (a[i]>a[i-1]){
                b++;
            }
            else m++;
        }

        if (b==n-1){
            for (int i=0;i<n;i++){
                out.write(0+" ");
            }
            out.write("\n");
        }
        else {
            int c = 0;
            int k = 0;
            for (int j = n - 1; j > 0; j--) {
                if (a2[j] < a2[j - 1]) {
                    c = j - 1;
                    break;
                }
            }
            int max = Integer.MIN_VALUE;
            for (int j = n - 1; j >= c; j--) {

                if ((a2[j] < a2[c]) && (a2[j] >max )) {
                    k = j;
                    max = a2[j];
                }
            }
            int temp1 = a[k];
            a2[k] = a2[c];
            a2[c] = temp1;
            int s = n - 1;
            for (int j = 0; j < (n - 1 - c) / 2; j++) {
                int temp = a2[c + j + 1];
                a2[c + j + 1] = a2[s];
                a2[s] = temp;
                s--;
            }
            for (int l = 0; l < n; l++) {
                out.write(a2[l] + " ");
            }

            out.write("\n");

        }
        if (m==n-1){
            for (int i=0;i<n;i++){
                out.write(0+" ");
            }
            out.write("\n");
        }
        else{
            int c = 0;
            int k = 0;
            for (int j = n - 1; j > 0; j--) {
                if (a[j] > a[j - 1]) {
                    c = j - 1;
                    break;
                }
            }
            int min = Integer.MAX_VALUE;
            for (int j = n - 1; j >= c; j--) {

                if ((a[j] > a[c]) && (a[j] < min)) {
                    k = j;
                    min = a[j];
                }
            }
            int temp1 = a[k];
            a[k] = a[c];
            a[c] = temp1;
            int s = n - 1;
            for (int j = 0; j < (n - 1 - c) / 2; j++) {
                int temp = a[c + j + 1];
                a[c + j + 1] = a[s];
                a[s] = temp;
                s--;
            }
            for (int l = 0; l < n; l++) {
                out.write(a[l] + " ");
            }

            out.write("\n");

        }




        out.close();


    }
}

