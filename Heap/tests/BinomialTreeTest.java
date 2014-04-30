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
}
