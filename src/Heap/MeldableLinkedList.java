package Heap;

import java.util.Iterator;

/**
 * A doubly-linked list that supports O(1) concatenation. You are not required
 * to implement this class, but we strongly recommend doing so as a stepping
 * stone toward building the LazyBinomialHeap.
 */
public class MeldableLinkedList<T> implements Iterable<T> {
    private T value;
    private MeldableLinkedList<T> prev;
    private MeldableLinkedList<T> next;

    public MeldableLinkedList<T> getPrev() {
        return prev;
    }

    public MeldableLinkedList<T> getNext() {
        return next;
    }

    public void setNext(MeldableLinkedList<T> node) {
        next = node;
    }

    public void setPrev(MeldableLinkedList<T> node) {
        prev = node;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Must meld tail to head.
     * @param list
     */
    public void append(MeldableLinkedList<T> list) {
        assert (this.next != null || list.getPrev() != null);

        this.setNext(list);
        list.setPrev(this);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }

    private class LinkedListIterator<T> implements Iterator<T>{
        private MeldableLinkedList<T> current;

        private LinkedListIterator(MeldableLinkedList<T> head) {
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
