package ab3.impl.MaroltLiebhart;

import ab3.Ab3;

public class Ab3Impl implements Ab3 {

    @Override
    public byte[] uncompress(byte[] compressedData) {
        //does not work
        //LZW_Decoder code = new LZW_Decoder(compressedData);
        return null;
    }

    @Override
	public int[] shortestPath(Integer[][] costMatrix, int from, int to) {
        floydWarshallAlgorithmClass FWA = new floydWarshallAlgorithmClass(costMatrix);
        return FWA.getPath(from, to);
    }

    @Override
    public int[] buildTree(int[] keys) {
	    AVL_Tree x = new AVL_Tree(keys.length);
        for (int i = 0; i < keys.length; i++) {
            x.root = x.insert(x.root,keys[i]);
        }
	    return x.toArray();
    }

}