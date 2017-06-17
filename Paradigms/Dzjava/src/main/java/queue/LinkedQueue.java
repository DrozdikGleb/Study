package queue;
public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }
    }

    protected void enqueueImpl(Object element) {
        Node ntail = tail;
        tail = new Node(element, null);
        if (size == 0) {
            head = tail;
        } else {
            ntail.next = tail;
        }

    }

    protected void remove() {
        head = head.next;
    }

    protected Object[] toArrayImpl(Object [] newElements) {
        Node node = head;
        int s = size;
        while (s != 0) {
            newElements[size - s] = node.value;
            node = node.next;
            s--;
        }

        return newElements;
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected void clearImpl() {
        head = null;
        tail = null;
    }

}
