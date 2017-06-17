import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BST {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("in.txt"));
        while (in.hasNext()){
            String [] line= in.nextLine().split(" ");
          //  int hash=java.util.Arrays.deepHashCode( board );
            if (line[0].equals("insert")){
                int a=Integer.parseInt(line[1]);

            }
            if (line[0].equals("exists")){
                int a=Integer.parseInt(line[1]);

            }
            if (line[0].equals("next")){
                int a=Integer.parseInt(line[1]);

            }
            if (line[0].equals("prev")){
                int a=Integer.parseInt(line[1]);

            }
            if (line[0].equals("delete")){
                int a=Integer.parseInt(line[1]);
                
            }

        }

    }

}
