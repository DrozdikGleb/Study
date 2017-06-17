package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue {
    private int head;
    private int tail;
    private Object[] elements = new Object[5];

    protected void enqueueImpl(Object element) {
        ensureCapacity(tail + 1);
        elements[tail++] = element;
    }

    protected Object[] toArrayImpl(Object[] array) {

            for (int i = 0; i < size; i++) {
                array[i] = elements[head + i];
            }
            return array;

    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        for (int i = 0; i < tail - head; i++) {
            newElements[i] = elements[head + i];
        }
        elements = newElements;
        tail = tail - head;
        head = 0;
    }

    protected void remove() {
        head++;
    }

    protected Object elementImpl() {
        return elements[head];
    }
  /*  protected ArrayQueue mapImpl(Function<Object,Object> function){
        ArrayQueue queue = new ArrayQueue();
        int anotherh=head;
        for (int i = 0; i < size ; i++) {
            queue.enqueue(function.apply(elements[anotherh]));
            anotherh=(anotherh+1)
        }
        return queue;
    }
    protected ArrayQueue filterImpl(Predicate<Object> predicate){
        ArrayQueue queue = new ArrayQueue();
        int anotherh=head;
        for (int i = 0; i < size ; i++) {
           if (predicate.test(elements[anotherh]))
            anotherh=(anotherh+1)
        }
    }*/

    protected void clearImpl() {
        head = 0;
        tail = 0;
        elements = new Object[elements.length];
    }


}

