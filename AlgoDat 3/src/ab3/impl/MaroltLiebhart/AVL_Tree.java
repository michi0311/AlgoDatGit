package ab3.impl.MaroltLiebhart;


/****************************
 * Created by Michael Marolt *
 *****************************/

public class AVL_Tree {
    TreeNode root;
    private int size;
    private int[] orderArray;
    private int currentIndex = 0;

    class TreeNode {
        private TreeNode leftChild = null;
        private TreeNode rightChild = null;
        private int data;
        private int height = 1;

        TreeNode(int data) {
            this.data = data;
        }

        int getData() {
            return data;
        }

        TreeNode getLeftChild() {
            return leftChild;
        }

        void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        TreeNode getRightChild() {
            return rightChild;
        }

        void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

        int getHeight() {
            return height;
        }

        void setHeight(int height) {
            this.height = height;
        }
    }

    public AVL_Tree(int arraySize) {
        size = arraySize;
    }

    TreeNode insert(TreeNode node, int data) {
        if (node == null)
            return (new TreeNode(data));

        if (data < node.data)
            node.leftChild = insert(node.leftChild, data);
        else if (data > node.data)
            node.rightChild = insert(node.rightChild, data);
        else
            return node;

        node.height = 1 + max(updateHeight(node.leftChild),
                updateHeight(node.rightChild));

        int balance = getBalance(node);

        if (balance > 1 && data < node.leftChild.data)
            return rightRotate(node);

        if (balance < -1 && data > node.rightChild.data)
            return leftRotate(node);

        if (balance > 1 && data > node.leftChild.data) {
            node.leftChild = leftRotate(node.leftChild);
            return rightRotate(node);
        }

        if (balance < -1 && data < node.rightChild.data) {
            node.rightChild = rightRotate(node.rightChild);
            return leftRotate(node);
        }

        return node;
    }

    private int updateHeight(TreeNode t) {
        if (t == null) {
            return 0;
        }
        return t.height;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getBalance(TreeNode t) {
        if (t == null)
            return 0;

        return updateHeight(t.leftChild) - updateHeight(t.rightChild);
    }

    private TreeNode leftRotate(TreeNode t) {
        TreeNode x = t.rightChild;
        TreeNode T2 = x.leftChild;

        x.leftChild = t;
        t.rightChild = T2;

        t.height = max(updateHeight(t.leftChild), updateHeight(t.rightChild)) + 1;
        x.height = max(updateHeight(x.leftChild), updateHeight(x.rightChild)) + 1;
        return x;
    }

    private TreeNode rightRotate(TreeNode t) {
        TreeNode x = t.leftChild;
        TreeNode T2 = x.rightChild;
        x.rightChild = t;
        t.leftChild = T2;
        t.height = max(updateHeight(t.leftChild), updateHeight(t.rightChild)) + 1;
        x.height = max(updateHeight(x.leftChild), updateHeight(x.rightChild)) + 1;
        return x;
    }

    public int[] toArray() {
        orderArray = new int[size];
        currentIndex = 0;
        WRLOrder(root);
        return orderArray;
    }

    private void WRLOrder(TreeNode node) {
        if (node != null) {
            addToArray(node.getData());
            WRLOrder(node.getRightChild());
            WRLOrder(node.getLeftChild());
        }
    }

    private void addToArray(int value){
        orderArray[currentIndex++] = value;
    }

}
