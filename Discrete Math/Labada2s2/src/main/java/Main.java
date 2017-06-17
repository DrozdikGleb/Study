import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Drozdov Gleb on 18.05.2017.
 */
public class Main {
    private static Random rand = new Random();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /*int []mas = new int [10];
        for (int i = 0; i < 10; i++) {
            mas[i]=in.nextInt();
        }
        quickSort(mas,0,9);
        for (int i = 0; i <10 ; i++) {
            System.out.println(mas[i]+" ");
        }*/


        System.out.println((int)'a');
    }
    public static void quickSort(int[] array, int l, int r) {
        int i = l;
        int j = r;
        int x = array[l + rand.nextInt(r - l + 1)];
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
        if (l<j){
            quickSort(array, l, j);
        }
        if(i<r){
            quickSort(array, i, r);
        }
    }
}
