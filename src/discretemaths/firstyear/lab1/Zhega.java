package discretemaths.firstyear.lab1;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Zhega {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        double k = in.nextInt();
        int m = (int) Math.pow(2, k);
        ArrayDeque<Integer> pol = new ArrayDeque<Integer>();
        String[] mas = new String[m];
        int[] ans = new int[m];
        // int [] ch = new int [m];
        for (int i = 0; i < m; i++) {
            mas[i] = in.next();
            pol.addLast(in.nextInt());
        }

        ans[0] = pol.getFirst();

        for (int i = 1; i < m; i++) {
            int len = pol.size();
            for (int j = 0; j < len - 1; j++) {
                int x = pol.getLast();
                pol.removeLast();
                int y = pol.getLast();
                if ((x + y) % 2 == 0) {
                    pol.addFirst(0);
                } else pol.addFirst(1);
            }
            pol.removeLast();
            ans[i] = pol.getFirst();
        }

        for (int i = 0; i < m; i++) {
            System.out.println(mas[i] + " " + ans[i]);
        }

    }
}
