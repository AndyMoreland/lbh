import junit.framework.TestCase;

public class LazyBinomialHeapTest extends TestCase {

    public void testIsEmpty() throws Exception {

    }

    public void testEnqueue() throws Exception {

    }

    public void testMin() throws Exception {
        LazyBinomialHeap heap = new LazyBinomialHeap();
        heap.enqueue(3);
        heap.enqueue(4);
        heap.enqueue(2);
        heap.enqueue(3);
        heap.enqueue(5);
        assertEquals(2, heap.min());
        assertEquals(2, heap.min());
    }

    public void testExtractMin() throws Exception {
        LazyBinomialHeap heap = new LazyBinomialHeap();
        heap.enqueue(3);
        heap.enqueue(4);
        heap.enqueue(2);
        heap.enqueue(3);
        heap.enqueue(5);
        assertEquals(2, heap.extractMin());
        assertEquals(3, heap.extractMin());
    }

    public void testMeld() throws Exception {

    }
}