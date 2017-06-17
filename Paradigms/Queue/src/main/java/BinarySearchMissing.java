public class BinarySearchMissing {
    // pre: для всех i, j (0 <= i < j < a.length) => a[i] >= a[j]
    // post: x ∈ a => R == min(i) (i == 0..a.length - 1): a[i] == x,
    //      x ∉ a => R == -(insertion point + 1), где insertion point == min(i)
    //      (i == 0..a.length - 1): a[i] < x или insertion point == a.length,
    //      если для всех i (i == 0..a.length - 1) a[i] > x
    public static int binarySearchIterative(int[] a, int x) {
        int l = -1;
        int r = a.length;
        // I: l >= -1 ∧ r <= a.length ∧ l < r - 1 ∧ x < a[l] ∧ x >= a[r] (a[-1] == inf, a[a.lentgth] == -inf)
        // pre: l >= -1 ∧ r <= a.length ∧ l < r - 1 ∧ x < a[l] ∧ x >= a[r] (a[-1] == inf, a[a.lentgth] == -inf)
        while (l < r - 1) {
            int m = (l + r) / 2;
            // pre: m >= 0 ∧ m <= a.length - 1 ∧ l < m < r ∧ m == l + (r - l) / 2
            if (a[m] <= x) {
                // pre: a[m] <= x
                r = m;
                // post a[r] <= x ∧ r' == l + (r - l) / 2
            } else {
                // pre: a[m] > x
                l = m;
                // post: a[l] > x ∧ l' == l + (r - l) / 2
            }
        }
        // post: l' >= -1 ∧ r' <= a.length ∧ l' < r' - 1 ∧ x < a[l']
        //      ∧ x >= a[r'] (a[-1] == inf, a[a.length] == -inf)
        //      ∧ r' - l' == (r - l) / 2
        // pre: r >= 0 ∧ r <= a.length ∧ r == min(i) (i == 0..a.length - 1): a[i] <= x
        if (r < a.length && x == a[r]) {
            // pre: r < a.length ∧ x == a[r] ∧ r == min(i) (i == 0..a.length - 1): a[i] == x
            return r;
            // post: R == min(i) (i == 0..a.length - 1): a[i] == x
        } else {
            // pre: r == min(i) (i == 0..a.length - 1): a[i] < x
            //      ∧ для всех i: 0 <= i < r ⇒ a[i] > x
            return -(r + 1);
            // post R == -(r + 1), где r == min(i)
            //      (i == 0..a.length - 1): a[i] < x или r == a.length,
            //      если для всех i (i == 0..a.length - 1) a[i] > x
        }
    }

    // pre: для всех i, j (0 <= i < j < a.length) ⇒ a[i] >= a[j]
    //      ∧ -1 <= l < r <= a.length
    //      ∧ x < a[l] ∧ x >= a[r] (a[-1] == inf, a[a.lentgth] == -inf)
    // post: x ∈ a => R == min(i) (i == 0..a.length - 1): a[i] == x,
    //      x ∉ a => R == -(insertion point + 1), где insertion point == min(i)
    //      (i == 0..a.length - 1): a[i] < x или insertion point == a.length,
    //      если для всех i (i == 0..a.length - 1) a[i] > x
    public static int binarySearchRecursive(int[] a, int l, int r, int x) {
        // pre: l >= -1 ∧ r <= a.length ∧ l < r - 1 ∧ x < a[l] ∧ x >= a[r] (a[-1] == inf, a[a.lentgth] == -inf)
        if (l < r - 1) {
            //pre: l < r - 1
            int m = (l + r) / 2;
            // pre: l < m < r
            if (a[m] <= x) {
                // pre: a[m] <= x
                return binarySearchRecursive(a, l, m, x);
                // post: x ∈ a => R == min(i) (i == 0..a.length - 1): a[i] <= x,
                //      x ∉ a => R == -(insertion point + 1), где insertion point == min(i)
                //      (i == 0..a.length - 1): a[i] < x или insertion point == a.length,
                //      если для всех i (i == 0..a.length - 1) a[i] > x
            } else {
                // pre: a[m] > x
                return binarySearchRecursive(a, m, r, x);
                // post: x ∈ a => R == min(i) (i == 0..a.length - 1): a[i] == x,
                //      x ∉ a => R == -(insertion point + 1), где insertion point == min(i)
                //      (i == 0..a.length - 1): a[i] < x или insertion point == a.length,
                //      если для всех i (i == 0..a.length - 1) a[i] > x
            }
        }
        // post: R == R' ∨ R == R'', где ' — результат BinarySearchRecursive(x, a, l, m)
        //      и R'' — результат BinarySearchRecursive(x, a, m, r)
        // post: l' >= -1 ∧ r' <= a.length ∧ l' < r' - 1 ∧ x < a[l']
        //      ∧ x >= a[r'] (a[-1] == inf, a[a.length] == -inf)
        //      ∧ r' - l' == (r - l) / 2
        // pre: 0 <= r <= a.length ∧ r == min(i) (i == 0..a.length - 1): a[i] <= x
        if (r < a.length && x == a[r]) {
            // pre: r < a.length ∧ x == a[r] ∧ r == min(i) (i == 0..a.length - 1): a[i] == x
            return r;
            // post: R == min(i) (i == 0..a.length - 1): a[i] == x
        } else {
            // pre: r == min(i) (i == 0..a.length - 1): a[i] < x
            //      ∧ всех i: 0 <= i < r => a[i] > x
            return -(r + 1);
            // post R == -(r + 1), где r == min(i)
            //      (i == 0..a.length - 1): a[i] < x или r == a.length,
            //      если для всех i (i == 0..a.length - 1) a[i] > x
        }
    }


    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            a[i] = Integer.parseInt(args[i + 1]);
        }
        System.out.println(binarySearchIterative(a, x));
        //System.out.println(binarySearchRecursive( a, -1, a.length,x));
    }
}
