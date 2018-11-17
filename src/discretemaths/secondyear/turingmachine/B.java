package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 04.06.2018
 */
public class B {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("aplusb.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s 1 -> r 1 >\n" +
                "s 0 -> r 0 >\n" +
                "r 0 -> r 0 >\n" +
                "r 1 -> r 1 >\n" +
                "r + -> r + >\n" +
                "r _ -> take _ <\n" +
                "take 0 -> plus0 _ <\n" +
                "take 1 -> plus1 _ <\n" +
                "plus1 0 -> plus1 0 <\n" +
                "plus1 1 -> plus1 1 <\n" +
                "plus1 + -> plus11 + <\n" +
                "plus11 3 -> plus11 3 <\n" +
                "plus11 2 -> plus11 2 <\n" +
                "plus11 0 -> back 3 >\n" +
                "plus11 _ -> back 3 >\n" +
                "plus11 1 -> plus111 2 <\n" +
                "plus111 1 -> plus111 0 <\n" +
                "plus111 0 -> back 1 >\n" +
                "plus111 _ -> back 1 >\n" +
                //"plus111 1 -> plus111 2 <\n" +
                "plus0 0 -> plus0 0 <\n" +
                "plus0 1 -> plus0 1 <\n" +
                "plus0 + -> plus00 + <\n" +
                "plus00 0 -> back 2 >\n" +
                "plus00 1 -> back 3 >\n" +
                "plus00 2 -> plus00 2 <\n" +
                "plus00 3 -> plus00 3 <\n" +
                "plus00 _ -> back 2 >\n" +
                "back 2 -> back 2 >\n" +
                "back 3 -> back 3 >\n" +
                "back + -> back + >\n" +
                "back 1 -> back 1 > \n" +
                "back 0 -> back 0 >\n" +
                "back _ -> take _ <\n" +
                "take + -> clear _ <\n" +
                "clear 2 -> clear 0 <\n" +
                "clear 3 -> clear 1 <\n" +
                "clear 1 -> clear 1 <\n" +
                "clear 0 -> clear 0 <\n" +
                "clear _ -> finish _ >\n" +
                "finish 0 -> ac 0 ^\n" +
                "finish 1 -> ac 1 ^\n" +
                "";
        printWriter.println(s);
        printWriter.close();
    }
}
