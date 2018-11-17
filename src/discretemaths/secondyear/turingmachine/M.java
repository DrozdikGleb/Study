package discretemaths.secondyear.turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Gleb on 12.06.2018
 */
public class M {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("sorting.out")));
        String s = "3\n" +
                "S 0 _ _ -> copy 0 > 0 > _ ^\n" +
                "S 1 _ _ -> copy 1 > 1 > _ ^\n" +
                "S | _ _ -> copy | > | > _ ^\n" +

                "copy 1 _ _ -> copy 1 > 1 > _ ^\n"+
                "copy 0 _ _ -> copy 0 > 0 > _ ^\n" +
                "copy | _ _ -> copy | > | > _ ^\n"+
                "copy _ _ _ -> check _ < _ < _ ^\n" +

                //проверяем 0 ли число, если да, то выводим его
                "check 0 0 _ -> check 0 < 0 < _ ^\n" +
                "check 1 0 _ -> check 1 < 0 < _ ^\n" +
                "check 1 1 _ -> to_last_num 1 > 1 > _ ^\n" +
                "check 0 1 _ -> to_last_num 0 > 1 > _ ^\n" +
                "check ? ? _ -> next_num ? < ? < _ ^\n" +
                "check | | _ -> print_to_3_tape | > | > _ ^\n" +
                "check _ _ _ -> check_last _ > _ > _ ^\n" +

                "check_last 0 1 _ -> to_end _ > _ > _ ^\n" +
                "check_last 1 1 _ -> to_end _ > _ > _ ^\n" +
                "check_last ? ? _ -> to_end _ > _ > _ ^\n" +
                "check_last 0 0 _ -> to_last_num_last 0 > 0 > _ ^\n" +
                "check_last 1 0 _ -> to_last_num_last 1 > 0 > _ ^\n" +

                "to_last_num_last 0 0 _ -> to_last_num_last 0 > 0 > _ ^\n" +
                "to_last_num_last 1 0 _ -> to_last_num_last 1 > 0 > _ ^\n" +
                "to_last_num_last 0 1 _ -> to_end 0 > 1 > _ ^\n" +
                "to_last_num_last 1 1 _ -> to_end 1 > 1 > _ ^\n" +
                "to_last_num_last | | _ -> to_left | < | < _ ^\n" +
                "to_last_num_last _ _ _ -> to_left _ < _ < _ ^\n" +

                "to_left 1 0 _ -> to_left 1 < 0 < _ ^\n" +
                "to_left 0 0 _ -> to_left 0 < 0 < _ ^\n" +
                "to_left 1 1 _ -> to_left 1 < 1 < _ ^\n" +
                "to_left 0 1 _ -> to_left 0 < 1 < _ ^\n" +
                "to_left _ _ _ -> print_to_3_tape_last _ > _ > _ ^\n" +

                "print_to_3_tape_last 1 0 _ -> print_to_3_tape_last ? > ? > 1 > \n" +
                "print_to_3_tape_last 0 0 _ -> print_to_3_tape_last ? > ? > 0 > \n" +
                "print_to_3_tape_last _ _ _ -> to_end _ ^ _ ^ | >\n" +
                "print_to_3_tape_last | | _ -> to_end | < | < | >\n" +

                "" +
                //вывод числа
                "print_to_3_tape 1 0 _ -> print_to_3_tape ? > ? > 1 > \n" +
                "print_to_3_tape 0 0 _ -> print_to_3_tape ? > ? > 0 > \n" +
                "print_to_3_tape _ _ _ -> next_num _ < _ < | >\n" +
                "print_to_3_tape | | _ -> next_num | < | < | >\n" +

                //следующее число
                "next_num 1 0 _ -> next_num 1 < 0 < _ ^ \n" +
                "next_num 0 0 _ -> next_num 0 < 0 < _ ^ \n" +
                "next_num 1 1 _ -> next_num 1 < 1 < _ ^ \n" +
                "next_num 0 1 _ -> next_num 0 < 1 < _ ^ \n" +
                "next_num | | _ -> check | < | < _ ^ \n" +
                "next_num ? ?  _ -> next_num ? < ? < _ ^ \n" +
                "next_num _ _ _ -> to_end _ > _ > _ ^ \n" +

                //возвращаемся к младшему биту
                "to_last_num 1 1 _ -> to_last_num 1 > 1 > _ ^ \n" +
                "to_last_num 0 1 _ -> to_last_num 0 > 1 > _ ^ \n" +
                "to_last_num 1 0 _ -> to_last_num 1 > 0 > _ ^ \n" +
                "to_last_num 0 0 _ -> to_last_num 0 > 0 > _ ^ \n" +
                "to_last_num _ _ _ -> sub _ < _ < _ ^ \n" +
                "to_last_num | | _ -> sub | < | < _ ^ \n" +

                "sub 1 1 _ -> next_num 1 < 0 < _ ^\n" +
                "sub 0 1 _ -> next_num 0 < 0 < _ ^\n" +
                "sub 1 0 _ -> sub 1 < 1 < _ ^ \n" +
                "sub 0 0 _ -> sub 0 < 1 < _ ^ \n" +
                "sub | | _ -> check | < | < _ ^ \n" +

                //идём в самую левую часть и проверяем затем все ли вопросики
                "to_end 1 0 _ -> to_end 1 > 0 > _ ^\n" +
                "to_end 0 0 _ -> to_end 0 > 0 > _ ^\n" +
                "to_end 0 1 _ -> to_end 0 > 1 > _ ^\n" +
                "to_end 1 1 _ -> to_end 1 > 1 > _ ^\n" +
                "to_end ? ? _ -> to_end ? > ? > _ ^\n" +
                "to_end | | _ -> to_end | > | > _ ^\n" +
                "to_end _ _ _ -> check? _ < _ < _ ^\n" +
                //проверяем все ли у нас вопросики, если да, то копируем из первой ленты в 3
                "check? ? ? _ -> check? ? < ? < _ ^\n" +
                "check? | | _ -> check? | < | < _ ^\n" +
                "check? 1 0 _ -> to_simple_end 1 > 0 > _ ^\n" +
                "check? 0 0 _ -> to_simple_end 0 > 0 > _ ^\n" +
                "check? 1 1 _ -> to_simple_end 1 > 1 > _ ^\n" +
                "check? 0 1 _ -> to_simple_end 0 > 1 > _ ^\n" +
                "check? _ _ _ -> clean_first _ > _ ^  _ ^\n" +

                "clean_first ? _ _ -> clean_first _ > _ ^ _ ^\n" +
                "clean_first | _ _ -> clean_first _ > _ ^ _ ^\n" +
                "clean_first _ _ _ -> delete_last _ ^ _ ^ _ <\n" +

                "delete_last _ _ | -> copy_to_first _ < _ ^ _ <\n" +

                "copy_to_first _ _ 1 -> copy_to_first 1 < _ ^ _ <\n" +
                "copy_to_first _ _ 0 -> copy_to_first 0 < _ ^ _ <\n" +
                "copy_to_first _ _ | -> copy_to_first | < _ ^ _ <\n" +
                "copy_to_first _ _ _ -> AC _ > _ ^ _ ^\n" +
                //возвращаемся обратно и запускаем обычный алгоритм
                "to_simple_end 1 0 _ -> to_simple_end 1 > 0 > _ ^\n" +
                "to_simple_end 0 0 _ -> to_simple_end 0 > 0 > _ ^\n" +
                "to_simple_end 0 1 _ -> to_simple_end 0 > 1 > _ ^\n" +
                "to_simple_end 1 1 _ -> to_simple_end 1 > 1 > _ ^\n" +
                "to_simple_end ? ? _ -> to_simple_end ? > ? > _ ^\n" +
                "to_simple_end | | _ -> to_simple_end | > | > _ ^\n" +
                "to_simple_end _ _ _ -> check _ < _ < _ ^\n" +
               "";

        printWriter.println(s);
        printWriter.close();
    }
}
