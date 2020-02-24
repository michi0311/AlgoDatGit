package ab2.impl.MaroltLiebhartKrainer;

import ab2.AuDSortedTree;


public class SortedTreeImpl implements AuDSortedTree {
    private TreeNode root;
    private int size = 0;
    private int[] orderArray;
    private int currentIndex = 0;

    class TreeNode {
        private TreeNode leftChild = null;
        private TreeNode rightChild = null;
        private int data;

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
    }

    /**
     *
     * @param value you want to add
     * @return i
     */
    @Override
    public boolean add(int value) {
        if(root == null) {
            root = new TreeNode(value);
            size++;
            return true;
        } else {
            TreeNode current = root;
            while (current != null) {
                if (current.data < value) {
                    if (current.getRightChild() == null) {
                        current.setRightChild(new TreeNode(value));
                        size++;
                        return true;
                    }
                    current = current.getRightChild();
                } else if (current.data > value) {
                    if (current.getLeftChild() == null) {
                        current.setLeftChild(new TreeNode(value));
                        size++;
                        return true;
                    }
                    current = current.getLeftChild();
                } else {
                    return false;
                }
            }

        }
        return false;
    }

    @Override
    public boolean contains(int value) {
        if (findElement(value) == null) return false;
        return true;
    }

    /**
     * zero child: works
     * left child: works
     * right child: works
     * two childs: works
     * @param value you want to delete
     * @return if the item was deleted
     */
    @Override
    public boolean delete(int value) {
        TreeNode prevNode = root;
        TreeNode current = root;
        while (current.getData() != value) {
            if (current.getData() > value) {
                prevNode = current;
                current = current.getLeftChild();
            } else if (current.getData() < value) {
                prevNode = current;
                current = current.getRightChild();
            }
            if (current == null) {
                return false;
            }
        }
        size--;
        if (current.getRightChild() == null && current.getLeftChild() == null) {
            if (prevNode.getRightChild() == current) {
                prevNode.rightChild = null;
                return true;
            } else {
                prevNode.leftChild = null;
                return true;
            }
        } else if (current.getRightChild() == null) {
            if (prevNode.getRightChild() == current) {
                prevNode.rightChild = current.getLeftChild();
                return true;
            } else {
                prevNode.leftChild = current.getLeftChild();
                return true;
            }
        } else if (current.getLeftChild() == null) {
            if (prevNode.getRightChild() == current) {
                prevNode.rightChild = current.getRightChild();
                return true;
            } else {
                prevNode.leftChild = current.getRightChild();
                return true;
            }
        }
        int smallest = smallestValue(current.rightChild);
        current.data = smallest;
        delete(current.rightChild,smallest);
        return true;
    }

    private boolean delete(TreeNode node, int value) {
        TreeNode prevNode = root;
        TreeNode current = node;
        while (current.getData() != value) {
            if (current.getData() > value) {
                prevNode = current;
                current = current.getLeftChild();
            } else if (current.getData() < value) {
                prevNode = current;
                current = current.getRightChild();
            }
            if (current == null) {
                return false;
            }
        }
        if (current.getRightChild() == null && current.getLeftChild() == null) {
            if (prevNode.getRightChild() == current) {
                prevNode.rightChild = null;
                return true;
            } else {
                prevNode.leftChild = null;
                return true;
            }
        } else if (current.getRightChild() == null) {
            if (prevNode.getRightChild() == current) {
                prevNode.rightChild = current.getLeftChild();
                return true;
            } else {
                prevNode.leftChild = current.getLeftChild();
                return true;
            }
        } else if (current.getLeftChild() == null) {
            if (prevNode.getRightChild() == current) {
                prevNode.rightChild = current.getRightChild();
                return true;
            } else {
                prevNode.leftChild = current.getRightChild();
                return true;
            }
        }
        int smallest = smallestValue(current.rightChild);
        current.data = smallest;
        delete(current.rightChild,smallest);
        return true;
    }

    private int smallestValue(TreeNode node) {
        while(node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node.getData();
    }

    private TreeNode findElement(int value) {
        TreeNode current = root;
        while (current.getData() != value) {
            if (current.getData() > value) {
                current = current.getLeftChild();
            } else if (current.getData() < value) {
                current = current.getRightChild();
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     *
     * @param order Ordering.LWR, RWL, LRW, RLW, WLR, WRL
     * @return Ordered Array
     */
    @Override
    public int[] toArray(Ordering order) {
        orderArray = new int[size];
        currentIndex = 0;

        if (order == Ordering.LWR) {
            LWROrder(root);
        } else if (order == Ordering.RWL) {
            RWLOrder(root);
        } else if (order == Ordering.LRW) {
            LRWOrder(root);
        }else if (order == Ordering.RLW) {
            RLWOrder(root);
        }else if (order == Ordering.WLR) {
            WLROrder(root);
        }else if (order == Ordering.WRL) {
            WRLOrder(root);
        }

        return orderArray;
    }

    private void LWROrder(TreeNode node){
        if (node != null) {
            LWROrder(node.getLeftChild());
            addToArray(node.getData());
            LWROrder(node.getRightChild());
        }

    }

    private void RWLOrder(TreeNode node){
        if (node != null) {
            RWLOrder(node.getRightChild());
            addToArray(node.getData());
            RWLOrder(node.getLeftChild());
        }
    }

    private void LRWOrder(TreeNode node) {
        if (node != null) {
            LRWOrder(node.getLeftChild());
            LRWOrder(node.getRightChild());
            addToArray(node.getData());
        }
    }

    private void RLWOrder(TreeNode node) {
        if (node != null) {
            RLWOrder(node.getRightChild());
            RLWOrder(node.getLeftChild());
            addToArray(node.getData());
        }
    }

    private void WLROrder(TreeNode node) {
        if (node != null) {
            addToArray(node.getData());
            WLROrder(node.getLeftChild());
            WLROrder(node.getRightChild());
        }
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

    @Override
    public int[] getLeafs() {
        orderArray = new int[(size/2)+1];
        currentIndex = 0;
        findLeafs(root);
        return orderArray;
    }

    public void findLeafs(TreeNode node) {
        if (node != null) {
            findLeafs(node.getLeftChild());
            findLeafs(node.getRightChild());
            if (node.getRightChild() == null && node.getLeftChild() == null) {
                addToArray(node.getData());
            }
        }
    }

    @Override
    public int min() {
        TreeNode current = root;
        while(current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current.getData();
    }

    @Override
    public int max() {
        TreeNode current = root;
        while(current.getRightChild() != null) {
            current = current.getRightChild();
        }
        return current.getData();
    }


}
