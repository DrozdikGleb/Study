
public class ArrayQueueADT {
    //I : для всех i от 0.. elements.length-1 elements[i]!=null ∧ size>=0
    private int size;
    private int head;
    private int tail;
    private Object[] elements = new Object[5];
    // pre: element ≠ null
    // post: size = size' + 1 ∧ elements[i]=elements'[i] ∧elements[tail] = element ∧ tail++

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, queue.tail + 1);
        queue.elements[queue.tail++] = element;
        queue.size++;

    }
    // post:
    //if(size>elements.length)
    //      size=size' ∧ elements.length=2*(elements'.length)
    //      ∧для i=0..tail elements[i]=elements'[head+i] ∧ tail=tail'-head ∧head=0
    //
    //else return
    //

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }

        Object[] newElements = new Object[2 * capacity];
        for (int i = 0; i < queue.tail; i++) {
            newElements[i] = queue.elements[i];
        }
        queue.elements = newElements;
    }

    // pre: size > 0
    // post: R = elements[head++]∧ elements[i]=elements'[i] ∧ size = size' − 1
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        queue.size--;
        return queue.elements[queue.head++];
    }

    // pre: size > 0
    // post:R = elements[head]∧ elements[i]=elements'[i] ∧ size = size'
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;

        return queue.elements[queue.head];
    }

    //post R=size∧ elements[i]=elements'[i] ∧ size=size'
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    // post: size=0∧ elements[i]=elements'[i]∧ head=0 ∧tail=0
    public static void clear(ArrayQueueADT queue) {
        queue.head = 0;
        queue.tail = 0;
        queue.size = 0;
        queue.elements = new Object[queue.elements.length];
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

}


