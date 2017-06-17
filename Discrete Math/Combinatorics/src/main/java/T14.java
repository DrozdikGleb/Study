import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class T14 {
    public static long fact(long n) {
        long k = 1;
        if (n==0){
            return 1;
        }
        while (n != 0) {
            k *= n;
            n--;
        }
        return k;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("perm2num.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("perm2num.out"));
        BigInteger[] per = new BigInteger[19];
        per[0]=new BigInteger("0");
        per[1]=new BigInteger("1");
        per[2]=new BigInteger("2");
        per[3]= new BigInteger("6");
        per[4]=new BigInteger("24");
        per[5]=new BigInteger("120");
        per[6]=new BigInteger("720");
        per[7]=new BigInteger("5040");
        per[8]=new BigInteger("40320");
        per[9]=new BigInteger("362880");
        per[10]= new BigInteger("3628800");
        per[11]=new BigInteger("39916800");
        per[12]=new BigInteger("479001600");
        per[13]=new BigInteger("6227020800");
        per[14]=new BigInteger("87178291200");
        per[15]=new BigInteger("1307674368000");
        per[16]=new BigInteger("20922789888000");
        per[17]=new BigInteger ("355687428096000");
        per[18]=new BigInteger("6402373705728000");
        int n = in.nextInt();
        long mas[] = new long[n + 1];
        long numb[] = new long[n + 1];
        numb[0]=0;
        for (int i = 1; i < n + 1; i++) {
            mas[i] = in.nextLong();
            numb[i] = 0;
        }
        BigInteger num=new BigInteger("0");

        for (int i = 1; i < n; i++) {
                  for (int j=1;j<mas[i];j++){
                      if (numb[j] == 0)
                      {
                          num=num.add(per[n-i]);
                      }
                  }
            numb[(int)mas[i]] = 1;

        }
        out.write(String.valueOf(num));
        out.close();

    }
}
