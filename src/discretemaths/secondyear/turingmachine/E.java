package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 04.06.2018
 */
public class E {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("balanced.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s _ -> ac _ ^\n" +
                "s ( -> right ( >\n" +
                "s ) -> rj _ ^\n" +
                "right ) -> left 0 <\n" +
                "left 0 -> left 0 <\n" +
                "left ( -> right 0 >\n" +
                "left _ -> rj _ ^\n" +
                "right ( -> right ( >\n" +
                "right _ -> last _ <\n" +
                "right 0 -> right 0 >\n" +
                "last 0 -> last 0 <\n" +
                "last ( -> rj _ ^\n" +
                "last ) -> rj _ ^\n" +
                "last _ -> ac _ ^\n";

        printWriter.println(s);
        printWriter.close();
    }
}
