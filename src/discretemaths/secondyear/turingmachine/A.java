package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 03.06.2018
 */
public class A {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("zero.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s _ -> ac _ ^\n" +
                "s 0 -> n 0 >\n" +
                "n 0 -> s 0 >\n" +
                "n _ -> rj _ >";
        printWriter.println(s);
        printWriter.close();
    }
}
