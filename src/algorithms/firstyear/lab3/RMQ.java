package algorithms.firstyear.lab3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RMQ {
    public static void buildTree(long tree[], long mas[], int k, int tl, int tr) {

        if (tl == tr)
            tree[k] = mas[tl];
        else {
            int m = (tl + tr) / 2;
            buildTree(tree, mas, k * 2, tl, m);
            buildTree(tree, mas, k * 2 + 1, m + 1, tr);
            tree[k] = Math.min(tree[k * 2], tree[k * 2 + 1]);

        }
        System.out.print(tree[k] + " ");

    }

    public static void set(long tree[], int k, int tl, int tr, int pos, int new_val) {
        if (tl == tr)
            tree[k] = new_val;
        else {
            int m = (tl + tr) / 2;
            if (pos <= m)
                set(tree, k * 2, tl, m, pos, new_val);
            else
                set(tree, k * 2 + 1, m + 1, tr, pos, new_val);
            tree[k] = Math.min(tree[k * 2], tree[k * 2 + 1]);
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        int N = in.nextInt();
        long[] tree = new long[4 * N];
        long[] mas = new long[N];
        for (int i = 0; i < N; i++) {
            mas[i] = in.nextInt();
        }


        buildTree(tree, mas, 1, 0, N - 1);


        while (in.hasNextLine()) {
            String[] s = in.nextLine().split(" ");
            if (s[0].equals("min")) {
                int a = Integer.parseInt(s[1]);
                int b = Integer.parseInt(s[2]);
                // out.write(min(tree, 1, 0, N - 1, a - 1, b - 1) + "\n");


            }
            if (s[0].equals("set")) {
                int c = Integer.parseInt(s[1]);
                int d = Integer.parseInt(s[2]);
                set(tree, 1, 0, N - 1, c - 1, d);
            }
            if (s[0].equals("add")) {
                int f = Integer.parseInt(s[1]);
                int g = Integer.parseInt(s[2]);
            }


        }
        //out.close();


    }
}