import java.io.*
import java.util.*

class StringPeriod {
    private lateinit var input: FastScanner
    internal lateinit var out: PrintWriter

    @Throws(IOException::class)
    fun solve() {
        out = PrintWriter(System.out)
        val str: String = input.next()
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
        var period = l - p[l - 1]
        if (l % period != 0) {
            period = l
        }
        out.print(period)
    }

    fun run() {
        try {
            input = FastScanner()
            solve()
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    internal inner class FastScanner {
        lateinit var br: BufferedReader
        var st: StringTokenizer? = null

        init {
            try {
                br = BufferedReader(InputStreamReader(System.`in`))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }

        operator fun next(): String {
            while (st == null || !st!!.hasMoreTokens()) {
                try {
                    st = StringTokenizer(br.readLine())
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            return st!!.nextToken()
        }

        fun nextInt(): Int {
            return Integer.parseInt(next())
        }
    }

    companion object {

        @JvmStatic
        fun main(arg: Array<String>) {
            StringPeriod().run()
        }
    }
}