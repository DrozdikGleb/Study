import java.util.HashSet;
import java.util.Set;

/**
 * Created by Виталий on 07.12.2016.
 */
public class Test {
    public static void main(String[] args) {
        Long k = new Double(-Math.pow(2, 32)).longValue();
        Long temp = 1L + k;
        Set<Long> testHash1 = new HashSet<Long>();
        Set<Long> testHash2 = new HashSet<Long>();
        Long a;
        Long mas[]=new Long [10000000];

        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000 ; i++) {
           // testHash1.add(i + k);
           // testHash1.add((long) i);
            //mas[i]=(long)(i);
        }
        System.out.println(System.currentTimeMillis() - time1);

        long time2 = System.currentTimeMillis();
        /*testHash2.add(2L);
        testHash2.add(1L);*/

        for (int i = 0; i < 10000000 ; i++) {
            testHash1.add((i+k)^((i+k)>>32));
            //testHash1.add((long) i);
        }
        System.out.println(System.currentTimeMillis() - time2);
        //System.out.println(new Long().hashCode());
    }
}
