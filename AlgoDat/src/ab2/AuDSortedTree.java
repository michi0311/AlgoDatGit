package ab2;

public interface AuDSortedTree {

	// fügt ein Element in das TreeSet ein, und gibt "true" zurück, falls
	// erfolgreich, und "false", falls der Wert schon existiert
	//   value: der einzufügende Wert
    boolean add(int value);

	// gibt "true" zurück, falls Ihr TreeSet den Wert enthält; sonst "false"
	//   value: der zu suchende Wert
    boolean contains(int value);

	// löscht ein Element aus dem TreeSet, und gibt "true" zurück, falls
	// erfolgreich, und "false", falls der Wert schon existiert
    boolean delete(int value);

	// konvertiert das TreeSet in ein Array anhand der angegebenen
	// Traversierungs-Reihenfolge
    int[] toArray(Ordering order);

	// liefert alle Werte, die sich in den Blatt-Knoten des Baumes befinden,
	// als Array zurück. Die Reihenfolge ist hier irrelevant.
    int[] getLeafs();

	// liefert den kleinsten Wert im TreeSet zurück
    int min();

	// liefert den größten Wert im TreeSet zurück
    int max();

	// Enum der Traversierungs-Reihenfolgen
    enum Ordering {
	LWR, RWL, LRW, RLW, WLR, WRL
    }
}
