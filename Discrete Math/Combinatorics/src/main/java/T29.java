import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T29 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextpartition.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("nextpartition.out"));
        String a = in.nextLine();
        a+="+";
        int mas2[]=new int [1000000];
        int add = 0;
        int cplus=0;
        String ans="";
        int k=0;
        for (int i = 0; i < a.length(); i++){
            ans+=a.charAt(i);
            k++;
            if (a.charAt(i)=='=') break;
        }

    String numb="";
        int l=0;
        for (int i = k; i < a.length(); i++) {
            if (a.charAt(i) != '+') {
                numb+=a.charAt(i);
            }
            else {
                mas2[l]=Integer.parseInt(numb);
                l++;
                numb="";
                add++;
            }
        }
        for (int i=0;i<add;i++){
            System.out.print(mas2[i]);
        }
        if (add == 1) {
            out.write("No solution");
        }else {

           int length=add;

                  mas2[add-1]--;
                mas2[add-2]++;
                if (mas2[add-2]>mas2[add-1]){
                    mas2[add-2]+=mas2[add-1];
                    length--;
                    out.write(ans);
                    for (int i=0;i<length;i++){
                        out.write(String.valueOf(mas2[i]));
                        if (i!=length-1){
                            out.write("+");
                        }
                    }
                }
                else{
                    while (mas2[add-2] * 2 <= mas2[add-1]) {
                       mas2[add]=mas2[add-1]-mas2[add-2];
                        mas2[add-1] = mas2[add-2];
                        add++;
                    }
                    out.write(ans);

                    for (int i = 0; i < add; i++) {
                        out.write(String.valueOf(mas2[i]));
                        if (i!=add-1){
                            out.write("+");
                        }
                    }

                }

        }
        out.close();

    }

}
