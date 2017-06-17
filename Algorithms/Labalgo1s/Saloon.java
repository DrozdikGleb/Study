package com.training.database;

import java.util.*;
import java.io.*;

public class Saloon {
    FastScanner in;
    PrintWriter out;




    public void solve() throws IOException {
        ArrayDeque<Integer> deck = new ArrayDeque<Integer>();

        int N = in.nextInt();
        int m = in.nextInt();
        int temp=0;
int count =0;
        for(int i=0; i<N;i++){
            temp=in.nextInt();
            deck.addLast(temp);
        }
        while(deck.size()!=0 && deck.size()<=m) {
            int k=deck.size();
            int pos = m / k;
            for (int i = 0; i < k; i++) {
                if (deck.getFirst() <= pos) {
                    count += deck.getFirst();
                    deck.removeFirst();
                } else {
                    count += pos;
                    int t = deck.getFirst();
                    deck.removeFirst();
                    deck.addLast(t-pos);
                }
            }
            m=m-count;
            count=0;
        }
 if (deck.size()!=0) {
     for (int i = 0; i < m; i++) {

         int k = deck.getFirst() - 1;
         if (k == 0) {

             deck.removeFirst();
         } else {

             deck.removeFirst();
             deck.addLast(k);

         }

     }
 }

        int f=deck.size();
        if (f==0)out.write(-1+"\n");
        else {
            out.write(f + "\n");
            for (int i = 0; i < f; i++) {
                out.write(deck.getFirst() + " ");
                deck.removeFirst();
            }
        }


    }





    public void run() {
        try {
            in = new FastScanner(new File("bureaucracy.in"));
            out = new PrintWriter(new File("out.txt"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        new Saloon().run();
    }
}
