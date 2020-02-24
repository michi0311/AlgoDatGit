package ab2.impl.MaroltLiebhartKrainer;

import ab2.AuDQueue;



public class QueueImpl implements AuDQueue {
    private DoubleLinkedNodeImpl Head;
    private DoubleLinkedNodeImpl Tail;
    private Type queueType;

    public QueueImpl(Type queueType) {
        this.queueType = queueType;
    }

    class DoubleLinkedNodeImpl {
        private DoubleLinkedNodeImpl nextNode = null;
        private DoubleLinkedNodeImpl prevNode = null;
        private int data;

        public DoubleLinkedNodeImpl(DoubleLinkedNodeImpl prev, DoubleLinkedNodeImpl next, int data) {
            nextNode = next;
            prevNode = prev;
            this.data = data;
        }

        private DoubleLinkedNodeImpl(int data) {
            this.data = data;
        }

        private DoubleLinkedNodeImpl getNext() {
            return nextNode;
        }

        private void setNextNode(DoubleLinkedNodeImpl nextNode) {
            this.nextNode = nextNode;
        }

        private DoubleLinkedNodeImpl getPrev() {
            return prevNode;
        }

        private void setPrevNode(DoubleLinkedNodeImpl prevNode) {
            this.prevNode = prevNode;
        }

        private int getData() {
            return data;
        }
    }

    @Override
    public void enqueue(int value) {
        DoubleLinkedNodeImpl x = new DoubleLinkedNodeImpl(value);
        if (Head == null && Tail == null) {
            Head = x;
            Tail = Head;
        } else {
            Tail.setNextNode(x);
            x.setPrevNode(Tail);
            Tail = x;
        }
    }

    @Override
    public int dequeue() {
        int val;
        if(Head == null && Tail == null) {
            return -1;
        } else if (queueType == Type.FIFO) {
            val = Head.getData();
            if(Head.nextNode != null) {
                Head.nextNode.setPrevNode(null);
                Head = Head.nextNode;
            } else {
                Head = null;
                Tail = null;
            }
        } else {
            val = Tail.getData();
            if (Tail.prevNode != null){
                Tail.prevNode.setNextNode(null);
                Tail = Tail.prevNode;
            } else {
                Head = null;
                Tail = null;
            }
        }
        return val;
    }
}
