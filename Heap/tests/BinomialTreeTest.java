import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BinomialTreeTest {

    @Test
    public void BasicTest() {
        LazyBinomialHeap heap = new LazyBinomialHeap();
        heap.enqueue(3);
        assertEquals(heap.min(), 3);
    }

    @Test
    public void ExtractMinTest() {
        LazyBinomialHeap heap = new LazyBinomialHeap();
        for (int i = 0; i < 20; i++) {
            heap.enqueue(i);
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            assertEquals(i, heap.extractMin());
        }


        for (ListNode<BinomialTree> treeListNode : heap.getTrees()) {
            System.out.println(treeListNode.getValue().getSize());
        }
    }
}
