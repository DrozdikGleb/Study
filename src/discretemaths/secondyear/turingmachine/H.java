package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 05.06.2018
 */
public class H {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("convertto2.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s 2 -> print# 2 <\n" +
                "s 0 -> ac 0 ^\n" +
                "s 1 -> print# 1 <\n" +

                "print# _ -> right # >\n" +
                "right 1 -> right 1 >\n" +
                "right 2 -> right 2 >\n" +
                "right 0 -> right 0 >\n" +
                "right _ -> sub _ <\n" +
                "sub 1 -> left 0 <\n" +
                "sub 2 -> left 1 <\n" +
                "sub 0 -> check_sub0 2 <\n" +
                "check_sub0 # -> clear_line # >\n" +
                "check_sub0 1 -> left 0 <\n" +
                "check_sub0 2 -> left 1 <\n" +
                "check_sub0 0 -> check_sub0 2 <\n " +

                "sub # -> plus # <\n" +

                "left 0 -> left 0 <\n" +
                "left 1 -> left 1 <\n" +
                "left 2 -> left 2 <\n" +
                "left # -> plus # <\n"+

                "plus _ -> back 1 >\n" +
                "plus 0 -> back 1 >\n" +
                "plus 1 -> plus 0 <\n" +

                "back 1 -> back 1 >\n" +
                "back 0 -> back 0 >\n" +
                "back # -> right # >\n" +

                "clear_line 0 -> clear_line _ >\n" +
                "clear_line 1 -> clear_line _ >\n" +
                "clear_line 2 -> clear_line _ >\n" +
                "clear_line _ -> to_start _ >\n" +
                "to_start _ -> to_start _ <\n" +
                "to_start # -> to_start2 _ <\n" +

                "to_start2 1 -> to_start2 1 <\n" +
                "to_start2 0 -> to_start2 0 <\n" +
                "to_start2 _ -> ac _ >\n" +
                "";
        printWriter.println(s);
        printWriter.close();
    }
}
