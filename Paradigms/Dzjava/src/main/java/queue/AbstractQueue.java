package queue;

import java.util.function.Function;
import java.util.function.Predicate;

abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object element() {
        assert size > 0;

        return elementImpl();
    }

    protected abstract Object elementImpl();

    public Object[] toArray() {
        Object[] newElements = new Object[size];
        return toArrayImpl(newElements);
    }

    protected abstract Object[] toArrayImpl(Object[] mas);

    public Object dequeue() {
        assert size > 0;
        Object result = element();
        size--;
        remove();
        return result;
    }

    protected abstract void remove();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void clear() {
        size = 0;
        clearImpl();
    }

    protected abstract void clearImpl();
  /*  public Queue filter(Predicate<Object> p) throws IllegalAccessException, InstantiationException {
        Queue q = this.getClass().newInstance();
        for (int i = 0; i < size(); i++) {
            Object obj = dequeue();
            if (p.test(obj)) {
                q.enqueue(obj);
            }
            enqueue(obj);
        }
        return q;
    }

    public Queue map(Function<Object, ?> f) throws IllegalAccessException, InstantiationException {
        Queue q = this.getClass().newInstance();
        for (int i = 0; i < size(); i++) {
            Object obj = dequeue();
            q.enqueue(f.apply(obj));
            enqueue(obj);
        }
        return q;
    }*/


}
