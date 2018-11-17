package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 05.06.2018
 */
public class J {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("postfixlogic.out")));
        String s = "3\n" +
                "S 0 _ _ -> S _ > 0 > _ ^\n" +
                "S 1 _ _ -> S _ > 1 > _ ^\n"
                +
                "S a _ _ -> S _ > a > _ ^\n"
                +
                "S o _ _ -> S _ > o > _ ^\n"
                +
                "S _ _ _ -> to_start _ ^ _ < _ ^\n"
                +
                "to_start _ 0 _ -> to_start _ ^ 0 < _ ^\n" +
                "to_start _ 1 _ -> to_start _ ^ 1 < _ ^\n" +
                "to_start _ o _ -> to_start _ ^ o < _ ^\n" +
                "to_start _ a _ -> to_start _ ^ a < _ ^\n" +
                "to_start _ _ _ -> SS _ ^ _ > _ ^\n"
                +
                "SS _ 0 _ -> SS _ ^ _ > 0 >\n" +
                "SS _ 1 _ -> SS _ ^ _ > 1 >\n" +
                "SS _ a _ -> And _ ^ a ^ _ <\n" +
                "SS _ o _ -> Or _ ^ o ^ _ <\n" +
                "And _ a 0 -> check_prev_and_0 _ ^ a ^ _ <\n" +
                "check_prev_and_0 _ a 0 -> SS _ ^ _ > 0 >\n" +
                "check_prev_and_0 _ a 1 -> SS _ ^ _ > 0 >\n" +
                "And _ a 1 -> check_prev_and_1 _ ^ a ^ _ < \n" +
                "check_prev_and_1 _ a 1 -> SS _ ^ _ > 1 >\n" +
                "check_prev_and_1 _ a 0 -> SS _ ^ _ > 0 >\n" +
                "Or _ o 0 -> check_prev_or_0 _ ^ o ^ _ <\n" +
                "Or _ o 1 -> check_prev_or_1 _ ^ o ^ _ <\n" +
                "check_prev_or_0 _ o 0 -> SS _ ^ _ > 0 >\n" +
                "check_prev_or_0 _ o 1 -> SS _ ^ _ > 1 >\n" +
                "check_prev_or_1 _ o 0 -> SS _ ^ _ > 1 >\n" +
                "check_prev_or_1 _ o 1 -> SS _ ^ _ > 1 >\n" +
                "SS _ _ _ -> ans _ ^ _ ^ _ <\n" +
                "ans _ _ 1 -> AC 1 ^ _ ^ _ ^\n" +
                "ans _ _ 0 -> AC 0 ^ _ ^ _ ^\n ";

        printWriter.println(s);
        printWriter.close();
    }
}
