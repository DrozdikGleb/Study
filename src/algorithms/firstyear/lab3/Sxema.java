package algorithms.firstyear.lab3;

import java.io.*;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Sxema {

    static InputReader in2;
    static OutputWriter out2;

    public static void main(String[] args) throws IOException {
        in2 = new InputReader(System.in);
        out2 = new OutputWriter(System.out);
        int i, j, n, k = 0, now, x, number, wow, max;
        n = in2.readInt();
        int[] heigh = new int[n];
        int[] m = new int[n];
        int[] st = {1, 2, 4, 8, 16, 32};
        int[] num = new int[n];
        int[][] in = new int[n][5];
        int[][] out = new int[n][32];
        for (i = 0; i < n; i++) {
            m[i] = in2.readInt();
            if (m[i] != 0) {
                max = 0;
                for (j = 0; j < m[i]; j++) {
                    in[i][j] = in2.readInt();
                    if (max < heigh[in[i][j] - 1])
                        max = heigh[in[i][j] - 1];
                }
                heigh[i] = max + 1;
                for (j = 0; j < st[m[i]]; j++) {
                    out[i][j] = in2.readInt();
                }
            } else k++;
        }
        int[] list = new int[k];
        for (int p = 0; p < n; p++) {
            System.out.println(heigh[p]);
        }
        out2.printLineln(heigh[i - 1]);
        wow = (int) Math.pow(2, k);
        for (x = 0; x < wow; x++) {
            now = 0;
            for (i = 0; i < n; i++) {
                if (m[i] == 0) {
                    num[i] = list[now];
                    now++;
                } else {
                    number = 0;
                    for (j = m[i]; j > 0; j--) {
                        number += num[in[i][j - 1] - 1] * st[m[i] - j];

                    }
                    num[i] = out[i][number];
                }
            }
            out2.printLine(num[i - 1]);
            list[k - 1]++;
            for (int r = k - 1; r > 0; r--) {
                if (list[r] == 2) {
                    list[r - 1]++;
                    list[r] = 0;
                }
            }
        }
        out2.close();
    }
}


class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    FastScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
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


class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
        return readString();
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object... objects) {
        print(objects);
    }

    public void printLineln(Object... objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

    public void flush() {
        writer.flush();
    }

}

