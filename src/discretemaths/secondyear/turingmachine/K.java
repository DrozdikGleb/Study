package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 10.06.2018
 */
public class K {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("infixlogic.out")));
        String s =
                "3\n" +
                "S 1 _ _ -> S _ > 1 > _ ^\n" +
                "S 0 _ _ -> S _ > 0 > _ ^\n" +
                "S ( _ _ -> S _ > _ ^ ( > \n" +
                "S ) _ _ -> close_bracket _ ^ _ ^ _ <\n" +
                "S o _ _ -> addO _ ^ _ ^ _ <\n" +
                "S a _ _ -> addA _ ^ _ ^ _ <\n" +
                "S _ _ _ -> check_stack _ ^ _ ^ _ <\n" +

                "check_stack _ _ a -> check_stack _ ^ a > _ <\n" +
                "check_stack _ _ o -> check_stack _ ^ o > _ <\n" +
                "check_stack _ _ _ -> go_to_first _ ^ _ < _ ^\n" +

                "go_to_first _ a _ -> go_to_first _ ^ a < _ ^\n" +
                "go_to_first _ o _ -> go_to_first _ ^ o < _ ^\n" +
                "go_to_first _ 1 _ -> go_to_first _ ^ 1 < _ ^\n" +
                "go_to_first _ 0 _ -> go_to_first _ ^ 0 < _ ^\n" +
                "go_to_first _ _ _ -> SS _ ^ _ > _ ^\n" +

                "addO _ _ ( -> addOO _ ^ _ ^ ( >\n" +
                "addO _ _ o -> addO _ ^ o > _ <\n" +
                "addO _ _ a -> addO _ ^ a > _ <\n" +
                "addO _ _ _ -> S _ > _ ^ o >\n" +
                "addOO _ _ _ -> S _ > _ ^ o >\n"+

                "addA _ _ ( -> addAA _ ^ _ ^ ( >\n" +
                "addA _ _ _ -> S _ > _ ^ a >\n" +
                "addA _ _ o -> addAA _ > _ ^ a >\n" +
                "addA _ _ a -> addA _ ^ a > _ <\n" +
                "addAA _ _ _ -> S _ > _ ^ a >\n"+

                "close_bracket _ _ a -> close_bracket _ ^ a > _ <\n" +
                "close_bracket _ _ o -> close_bracket _ ^ o > _ <\n" +
                "close_bracket _ _ ( -> S _ > _ ^ _ ^\n" +


                ""+



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
                "ans _ _ 0 -> AC 0 ^ _ ^ _ ^\n " +
                "";
        printWriter.println(s);
        printWriter.close();
    }
}
