package com.training.database;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class Stroi {

    FastScanner in;
    PrintWriter out;


    public void solve() throws IOException {
        class Element {
            public int prev;
            public int next;

            Element() {
                prev = 0;
                next = 0;
            }
        }
        class List {
            public Element mas[];

            List(int Size) {
                mas = new Element[Size];
                for (int i = 0; i < Size; i++) {
                    mas[i] = new Element();
                }
            }

            public void left(int i, int j) {
                mas[i - 1].prev = mas[j - 1].prev;
                mas[i - 1].next = j;
                if (mas[j - 1].prev != 0)
                    mas[mas[j - 1].prev - 1].next = i;
                mas[j - 1].prev = i;
            }

            public void right(int i, int j) {
                mas[i - 1].prev = j;
                mas[i - 1].next = mas[j - 1].next;
                if (mas[j - 1].next != 0)
                    mas[mas[j - 1].next - 1].prev = i;
                mas[j - 1].next = i;
            }

            public void leave(int i) {
                if (mas[i - 1].prev != 0)
                    mas[mas[i - 1].prev - 1].next = mas[i - 1].next;
                if (mas[i - 1].next != 0)
                    mas[mas[i - 1].next - 1].prev = mas[i - 1].prev;
                mas[i - 1].next = 0;
                mas[i - 1].prev = 0;
            }

        }


        int M = in.nextInt();
        int N = in.nextInt();

        List list = new List(N);
        int element;
        int sosed;
        int a, b;
        for (int i = 0; i < N; i++) {
            String temp = in.next();
            if (temp.equals("left")) {
                element = in.nextInt();
                sosed = in.nextInt();
                list.left(element, sosed);
            }
            if (temp.equals("right")) {
                element = in.nextInt();
                sosed = in.nextInt();
                list.right(element, sosed);
            }
            if (temp.equals("leave")) {
                element = in.nextInt();
                list.leave(element);
            }
            if (temp.equals("name")) {
                element = in.nextInt();
                out.write(list.mas[element - 1].prev + " " + list.mas[element - 1].next + "\n");


            }


        }


        out.close();

    }


    public void run() {
        try {
            in = new FastScanner(new File("formation.in"));
            out = new PrintWriter(new File("formation.out"));

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
        new Stroi().run();
    }


}