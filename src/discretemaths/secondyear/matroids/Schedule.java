package discretemaths.secondyear.matroids;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Schedule {
    FastScanner in;
    PrintWriter out;

    public static void main(String[] arg) {
        new Schedule().run();
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        ArrayList<Task> tasks = new ArrayList<>();
        PriorityQueue<Task> queue = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });
        long answer = 0;
        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            int w = in.nextInt();
            tasks.add(new Task(d, w));
            //answer+=w;
        }
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return Integer.compare(o1.deadline, o2.deadline);
            }
        });
        int time = 1;
        for (int i = 0; i < n; i++) {
            queue.add(tasks.get(i));
            if (tasks.get(i).deadline >= time) {
                time++;
            } else {
                Task task = queue.poll();
                answer += task.weight;
            }
        }
        out.print(String.valueOf(answer));
    }

    public void run() {
        try {
            in = new FastScanner(new File("schedule.in"));
            out = new PrintWriter(new File("schedule.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Task {
        int weight;
        int deadline;

        Task(int deadline, int weight) {
            this.weight = weight;
            this.deadline = deadline;
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
}
