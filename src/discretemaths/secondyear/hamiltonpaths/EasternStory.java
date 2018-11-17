package discretemaths.secondyear.hamiltonpaths; /**
 * Created by GlebDrozdov on 12.10.2017.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class EasternStory {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        ArrayList<Integer> mas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mas.add(i + 1);
        }
        mas.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("1 " + o1.toString() + " " + o2.toString());
                System.out.flush();
                String answer = in.nextLine();
                if (answer.equals("YES")) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        System.out.print("0" + " ");
        for (int i = 0; i < n; i++) {
            System.out.print(String.valueOf(mas.get(i)) + " ");
        }

    }
}
