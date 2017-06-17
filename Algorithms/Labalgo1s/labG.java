

public class labG {
    public static void main(String[] args) {
        String []a= new String [4];
       a[0]="a";
        a[1]="c";
        a[2]="g";
        a[3]="l";

        int [] b = new int [4];
        for(int i=0;i< 4;i++){
            b[i]=(int)a[i].charAt(0)-97;
        }
        for(int i=0;i<4;i++){
            System.out.println(b[i]);
        }

    }
}
