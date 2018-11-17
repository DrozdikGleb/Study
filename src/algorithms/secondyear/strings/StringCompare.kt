import java.io.*
import java.util.*

class StringCompare {
    private lateinit var input: FastScanner
    internal lateinit var out: PrintWriter

    @Throws(IOException::class)
    fun solve() {
        out = PrintWriter(System.out)
        val str: String = input.next()
        val m: Int = input.nextInt()
        val l = str.length
        val p = 31
        val pow = Array<Long>(l, { 0 })
        pow[0] = 1
        for (i in 1 until l)
            pow[i] = (pow[i - 1] * p)
        val hashes = Array<Long>(l, { 0 })
        for (i in 0 until l) {
            hashes[i] = ((str[i] - 'a' + 1) * pow[i])
            if (i != 0) {
                hashes[i] = (hashes[i - 1] + hashes[i])
            }
        }
        for (i in 0 until m) {
            val a = input.nextInt() - 1
            val b = input.nextInt() - 1
            val c = input.nextInt() - 1
            val d = input.nextInt() - 1
            val cutPrefix1: Long
            val cutPrefix2: Long
            cutPrefix1 = if (a == 0) {
                0
            } else {
                hashes[a - 1]
            }
            cutPrefix2 = if (c == 0) {
                0
            } else {
                hashes[c - 1]
            }
            val h1 = hashes[b] - cutPrefix1
            val h2 = hashes[d] - cutPrefix2
            if ((b - a) != (d - c)) {
                out.println("No")
            } else if (a == b && c == d && a != c) {
                if (str[a] == str[c]) {
                    out.println("Yes")
                } else {
                    out.println("No")
                }
            } else {
                if (((c >= a) && (h1 * pow[c - a] == h2)) || ((a > c) && (h1 == h2 * pow[a - c]))) {
                    out.println("Yes")
                } else {
                    out.println("No")
                }
            }
        }
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
            StringCompare().run()
        }
    }
}