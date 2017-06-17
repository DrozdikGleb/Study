/**
 * Created by Drozdov Gleb on 18.05.2017.
 */
public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }
    }

    public void add(Object element) {
        Node newTail = tail;
        tail = new Node(element, null);
        if (size == 0) {
            head = tail;
        } else {
            newTail.next = tail;
        }
    }

    public void remove() {
        head = head.next;
    }

    public Object find(Object element) {
        Node node = head;
        while ((node != null) && (element != node.value)) {
            node = node.next;
        }
        return node;
    }


}
