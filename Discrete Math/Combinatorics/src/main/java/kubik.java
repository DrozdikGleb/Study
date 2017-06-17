/**
 * Created by Виталий on 02.01.2017.
 */

public class kubik {
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            double a = Math.round(Math.random() * 10 % 5 + 1);
            System.out.print(Integer.parseInt(String.valueOf(a).substring(0, 1)) + " ");
        }
    }
}
