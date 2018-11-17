package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 04.06.2018
 */
public class C {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("mirror.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s 1 -> r 1 >\n" +
                "s 0 -> r 0 >\n" +
                "r 1 -> r 1 >\n" +
                "r 0 -> r 0 >\n" +
                "r _ -> take _ <\n" +
                "take 0 -> put0 2 >\n" +
                "put0 2 -> put0 2 >\n" +
                "put0 3 -> put0 3 >\n" +
                "put0 0 -> put0 0 >\n" +
                "put0 1 -> put0 1 >\n" +
                "put0 _ -> back 0 <\n" +
                "take 1 -> put1 3 >\n" +
                "put1 2 -> put1 2 >\n" +
                "put1 3 -> put1 3 >\n" +
                "put1 0 -> put1 0 >\n" +
                "put1 1 -> put1 1 >\n" +
                "put1 _ -> back 1 <\n" +
                "back 0 -> back 0 <\n" +
                "back 1 -> back 1 <\n" +
                "back 2 -> back_back 2 <\n" +
                "back 3 -> back_back 3 <\n" +
                "back_back 2 -> back_back 2 <\n" +
                "back_back 3 -> back_back 3 <\n" +
                "back_back 1 -> put1 3 >\n" +
                "back_back 0 -> put0 2 >\n" +
                "back_back _ -> change _ >\n" +
                "change 1 -> change 1 >\n" +
                "change 0 -> change 0 >\n" +
                "change 2 -> change 0 >\n" +
                "change 3 -> change 1 >\n" +
                "change _ -> to_start _ <\n" +
                "to_start 1 -> to_start 1 <\n" +
                "to_start 0 -> to_start 0 <\n" +
                "to_start _ -> finish _ >\n" +
                "finish 0 -> ac 0 ^\n" +
                "finish 1 -> ac 1 ^\n";

        printWriter.println(s);
        printWriter.close();
    }
}
