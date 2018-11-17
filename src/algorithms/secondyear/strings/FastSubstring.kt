
import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val str1 = input.nextLine()
    val str2 = input.nextLine()
    val str = str1 + "#" + str2
    val l1 = str1.length
    val l2 = str2.length
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
    val ans = ArrayList<Int>()
    for (i in (l1 + 1)..(l2 + 1)) {
        if (z[i] == l1) {
            ans.add(i - l1)
        }
    }
    println(ans.size)
    for (i in 0 until ans.size) {
        print("${ans[i]} ")
    }


}