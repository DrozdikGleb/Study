import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.PrintWriter

fun main(args: Array<String>) {
    val bufferedReader = BufferedReader(FileReader("in"))
    val out = PrintWriter(File("outt"))
    val str2: String = bufferedReader.readLine()
    val l1 = str2.length
    val str = str2 + str2
    val l = str.length
    val z = IntArray(l, { 0 })
    var left = 0
    var right = 0
    for (i in 1 until l) {
        z[i] = Math.max(0, Math.min(right - i, z[i - left]))
        while ((i + z[i] < l) && (str[z[i]] == str[i + z[i]])) {
            z[i]++
        }
        if (i + z[i] > right) {
            left = i
            right = i + z[i]
        }
    }
    var k = 1

    for (it in 1 until l1) {
        if (z[it] < l1 && str[z[it]] > str[it + z[it]]) {
            System.out.print("${z[it]} ${str[z[it]]}")
            System.out.print(" ")
            println(str[it + z[it]])
            k++
        }
    }
    //val k = 1 + (1 until l1).count { z[it]<l1 && str[z[it]]>str[it +z[it]] }
    out.print(k)
    out.close()
}