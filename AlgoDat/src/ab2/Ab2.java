package ab2;

public interface Ab2 {

	// liefert eine leere Instanz Ihrer Implementierung der AuDHashSet-Klasse
	//   capacity: die maximal benötigte Kapazität
    public AuDHashSet emptyHashSet(int capacity);

	// liefert eine leere Instanz Ihrer Implementierung der AuDSortedTree-Klasse
    public AuDSortedTree emptyTree();

	// liefert eine leere Instanz Ihrer Implementierung der AuDQueue-Klasse
	//   type: LIFO oder FIFO - liefern Sie eine entsprechende AuDQueue-Instanz
    public AuDQueue emptyQueue(AuDQueue.Type type);

}
