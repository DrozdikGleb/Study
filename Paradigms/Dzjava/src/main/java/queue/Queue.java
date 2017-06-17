package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Queue {
    // a[i]-последовательность объектов
    //Inv: size>=0 ∧ для любого i a[i]: oт 0 до size-1 != null
    // pre: element!=null
    // post: size = size' + 1 ∧ a[i]=a'[i] ∧a[size'] = element
    void enqueue(Object element);

    // pre: size > 0
    // post: R =a[0]∧ a[i]=a'[i] ∧ size = size' − 1
    Object dequeue();

    // pre: size > 0
    // post:R = a[0]∧ все элементы неизменны ∧ size = size'
    Object element();

    //post R=size∧ все элементы не меняются ∧ size=size'
    int size();

    // post: if (size ==0) =>true else false ∧ size=size'
    boolean isEmpty();

    // post: size=0
    void clear();

    //pre:size>=0
    //post : R=newArray ∧ элементы не меняются ∧ size=size'
    Object[] toArray();

   /* Queue filter (Predicate<Object> predicate);
    Queue map (Function<Object, ?> f) throws IllegalAccessException;*/

}
