import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class T4 {


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("chaincode.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("chaincode.out"));
        int n = in.nextInt();
        int step = (int) Math.pow(2, n);
        int numb[] = new int[step +1];
        for (int i = 0; i < step + 1; i++) {
            numb[i] = 0;
        }
        int c=0;

        int mas[] = new int[n+1];
        int mas2[]=new int [n+1];
        for (int i = 0; i < n; i++) {
            mas[i] = 0;
        }
        for (int i = 0; i < step; i++) {

            for (int j = 0; j < n; j++) {
                out.write(String.valueOf(mas[j]));
            }
            out.write("\n");
            for (int j=n-1;j>0;j--){
                mas2[j-1]=mas[j];
            }
            mas2[n-1]=1;
            c=0;
            for (int k=n-1;k>=0;k--){
                if (mas2[k]==1){
                    c+=((int)Math.pow(2,k));
                }
            }
            if( numb[c]==0){
                numb[c]=1;
            }
            else{
                mas2[n-1]=0;
            }
            for(int f=0;f<n;f++){
                mas[f]=mas2[f];
            }

            System.out.println(c);

        }


        out.close();

    }
}


