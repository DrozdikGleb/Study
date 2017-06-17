
public class DTree {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        for (int i = 1; i < 10000; i++) {
            a += 8;
            if (a > 24 * 60) a = a % (24 * 60);
            b += 18;
            if (b > 24 * 60) b = b % (24 * 60);
            if (((a == 1440) && (b==720))||((a == 720) && ((b==1440))||(a == 720) && (b==720))||((a == 1440) && (b==1440))) {
                System.out.println(i);
                break;


            }
        }
    }
}
