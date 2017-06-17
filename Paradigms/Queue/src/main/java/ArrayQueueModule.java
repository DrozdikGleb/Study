
public class ArrayQueueModule {
    //I : для всех i от 0.. elements.length-1 elements[i]!=null ∧ size>=0
    private static int size;
    private static int head;
    private static int tail;
    private static Object[] elements = new Object[5];

    // pre: element ≠ null
    // post: size = size' + 1 ∧ elements[i]=elements'[i] ∧elements[tail] = element ∧ tail++
    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(tail + 1);
        elements[tail++] = element;
        size++;

    }

    // post:
    //if(size>elements.length)
    //      size=size' ∧ elements.length=2*(elements'.length)
    //      ∧для i=0..tail elements[i]=elements'[head+i] ∧ tail=tail'-head ∧head=0
    //
    //else return
    //
    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }

        Object[] newElements = new Object[2 * capacity];
        for (int i = 0; i < tail; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    // pre: size > 0
    // post: R = elements[head++]∧ elements[i]=elements'[i] ∧ size = size' − 1
    public static Object dequeue() {
        assert size > 0;
        size--;
        return elements[head++];
    }

    // pre: size > 0
    // post:R = elements[head]∧ elements[i]=elements'[i] ∧ size = size'
    public static Object element() {
        assert size > 0;

        return elements[head];
    }

    //post R=size∧ elements[i]=elements'[i] ∧ size=size'
    public static int size() {
        return size;
    }

    // post: size=0∧ elements[i]=elements'[i]∧ head=0 ∧tail=0
    public static void clear() {
        head = 0;
        tail = 0;
        size = 0;
        elements = new Object[elements.length];
    }

    // post: if (size ==0) =>true else false ∧ size=size'
    public static boolean isEmpty() {
        return size == 0;
    }

}
