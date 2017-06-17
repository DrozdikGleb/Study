
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Krom {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        boolean check = true;
        ArrayList<ArrayList<Integer>> mas = new ArrayList<ArrayList<Integer>>();


        for (int i = 0; i < k; i++) {
            mas.add(new ArrayList<Integer>());
            for (int j = 0; j < n; j++) {
                int a = in.nextInt();
                if (a == 1) {
                    mas.get(i).add(j + 1);
                }
                if (a == 0) {
                    mas.get(i).add(-(j + 1));
                }
            }

        }


        exit:
        while (true) {
            int p = 0;
            for (int i = 0; i < mas.size(); i++) {
                if (mas.get(i).size() == 1) {
                    p = 1;
                    int one = mas.get(i).get(0);

                    for (int j = 0; j < mas.size(); j++) {
                        if (mas.get(j).contains(-one)) {
                            mas.get(j).remove(mas.get(j).indexOf(-one));
                        }

                        if (mas.get(j).size() == 0) {
                            check = false;
                            break exit;
                        }
                        if (mas.get(j).contains(one)) {
                            mas.remove(j);
                            j--;
                        }


                    }
                }

            }
            if (p == 0) {
                check = true;
                break exit;
            }

        }


        if (check) {
            System.out.println("NO");
        } else
            System.out.println("YES");


    }
}
