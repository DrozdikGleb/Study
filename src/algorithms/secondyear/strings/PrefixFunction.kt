import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main(args: Array<String>) {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val out = PrintWriter(System.out)
    val str: String = bufferedReader.readLine()
    val l = str.length
    val p = IntArray(l, { 0 })
    for (i in 1 until l) {
        var k = p[i - 1]
        while (str[i] != str[k]) {
            if (k == 0) break
            k = p[k - 1]
        }
        if (str[i] == str[k]) {
            k++
        }
        p[i] = k
    }
    for (i in 0 until l){
        out.print("${p[i]} ")
    }
    out.close()
}