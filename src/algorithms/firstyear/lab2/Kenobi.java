package algorithms.firstyear.lab2;


import java.io.*;
import java.util.StringTokenizer;

public class Kenobi {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        class Element {
            public int value;
            public int next;
            public int prev;

            Element() {
                prev = -1;
                next = -1;
                value = -1;

            }


        }
        class List {

            public Element mas[];
            public int j;
            public int tail;
            public int size;
            public int head;
            public int mid;

            List(int size) {
                mas = new Element[size];
                for (int i = 0; i < size; i++) {
                    mas[i] = new Element();
                }
                j = -1;
                tail = 0;
                size = 0;
                mid = 0;
                head = 0;

            }

            public void add(int i) {
                j++;
                mas[j].value = i;

                size++;
                if (size > 1) {
                    mas[j].prev = tail;
                    mas[tail].next = j;
                }
                tail = j;
                if (size % 2 == 0 && size != 2) {
                    mid = mas[mid].next;
                }


            }

            public void remove() {
                int k = tail;
                int s = size;
                if (size > 1) {
                    mas[tail].value = -1;
                    mas[mas[tail].prev].next = -1;
                    tail = mas[tail].prev;
                    mas[k].prev = -1;
                    size--;
                    if (size % 2 != 0 && size != 1) {
                        mid = mas[mid].prev;
                    }
                }

                if (s == 1) {
                    mas[tail].value = -1;
                    mas[k].prev = -1;
                    mid = j + 1;
                    head = j + 1;
                    tail = j + 1;
                    size--;
                }


            }

            public void mum() {
                if (size >= 2) {
                    int temp = tail;
                    mas[tail].next = head;
                    mas[head].prev = tail;
                    tail = mid;
                    head = mas[mid].next;
                    if (size % 2 == 0) {
                        mid = temp;

                    } else
                        mid = mas[temp].prev;
                }


            }
        }


        int N = in.nextInt();
        List list = new List(N);
        int k = 0;
        int t = 0;
        for (int i = 0; i < N; i++) {
            String temp = in.next();
            if (temp.equals("add")) {
                t = in.nextInt();
                list.add(t);
                k++;
            }
            if (temp.equals("take")) {
                list.remove();
                k--;
            }
            if (temp.equals("mum!")) {
                list.mum();
            }

        }

        int q = list.head;
        out.write(list.size + "\n");
        for (int i = 0; i < list.size; i++) {
            out.write(list.mas[q].value + " ");
            q = list.mas[q].next;
        }
    }


    public void run() {
        try {
            in = new FastScanner(new File("kenobi.in"));
            out = new PrintWriter(new File("kenobi.out"));

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
        new Kenobi().run();
    }
}
