package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 05.06.2018
 */
public class G {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("less.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s 1 -> r 1 >\n" +
                "s 0 -> r 0 >\n" +
                "r 1 -> r 1 >\n" +
                "r 0 -> r 0 >\n" +
                "r 2 -> r 2 >\n" +
                "r 3 -> r 3 >\n" +
                "r < -> r < >\n" +
                "r _ -> take _ <\n" +
                "take 0 -> comp0 _ <\n" +
                "comp0 0 -> comp0 0 <\n" +
                "comp0 1 -> comp0 1 <\n" +
                "comp0 < -> comp00 < <\n" +
                "comp00 2 -> comp00 2 <\n" +
                "comp00 3 -> comp00 3 <\n" +
                "comp00 1 -> r 2 >\n" +
                "comp00 0 -> check_prev 0 >\n" +
                "comp00 _ -> ac _ ^\n" +
                "take < -> check _ <\n" +
                "check 2 -> check 2 <\n" +
                "check 3 -> check 3 <\n" +
                "check 1 -> rj _ ^\n" +
                "check 0 -> rj _ ^\n" +
                "check _ -> last _ >\n" +
                "last 3 -> ac _ ^\n" +
                "last 2 -> rj _ ^\n" +
                "take 1 -> comp1 _ <\n" +
                "comp1 0 -> comp1 0 <\n" +
                "comp1 1 -> comp1 1 <\n" +
                "comp1 < -> comp11 < <\n" +
                "comp11 2 -> comp11 2 <\n" +
                "comp11 3 -> comp11 3 <\n" +
                "comp11 0 -> r 3 >\n" +
                "comp11 1 -> check_prev 1 >\n" +
                "comp11 _ -> ac _ ^\n" +
                "check_prev 3 -> print3 3 <\n" +
                "print3 1 -> r 3 >\n"+
                "print3 0 -> r 3 >\n" +
                "check_prev 2 -> print2 2 <\n" +
                "print2 1 -> r 2 >\n" +
                "print2 0 -> r 2 >\n" +
                "check_prev < -> print2 < <\n" +
                ""+

                "";
        printWriter.println(s);
        printWriter.close();
    }
}
