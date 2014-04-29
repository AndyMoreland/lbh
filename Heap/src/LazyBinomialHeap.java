/**
 * An implementation of a priority queue backed by a lazy binomial heap. Each
 * binomial tree in this heap should be represented using the left-child/right-
 * sibling representation, and the binomial heaps should be stored in a doubly-
 * linked list (though not necessarily a circularly-linked list).
 *
 * For simplicity, we will not make a distinction between keys and values in
 * this priority queue.
 */
public class LazyBinomialHeap {

    MeldableLinkedList<BinomialTree> trees = new MeldableLinkedList<BinomialTree>();
    BinomialTree minTree;
    int numNodes;

    public MeldableLinkedList<BinomialTree> getTrees() {
        return trees;
    }

    /**
     * Constructs a new, empty LazyBinomialHeap.
     */
    public LazyBinomialHeap() {
        // TODO: Fill this in!
    }

    /**
     * Returns whether the lazy binomial heap is empty.
     *
     * @return Whether this lazy binomial heap is empty.
     */
    public boolean isEmpty() {
        return trees.getSize() > 0;
    }

    /**
     * Adds the specified key to the priority queue. Duplicate values are
     * allowed.
     *
     * @param key The key to add.
     */
    public void enqueue(int key) {
        trees.append(new BinomialTree(key));


        numNodes++;
    }

    /**
     * Returns the minimum key in the priority queue. This method can assume
     * that the priority queue is not empty.
     *
     * @return The minimum key in the priority queue.
     */
    public int min() {
        assert !isEmpty() : "Priority queue is empty!";

        return minTree.getKey();
    }

    /**
     * Removes and returns the minimum element of the priority queue. This
     * method can assume that the priority queue is nonempty.
     *
     * @return The formed minimum element of the priority queue.
     */
    public int extractMin() {
        assert !isEmpty() : "Priority queue is empty!";

        int minValue = minTree.getKey();
        numNodes--;

        MeldableLinkedList<BinomialTree> newTrees = minTree.extractRoot();
        trees.concat(newTrees);

        coalesceTrees();

        return minValue;
    }

    /**
     * Melds together the two input priority queues into a single priority
     * queue. After this method is called on two priority queues, both of the
     * input queues should not be used again in the future and any operations
     * performed on them will have unspecified behavior.
     *
     * @param one The first queue to meld.
     * @param two The second queue to meld.
     * @return A queue consisting of all the keys in both input queues.
     */
    public static LazyBinomialHeap meld(LazyBinomialHeap one,
                                        LazyBinomialHeap two) {
        one.getTrees().concat(two.getTrees());

        return one;
    }


    private void coalesceTrees() {
        BinomialTree[] treeSizes = new BinomialTree[(int) Math.ceil(Math.log(numNodes) / Math.log(2))];

        for (BinomialTree tree : treeSizes) {
            int treeSize = tree.getSize();
        }
    }
}
