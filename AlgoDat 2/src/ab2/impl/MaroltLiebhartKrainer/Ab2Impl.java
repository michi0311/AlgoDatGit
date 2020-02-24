package ab2.impl.MaroltLiebhartKrainer;

import ab2.Ab2;
import ab2.AuDHashSet;
import ab2.AuDQueue;
import ab2.AuDQueue.Type;
import ab2.AuDSortedTree;

public class Ab2Impl implements Ab2 {

    @Override
    public AuDHashSet emptyHashSet(int capacity) {
        return new HashSetImpl(capacity);
    }

    @Override
    public AuDSortedTree emptyTree() {
        return new SortedTreeImpl();
    }

    @Override
    public AuDQueue emptyQueue(Type type) {
	    return new QueueImpl(type);
    }

}
