package ab3.impl.MaroltLiebhart;

/****************************
 * Created by Michael Marolt *
 *****************************/

class easyList {
    private Node root;
    private int size = 0;

    class Node {
        Node nextElement;
        int data;

        Node(int data) {this.data=data;}
    }

    void add(int data) {
        if (root == null) {
            root = new Node(data);
            size ++;
        } else {
            Node i = root;
            while (i.nextElement != null) i = i.nextElement;
            i.nextElement = new Node(data);
            size++;
        }
    }

    int[] getData() {
        int[] out = new int[size];
        Node x = root;
        for (int i = size-1; i >= 0; i--,x = x.nextElement) {
            out[i] = x.data;
        }
        return out;
    }
}

public class floydWarshallAlgorithmClass {
    private Integer[][] nextMatrix;

    public floydWarshallAlgorithmClass(Integer[][] adjacencyMatrix) {
        nextMatrix = new Integer[adjacencyMatrix.length][adjacencyMatrix.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                nextMatrix[i][j] = i;
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                }else if (adjacencyMatrix[i][j] == null) {
                    adjacencyMatrix[i][j] = Integer.MAX_VALUE;
                    nextMatrix[i][j] = null;
                }
            }
        }
        floydWarshallAlgorithm(adjacencyMatrix);
    }

    public int[] getPath(int from ,int to) {
        easyList out = new easyList();
        if (nextMatrix[from][to] == null) {
            return null;
        }
        out.add(to);
        while (from != to) {
            to = nextMatrix[from][to];
            out.add(to);
        }
        return out.getData();
    }

    private void floydWarshallAlgorithm(Integer[][] x) {
        for (int k = 0; k < x.length; k++) {
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x.length; j++) {
                    if (x[i][k] == Integer.MAX_VALUE || x[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (x[i][j] > x[i][k] + x[k][j]) {
                        x[i][j] = x[i][k] + x[k][j];
                        nextMatrix[i][j] = nextMatrix[k][j];
                    }
                }
            }
        }
    }
}
