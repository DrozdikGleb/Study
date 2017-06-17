import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T7 {
   public static int fact(int n){
       int k=1;
       while (n!=0){
           k*=n;
           n--;
       }
       return k;
   }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("permutations.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("permutations.out"));
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = i+1;
        }
        int min=0;
        int f=fact(n);
        for (int i=0;i<f;i++){
            for (int l = 0; l < n; l++) {
                out.write(a[l]+" ");
            }
            int c=0;
            int k=0;
            for (int j=n-1;j>0;j--){
                if (a[j]>a[j-1]){
                    c=j-1;
                    break;
                }
            }
            min=Integer.MAX_VALUE;
            for (int j=n-1;j>=c;j--){

                if ((a[j]>a[c])&&(a[j]<min)){
                    k=j;
                    min=a[j];
                }
            }
            int temp1=a[k];
            a[k]=a[c];
            a[c]=temp1;
            int s=n-1;
            for (int j=0;j<(n-1-c)/2;j++) {
                int temp = a[c + j + 1];
                a[c + j + 1] = a[s];
                a[s] = temp;
                s--;
            }

            out.write("\n");

        }
        out.close();


    }
}
