package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 10.06.2018
 */
public class I {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("multiplication.out")));
        String s = "start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +

                "s 1 -> print# 1 < \n" +
                "s 0 -> print# 0 <\n" +

                "print# _ -> check0 # >\n" +
                "check0 1 -> check0 1 >\n" +
                "check0 0 -> check0 0 >\n" +
                "check0 * -> check00 * >\n" +

                "check00 1 -> to_end_first 1 >\n" +
                "check00 0 -> ans_0 _ <\n" +

                "ans_0 1 -> ans_0 _ <\n" +
                "ans_0 0 -> ans_0 _ <\n" +
                "ans_0 * -> ans_0 _ <\n" +
                "ans_0 # -> ac 0 ^\n" +


                "to_end_first 1 -> to_end_first 1 >\n" +
                "to_end_first 0 -> to_end_first 0 >\n" +
                "to_end_first * -> to_end_first * >\n" +
                "to_end_first _ -> sub1 _ <\n" +

                "sub1 1 -> to_multiply_skip 0 <\n" +
                "sub1 0 -> sub1 1 <\n" +
                "sub1 * -> to_end * >\n" +

                "to_multiply_skip 1 -> to_multiply_skip 1 <\n" +
                "to_multiply_skip 0 -> to_multiply_skip 0 <\n" +
                "to_multiply_skip * -> to_multiply * <\n" +

                "to_multiply 1 -> plus1_skip 3 <\n" +
                "to_multiply 0 -> plus0_skip 2 <\n" +
                "to_multiply 3 -> to_multiply 3 <\n" +
                "to_multiply 2 -> to_multiply 2 <\n" +
                "to_multiply # -> to_start # <\n" +

                "plus1_skip 1 -> plus1_skip 1 <\n" +
                "plus1_skip 0 -> plus1_skip 0 <\n" +
                "plus1_skip # -> plus1 # <\n" +
                "plus0_skip 1 -> plus0_skip 1 <\n" +
                "plus0_skip 0 -> plus0_skip 0 <\n" +
                "plus0_skip # -> plus0 # <\n" +

                "plus1 3 -> plus1 3 <\n" +
                "plus1 2 -> plus1 2 <\n" +
                "plus1 0 -> back 3 >\n" +
                "plus1 1 -> plus11 2 <\n" +
                "plus1 _ -> check_prev _ >\n" +

                "plus11 0 -> back 1 >\n" +
                "plus11 1 -> plus11 0 <\n" +
                "plus11 _ -> back 1 >\n" +

                "check_prev # -> print3 # <\n" +
                "check_prev 2 -> print1 2 <\n" +
                "check_prev 1 -> print1 1 <\n" +
                "check_prev 3 -> print1 3 <\n" +
                "check_prev 0 -> print1 0 <\n" +

                "print3 _ -> back 3 >\n" +
                "print1 _ -> back 3 >\n" +

                "plus0 3 -> plus0 3 <\n" +
                "plus0 2 -> plus0 2 <\n" +
                "plus0 1 -> back 3 >\n" +
                "plus0 0 -> back 2 >\n" +
                "plus0 _ -> back 2 >\n" +

                "back 3 -> back 3 >\n" +
                "back 2 -> back 2 >\n" +
                "back 1 -> back 1 >\n" +
                "back 0 -> back 0 >\n" +
                "back # -> back_second # >\n" +

                "back_second 1 -> back_second 1 >\n" +
                "back_second 0 -> back_second 0 >\n" +
                "back_second 2 -> to_multiply 2 <\n" +
                "back_second 3 -> to_multiply 3 <\n" +

                "to_start 2 -> to_start 2 <\n" +
                "to_start 1 -> to_start 1 <\n" +
                "to_start 0 -> to_start 0 <\n" +
                "to_start 3 -> to_start 3 <\n" +
                "to_start _ -> clear_2_num _ >\n" +

                "clear_2_num 1 -> clear_2_num 1 >\n" +
                "clear_2_num 0 -> clear_2_num 0 >\n" +
                "clear_2_num 2 -> clear_2_num 0 >\n" +
                "clear_2_num 3 -> clear_2_num 1 >\n" +
                "clear_2_num # -> clear_2_num # >\n" +
                "clear_2_num * -> to_end_first * >\n" +

                "to_end 1 -> to_end 1 >\n" +
                "to_end 0 -> to_end 0 >\n" +
                "to_end _ -> clean _ <\n" +

                "clean 0 -> clean _ <\n" +
                "clean 1 -> clean _ <\n" +
                "clean 2 -> clean _ <\n" +
                "clean 3 -> clean _ <\n" +
                "clean * -> clean _ <\n" +
                "clean # -> to_ans _ <\n" +

                "to_ans 1 -> to_ans 1 <\n" +
                "to_ans 0 -> to_ans 0 <\n" +
                "to_ans _ -> ac _ >\n" +

                "";
        printWriter.println(s);
        printWriter.close();
    }
}
