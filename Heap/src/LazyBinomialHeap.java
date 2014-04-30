import java.util.LinkedList;
import java.util.Queue;

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
    ListNode<BinomialTree> minTreeNode;
    int numNodes;

    public MeldableLinkedList<BinomialTree> getTrees() {
        return trees;
    }

    /**
     * Constructs a new, empty LazyBinomialHeap.
     */
    public LazyBinomialHeap() {
    }

    /**
     * Returns whether the lazy binomial heap is empty.
     *
     * @return Whether this lazy binomial heap is empty.
     */
    public boolean isEmpty() {
        return trees.getSize() == 0;
    }

    /**
     * Adds the specified key to the priority queue. Duplicate values are
     * allowed.
     *
     * @param key The key to add.
     */
    public void enqueue(int key) {
        BinomialTree newTree = new BinomialTree(key);
        ListNode<BinomialTree> newNode = trees.append(newTree);
        if (minTreeNode == null || newTree.getKey() < minTreeNode.getValue().getKey()) {
            this.minTreeNode = newNode;
        }
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

        return minTreeNode.getValue().getKey();
    }

    /**
     * Removes and returns the minimum element of the priority queue. This
     * method can assume that the priority queue is nonempty.
     *
     * @return The formed minimum element of the priority queue.
     */
    public int extractMin() {
        assert !isEmpty() : "Priority queue is empty!";

        int minValue = minTreeNode.getValue().getKey();
        numNodes--;

        MeldableLinkedList<BinomialTree> newTrees = minTreeNode.getValue().extractRoot();
        trees.remove(minTreeNode);
        if (newTrees.getSize() > 0) {
            trees.concat(newTrees);
        }

        if (numNodes > 1) {
            coalesceTrees();
        }

        updateMin();

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
        int targetTreeCount = (int) Math.ceil(Math.log(numNodes) / Math.log(2));
        ListNode<BinomialTree> treeSizes[] = new ListNode[targetTreeCount];
        Queue<ListNode<BinomialTree>> treesToCoalesce = new LinkedList<ListNode<BinomialTree>>();

        for (ListNode<BinomialTree> treeNode : trees) {
            treesToCoalesce.add(treeNode);
        }

        while (treesToCoalesce.size() > targetTreeCount) {
            ListNode<BinomialTree> treeNode = treesToCoalesce.remove();
            BinomialTree tree = treeNode.getValue();

            int treeIndex = (int) Math.floor(Math.log(tree.getSize()) / Math.log(2));

            if (treeSizes[treeIndex] == null) {
                treeSizes[treeIndex] = treeNode;
            } else {
                BinomialTree newTree = BinomialTree.coalesce(treeSizes[treeIndex].getValue(), tree);
                treesToCoalesce.add(trees.append(newTree));
                trees.remove(treeNode);
                trees.remove(treeSizes[treeIndex]);
                treeSizes[treeIndex] = null;
            }
        }
    }

    private void updateMin() {
        minTreeNode = trees.getHead();

        for (ListNode<BinomialTree> treeNode: trees) {
            if (treeNode.getValue().getKey() < minTreeNode.getValue().getKey()) {
                minTreeNode = treeNode;
            }
        }
    }
}
