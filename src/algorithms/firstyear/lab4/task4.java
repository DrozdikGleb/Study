package algorithms.firstyear.lab4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args)throws IOException {
        Scanner in = new Scanner(new File("levenshtein.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("levenshtein.out"));
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        int m = s1.length();
        int n = s2.length();
        int[][] d = new int[m+1][n+1];
        d[0][0]=0;
        for (int i = 1; i <m+1 ; i++) {
            d[i][0]=d[i-1][0]+1;
        }
        for (int i = 1; i <n+1 ; i++) {
            d[0][i]=d[0][i-1]+1;
        }
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j <n+1 ; j++) {
                if(s1.charAt(i-1)!=s2.charAt(j-1)){
                    d[i][j]=Math.min(Math.min(d[i-1][j]+1,d[i-1][j-1]+1),d[i][j-1]+1);
                }
                else{
                    d[i][j]=d[i-1][j-1];
                }
            }
        }
        out.write(String.valueOf(d[m][n]));
        out.close();
    }
}
