package algorithms.firstyear.lab2;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class Sorts {
    FastScanner in;
    PrintWriter out;

    //QUICKSORT
    public void Quicksort(int[] array, int left, int right) {
        Random rand = new Random();
        int i = left;
        int j = right;
        int x = array[left + rand.nextInt(right - left + 1)];
        while (i <= j) {
            while (array[i] < x) {
                i++;
            }
            while (array[j] > x) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (left < j) {
            Quicksort(array, left, j);
        }
        if (i < right) {
            Quicksort(array, i, right);
        }
    }

    //****MERGESORT*****
    public void mergeSort(int[] a, int low, int high, int[] b) {

        if (low < high) {

            int middle = low + (high - low) / 2;

            mergeSort(a, low, middle, b);

            mergeSort(a, middle + 1, high, b);

            merge(a, low, middle, high, b);
        }
    }

    private void merge(int[] a, int low, int middle, int high, int[] b) {

        for (int i = low; i <= high; i++) {
            b[i] = a[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            if (b[i] <= a[j]) {
                a[k] = b[i];
                i++;
            } else {
                a[k] = b[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            a[k] = b[i];
            k++;
            i++;
        }

    }

    //****HEAPSORT*****
    public static void heapify(int[] array, int size, int pos) {
        while (2 * pos + 1 < size) {
            int t = 2 * pos + 1;
            if ((2 * pos + 2 < size) && (array[2 * pos + 1] < array[2 * pos + 2])) {
                t = 2 * pos + 2;
            }
            if (array[pos] < array[t]) {
                swap(array, pos, t);
                pos = t;
            } else {
                break;
            }
        }
    }


    public static int[] heapMake(int[] array) {
        int n = array.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        return array;
    }


    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void HeapSort(int[] array) {
        int n = array.length;
        heapMake(array);
        while (n > 0) {
            swap(array, 0, n - 1);
            n--;
            heapify(array, n, 0);
        }

    }

    //MAIN
    public void solve() throws IOException {

        int N = in.nextInt();
        int[] a = new int[N];
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();

        }
        switch (N % 3) {
            case 0:
                mergeSort(a, 0, N - 1, b);
                break;
            case 1:
                HeapSort(a);
                break;
            case 2:
                Quicksort(a, 0, N - 1);
                break;
        }
        for (int i = 0; i < N; i++) {
            out.print(a[i] + " ");

        }

    }


    public void run() {
        try {
            in = new FastScanner(new File("sort.in "));
            out = new PrintWriter(new File("sort.out"));

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
        new Sorts().run();
    }

}
