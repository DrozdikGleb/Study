package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 04.06.2018
 */
public class F {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("reverse.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s 1 -> r 1 >\n" +
                "s 0 -> r 0 >\n" +
                "r 1 -> L1 ? <\n" +
                "r 0 -> L0 ? <\n" +
                "r _ -> Clear _ <\n" +
                "L1 ? -> L1 ? <\n" +
                "L1 1 -> L1 1 <\n" +
                "L1 0 -> L1 0 <\n" +
                "L1 _ -> RR 1 >\n" +
                "L0 ? -> L0 ? <\n" +
                "L0 1 -> L0 1 <\n" +
                "L0 0 -> L0 0 <\n" +
                "L0 _ -> RR 0 >\n" +
                "RR 0 -> RR 0 >\n" +
                "RR 1 -> RR 1 >\n" +
                "RR ? -> RRR ? >\n" +
                "RRR ? -> RRR ? >\n" +
                "RRR 0 -> L0 ? <\n" +
                "RRR 1 -> L1 ? <\n" +
                "RRR _ -> Clear _ <\n" +
                "Clear ? -> Clear _ <\n" +
                "Clear 1 -> Go 1 <\n" +
                "Clear 0 -> Go 0 <\n" +
                "Go 1 -> Go 1 <\n" +
                "Go 0 -> Go 0 <\n" +
                "Go _ -> finish _ >\n" +
                "finish 1 -> ac 1 ^\n" +
                "finish 0 -> ac 0 ^\n" +
                "";

        printWriter.println(s);
        printWriter.close();
    }
}
