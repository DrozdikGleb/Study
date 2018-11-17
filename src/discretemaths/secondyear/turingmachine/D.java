package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 05.06.2018
 */
public class D {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("tandem.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s 1 -> r 3 >\n" +
                "s 0 -> r 2 >\n" +
                "r 0 -> r 0 >\n" +
                "r 1 -> r 1 >\n" +
                "r _ -> print_prev _ <\n" +
                "print_prev 0 -> back 2 <\n" +
                "print_prev 1 -> back 3 <\n" +
                "print_prev 2 -> rj _ ^\n" +
                "print_prev 3 -> rj _ ^\n" +
                "r 2 -> print_prev 2 <\n" +
                "r 3 -> print_prev 3 <\n" +
                "back 1 -> back 1 <\n" +
                "back 0 -> back 0 <\n" +
                "back 2 -> print_next 2 >\n" +
                "back 3 -> print_next 3 >\n" +
                "print_next 1 -> r 3 >\n" +
                "print_next 0 -> r 2 >\n" +
                "print_next 2 -> mid0 4 <\n" +
                "print_next 3 -> mid1 4 <\n" +
                "mid0 2 -> mid0 2 <\n" +
                "mid0 3 -> mid0 3 <\n" +
                "mid0 4 -> mid0 4 <\n" +
                "mid0 _ -> check_next0 _ >\n" +
                "check_next0 2 -> right _ >\n" +
                "check_next0 3 -> rj _ ^\n" +
                "mid1 2 -> mid1 2 <\n" +
                "mid1 3 -> mid1 3 <\n" +
                "mid1 4 -> mid1 4 <\n" +
                "mid1 _ -> check_next1 _ >\n" +
                "check_next1 3 -> right _ >\n" +
                "check_next1 2 -> rj _ ^\n" +
                "right 3 -> right 3 >\n" +
                "right 2 -> right 2 >\n" +
                "right 4 -> rightR 4 >\n" +
                "rightR 4 -> rightR 4 >\n" +
                "rightR _ -> ac _ ^\n" +
                "rightR 3 -> mid1 4 <\n" +
                "rightR 2 -> mid0 4 <\n ";
        printWriter.println(s);
        printWriter.close();
    }
}
