
import java.io.*;
import java.util.*;


public class Heap{

     /*   public static void main(String[] args) throws IOException {
            Scanner in = new Scanner(new File("formation.in"));
            FileWriter out = new FileWriter("formation.out");
            int M = in.nextInt();
            int N = in.nextInt();
            LinkedList<Integer> list = new LinkedList();
            list.add(1);
            int element;
            int sosed;
            int a, b;
            for (int i = 0; i < N; i++) {
                String temp = in.next();
                if (temp.equals("left")) {
                    element = in.nextInt();
                    sosed = in.nextInt();
                    list.add(sosed - 1, element);
                } else if (temp.equals("right")) {
                    element = in.nextInt();
                    sosed = in.nextInt();
                    list.add(sosed + 1, element);
                } else if (temp.equals("leave")) {
                    element = in.nextInt();
                    list.remove(list.indexOf(element));
                } else {
                    element = in.nextInt();
                    if (list.indexOf(element) == 0) a = 0;
                    else a = list.get(list.indexOf(element) - 1);
                    if (list.indexOf(element) == list.size() - 1) b = 0;
                    else b = list.get(list.indexOf(element) + 1);
                    out.write(a+" "+ b+"\n");


                }


            }


            out.close();
        }*/


    }
