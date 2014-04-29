import java.util.Iterator;

/**
 * A doubly-linked list that supports O(1) concatenation. You are not required
 * to implement this class, but we strongly recommend doing so as a stepping
 * stone toward building the LazyBinomialHeap.
 */
public class MeldableLinkedList<T> implements Iterable<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;


    /**
     * Must meld tail to head.
     *
     * @param list
     */
    public void concat(MeldableLinkedList<T> list) {
        tail.setNext(list.getHead());
        list.getHead().setPrev(tail);
        tail = list.getTail();
        list.setHead(head);
        size += list.getSize();
    }


    public void append(T value) {
        ListNode<T> node = new ListNode<T>(value);

        if (this.head == null) {
            head = node;
        }

        if (this.tail == null) {
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }

        this.size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(head);
    }

    public void setHead(ListNode<T> head) {
        this.head = head;
    }

    public ListNode<T> getHead() {
        return head;
    }

    public ListNode<T> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    private class LinkedListIterator<T> implements Iterator<T>{
        private ListNode<T> current;

        private LinkedListIterator(ListNode<T> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.getValue();
            current = current.getNext();
            return value;
        }

        @Override
        public void remove() {
            return;
        }
    }
}
