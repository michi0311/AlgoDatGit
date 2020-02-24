package ab2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.Test;

import ab2.Ab2;
import ab2.AuDHashSet;
import ab2.AuDQueue;
import ab2.AuDQueue.Type;
import ab2.AuDSortedTree;
import ab2.AuDSortedTree.Ordering;
import ab2.impl.MaroltLiebhartKrainer.Ab2Impl;

public class Tests {
    private static int ptsHashSet = 0;
    private static boolean passedHashSet = false;
    private static int ptsQueue = 0;
    private static int ptsSortedTree = 0;

    public static final int DATA_SIZE = 1_000_000;

    private Random rand = new Random(System.currentTimeMillis());

    private Ab2 impl = new Ab2Impl();

    /**************** Queue (4 Pts) ****************/

    @Test
    public void fifo() {
	fifoSimple();
	fifoExtended();
    }

    private void fifoSimple() {
	int[] data = random(DATA_SIZE);

	AuDQueue queue = impl.emptyQueue(Type.FIFO);

	for (int d : data) {
	    queue.enqueue(d);
	}

	for (int i = 0; i < DATA_SIZE; i++) {
	    assertEquals(data[i], queue.dequeue());
	}

	ptsQueue += 1;
    }

    private void fifoExtended() {
	// Nicht veröffentlicht. Wird zusätzlich für die Abgabebewertung verwendet (1
	// Pts möglich)
    }

    @Test
    public void lifo() {
	lifoSimple();
	lifoExtended();
    }

    private void lifoSimple() {
	int[] data = random(DATA_SIZE);

	AuDQueue queue = impl.emptyQueue(Type.LIFO);

	for (int d : data) {
	    queue.enqueue(d);
	}

	for (int i = DATA_SIZE - 1; i >= 0; i--) {
	    assertEquals(data[i], queue.dequeue());
	}

	ptsQueue += 1;
    }

    private void lifoExtended() {
	// Nicht veröffentlicht. Wird zusätzlich für die Abgabebewertung verwendet (1
	// Pts möglich)
    }

    /**************** HashSet (4 Pts) ****************/

    @Test
    public void hashSetAddAndContains() {
	hashSetAddAndContainsBasic();
	hashSetAddAndContainsExtended();
    }

    private void hashSetAddAndContainsBasic() {
	AuDHashSet hashSet = impl.emptyHashSet(DATA_SIZE);
	HashSet<Long> hashSetRef = new HashSet<>();

	for (int i = 0; i < DATA_SIZE; i++) {
	    long val = rand.nextLong() % DATA_SIZE;

	    hashSet.add(val);
	    hashSetRef.add(val);
		//System.out.println(val + "x" + i);

	    long val2 = rand.nextLong() % DATA_SIZE;
		//System.out.println(val2);


	    assertEquals(hashSet.contains(val2), hashSetRef.contains(val2));
		//System.out.println(i);
	}

	ptsHashSet += 3;
    }

    private void hashSetAddAndContainsExtended() {
	// Nicht veröffentlicht. Wird zusätzlich für die Abgabebewertung verwendet (1
	// Pts möglich)
    }

    @Test
    public void hashSetAddAndContainsSpeed() {
	AuDHashSet hashSet = impl.emptyHashSet(DATA_SIZE);
	HashSet<Long> hashSetRef = new HashSet<>();

	long[] data = randomLong(DATA_SIZE);
	long[] data2 = randomLong(DATA_SIZE);

	long start = System.currentTimeMillis();
	int i = 0;
	for (long d : data) {
	    hashSet.add(d);

	    long val2 = data2[i];

	    hashSet.contains(val2);

	    i++;
	}
	long end = System.currentTimeMillis();

	long timeHashSet = end - start;

	start = System.currentTimeMillis();
	i = 0;
	for (long d : data) {
	    hashSetRef.add(d);

	    long val2 = data2[i];

	    assertEquals(hashSet.contains(val2), hashSetRef.contains(val2));

	    i++;
	}
	end = System.currentTimeMillis();

	long timeHashSetRef = end - start;

	assertTrue(timeHashSet < timeHashSetRef * 3);

	passedHashSet = true;
    }

    /**************** SortedTree (7 Pts) ****************/

    @Test
    public void sortedTreeAddDeleteAndContains() {
	int[] data = random(DATA_SIZE);
	AuDSortedTree tree = impl.emptyTree();
	Set<Integer> refTree = new TreeSet<>();

	for (int i : data) {
	    tree.add(i);
	    refTree.add(i);
	}

	for (int i = 0; i < DATA_SIZE; i++) {
	    int r = rand.nextInt();

	    assertEquals(refTree.contains(r), tree.contains(r));
	}

	ptsSortedTree += 2;
    }

    @Test
    public void sortedTreeToArray() {
	int[] data = random(DATA_SIZE);
	AuDSortedTree tree = impl.emptyTree();
	Set<Integer> refTree = new TreeSet<>();

	for (int i : data) {
	    tree.add(i);
	    refTree.add(i);
	}

	int[] rwl = tree.toArray(Ordering.RWL);
	int[] lwr = tree.toArray(Ordering.LWR);

	List<Integer> rwlList = Arrays.stream(rwl).mapToObj(i -> i).collect(Collectors.toList());
	List<Integer> lwrList = Arrays.stream(lwr).mapToObj(i -> i).collect(Collectors.toList());

	List<Integer> refOrdering = refTree.stream().collect(Collectors.toList());
	refOrdering.sort(Comparator.comparing(e -> e));

	assertEquals(refOrdering, lwrList);

	refOrdering.sort(Comparator.comparing(e -> -e));

	assertEquals(refOrdering, rwlList);

	ptsSortedTree += 2;
    }

    @Test
    public void sortedTreeMinMax() {
	int[] data = random(DATA_SIZE);
	AuDSortedTree tree = impl.emptyTree();

	for (int i : data) {
	    tree.add(i);
	}

	int min = tree.min();
	int max = tree.max();

	int minRef = Arrays.stream(data).min().getAsInt();
	int maxRef = Arrays.stream(data).max().getAsInt();

	assertEquals(minRef, min);
	assertEquals(maxRef, max);

	ptsSortedTree += 2;
    }

    @Test
    public void sortedTreeExtra() {
	// Nicht veröffentlicht. Wird zusätzlich für die Abgabebewertung verwendet (1
	// Pts möglich)
    }

    /**************** Helper Methods ****************/

    @AfterClass
    public static void printPts() {
	int pts = (passedHashSet ? ptsHashSet : 0) + ptsQueue + ptsSortedTree;

	if (!passedHashSet) {
	    System.out.println("Die Basistests für das HashSet wurden nicht bestanden.");
	}

	System.out.println("Gesamtpunkte: " + pts);
    }

    private long[] randomLong(int n) {
	long[] arr = new long[n];

	for (int i = 0; i < n; i++) {
	    arr[i] = rand.nextLong();
	}

	return arr;
    }

    private int[] random(int n) {
	int[] arr = new int[n];

	for (int i = 0; i < n; i++) {
	    arr[i] = rand.nextInt(10 * n);
	}

	return arr;
    }
}
