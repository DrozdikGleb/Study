import java.io.*;
import java.util.Scanner;

class leaf {
    long min;
    long add;
    boolean p;

    leaf() {
        min = Long.MAX_VALUE;
        add=0;

    }
}

public class RMQ2 {
    public static void buildTree(leaf tree[], long mas[], int k, int tl, int tr) {

        if (tr-tl==1) {
            tree[k].add = 0;
            tree[k].min = mas[tl];
            tree[k].p=false;
        } else {
            int m = (tl + tr) / 2;
            buildTree(tree, mas, k * 2+1, tl, m);
            buildTree(tree, mas, k * 2 + 2, m , tr);
            tree[k].min = Math.min(tree[k * 2+1].min, tree[k * 2 + 2].min);
            tree[k].add = 0;
            tree[k].p=false;
        }

    }



    public static void push(leaf tree[], int i) {
        if (tree[i].p)
        {
            tree[2 * i +1] = tree[2 * i + 2] = tree[i];
            tree[i].p = false;
            tree[i].min += tree[i].add;
            tree[i].add = 0;
        }
        else if (tree[i].add != 0)
        {
            tree[2 * i + 2].add += tree[i].add;
            tree[2 * i +1].add += tree[i].add;
            tree[i].min += tree[i].add;
            tree[i].add = 0;
        }
    }
    public static void add(leaf tree[], int i, int tl, int tr, int l, int r, long value)
    {
        if (l >= r)
            return;
        if (tl == l && tr == r)
        {
            tree[i].add += value;
        }
        else
        {
            int m = (tl + tr) / 2;
            push(tree,i);
            add(tree,2 * i+1, tl, m, l, Math.min(r, m), value);
            add(tree,2 * i + 2, m, tr, Math.max(m, l), r, value);
            tree[i].min = Math.min(tree[2 * i+1].min + tree[2 * i+1].add,
                    tree[2 * i+2].min + tree[2 * i+2].add);
        }
    }

    public static void set(leaf tree[],int i, int tl, int tr, int l, int r, long value)
    {
        if (l >= r)
            return;
        if (l == tl && r == tr)
        {
            tree[i].p = true;
            tree[i].add = 0;
            tree[i].min = value;
        }
        else
        {
            int m = (tl + tr) / 2;
            push(tree,i);
            set(tree,2 * i+1, tl, m, l, Math.min(r, m), value);
            set(tree,2 * i + 2, m, tr, Math.max(m, l), r, value);
            tree[i].min = Math.min(tree[2 * i+1].min + tree[2 * i+1].add,
                    tree[2 * i+2].min + tree[2 * i+2].add);
        }
    }
    public static long minim(leaf tree[],int i, int tl, int tr, int l, int r)
    {
        if (l >= r)
            return Long.MAX_VALUE;
        if (tree[i].p)
            return tree[i].min + tree[i].add;
        if (l == tl && tr == r)
        {
            return tree[i].min + tree[i].add;
        }
        int m =	(tl + tr) / 2;
        return tree[i].add + Math.min(minim(tree,2 * i+1 , tl, m, l, Math.min(r, m)),
                minim(tree,2 *i + 2, m, tr, Math.max(m, l), r));
    }




    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("rmq2.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("rmq2.out"));
        int   N = in.nextInt();
        leaf[] tree = new leaf[4 * N];
        long [] mas = new long [N];
        for (int i = 0; i < N; i++) {
            mas[i] = in.nextLong();
        }
        for (int i = 0; i < 4 * N; i++) {
            leaf a = new leaf();
            tree[i] = a;
        }


        buildTree(tree, mas, 0, 0, N );



        while (in.hasNextLine()) {
            String[] s = in.nextLine().split(" ");
            if (s[0].equals("min")) {
                int a = Integer.parseInt(s[1]);
                int b = Integer.parseInt(s[2]);
                 out.write(Long.toString(minim(tree, 0, 0, N, a - 1, b)) + "\n");


            }
            if (s[0].equals("set")) {
                int c = Integer.parseInt(s[1]);
                int d = Integer.parseInt(s[2]);
                long l =Long.parseLong(s[3]);
                set(tree, 0, 0, N, c - 1, d,l);
            }
            if (s[0].equals("add")){
                int f= Integer.parseInt(s[1]);
                int g = Integer.parseInt(s[2]);
                long o=Long.parseLong(s[3]);
                add(tree,0, 0, N, f - 1, g, o);
            }


        }
        out.close();


    }
}