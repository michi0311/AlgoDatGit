package ab3.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import ab3.Ab3;
import ab3.impl.MaroltLiebhart.Ab3Impl;

public class Tests {

    private static Ab3 impl = new Ab3Impl();

    private static int pts = 0;

    @AfterClass
    public static void printPts() {
	System.out.println("Erreichte Punkte: " + pts);
    }

    @Test
    public void lzw1() throws IOException {
	lzwFromFile("data1.txt");

	pts += 1;
    }

    @Test
    public void lzw2() throws IOException {
	lzwFromFile("data2.txt");

	pts += 1;
    }

    @Test
    public void lzw3() throws IOException {
	lzwFromFile("data3.txt");

	pts += 1;
    }

    @Test
    public void lzw4() throws IOException {
	lzwFromFile("data4.txt");

	pts += 1;
    }

    @Test
    public void lzw5() throws IOException {
	lzwFromFile("data5.txt");

	pts += 1;
    }

    @Test
    public void shortestPath1() {
	Integer[][] costMatrix = new Integer[5][5];

	int[] path = impl.shortestPath(costMatrix, 0, 0);

	Assert.assertArrayEquals(new int[] { 0 }, path);

	pts += 1;
    }

    @Test
    public void shortestPath2() {
	Integer[][] costMatrix = new Integer[5][5];
	costMatrix[0][1] = 1;
	costMatrix[1][2] = 1;
	costMatrix[2][3] = 1;
	costMatrix[3][4] = 1;
	costMatrix[0][4] = 1;

	int[] path = impl.shortestPath(costMatrix, 0, 4);

	Assert.assertArrayEquals(new int[] { 0, 4 }, path);

	pts += 1;
    }

    @Test
    public void shortestPath3() {
	Integer[][] costMatrix = new Integer[5][5];
	costMatrix[0][1] = 1;
	costMatrix[1][2] = 1;
	costMatrix[2][3] = 1;
	costMatrix[3][4] = -5;
	costMatrix[0][4] = 1;

	int[] path = impl.shortestPath(costMatrix, 0, 4);

	Assert.assertArrayEquals(new int[] { 0, 1, 2, 3, 4 }, path);

	pts += 1;
    }

    @Test
    public void shortestPathExtended() {
	// wird im Rahmen der Bewertung getestet

	// pts += 2;
    }

    @Test
    public void buildTree1() {
	int[] order = impl.buildTree(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });

	Assert.assertArrayEquals(new int[] { 4, 6, 8, 9, 7, 5, 2, 3, 1 }, order);

	pts += 1;
    }

    @Test
    public void buildTree2() {
	int[] order = impl
		.buildTree(new int[] { 2, 4, 3, 1, 5, 6, 10, 9, 8, 11, 12, 20, 14, 13, 16, 17, 18, 19, 7, 15 });

	Assert.assertArrayEquals(new int[] { 11, 14, 17, 19, 20, 18, 16, 15, 12, 13, 6, 9, 10, 8, 7, 3, 5, 4, 2, 1 },
		order);

	pts += 2;
    }

    @Test
    public void buildTreeExtended() {
	// wird im Rahmen der Bewertung getestet

	// pts += 2;
    }

    private void lzwFromFile(String filename) throws IOException {
	BufferedReader reader;

	filename = "src/ab3/test/" + filename;

	reader = new BufferedReader(new FileReader(filename));
	String inputString = reader.readLine();

	String compressedHexString = reader.readLine().replace(" ", "");

	reader.close();

	byte[] compressedBytes = new BigInteger(compressedHexString, 16).toByteArray();

	byte[] uncompressedBytes = impl.uncompress(compressedBytes);

	String uncompressedString = new String(uncompressedBytes);

	Assert.assertEquals(inputString, uncompressedString);
    }

}
