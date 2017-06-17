import java.util.Scanner;



public class Full {
    public static boolean zero(int mas[],int size){
        if (mas[0]==0) return true; else
            return false;
    }
    public static boolean one(int mas[],int size){
        if (mas[size-1]==1) return true;
        else return false;
    }
    public static boolean mono(int mas[],int size){
        int p=size;
        int i;
        int k=1;
        int first;
        int last;
        while (p>1){
            p=p/2;
            first=0;
            last=p;
            for (i=0; i<k;i++) {
                for (int j = first; j < last; j++)
                    if (mas[j] > mas[j + p]) {
                        return false;
                    }
                    first+=2*p;
                last+=p*2;

            }
            k *= 2;
            }

        return true;


    }
    public static boolean dvoist(int mas[],int size) {
        for (int i = 0; i < size; i++) {
            if (mas[i] == mas[size - 1 - i]) return false;
        }
        return true;
    }
    public static boolean line(int mas[],int size){
        int []ans=new int [size];
        if (size>=4){
            int p=size-1;
            ans[0]=mas[0];
            for (int i=1; i<size; i++){
                for (int j=0; j<p; j++)
                    if (mas[j]==mas[j+1]) mas[j]=0; else mas[j]=1;
                ans[i]=mas[0];
                p--;
            }
            for (int i=3; i<size; i++)
                if (i!=4 && i!=8 && i!=16 && i!=32 )
                    if (ans[i]==1)  {
                        return false;
                    }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N=in.nextInt();
        int []check=new int [5];
        for (int i=0;i<5;i++){
            check[i]=0;
        }
        for (int i=0;i<N;i++){
            double k=in.nextInt();
            int t = (int) Math.pow(2,k);
            String m = in.next();
            int []mas = new int[32];
            for (int j=0;j<t;j++ ){
                mas[j]=(int)m.charAt(j)-'0';
            }
            if ((zero(mas,t)==false)&&(check[0]==0)){
                check[0]+=1;
            }
            if ((one(mas,t)==false)&&(check[1]==0)){
                check[1]+=1;
            }
            if ((mono(mas,t)==false)&&(check[2]==0)){
                check[2]+=1;
            }
            if ((dvoist(mas,t)==false)&&(check[3]==0)){
                check[3]++;
            }
            if ((line(mas,t)==false)&&(check[4]==0)){
                check[4]++;
            }
            int ch=0;
            for (int p=0;p<5;p++){
                if (check[p]>0){
                    ch++;
                }
            }
            if (ch==5)break;


        }
        int ch2=0;
        for (int p=0;p<5;p++){
            if (check[p]>0){
                ch2++;
            }
        }
        if (ch2==5){
            System.out.println("YES");
        }
        else System.out.println("NO");


    }
}
