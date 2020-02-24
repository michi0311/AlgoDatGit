package ab2;

public interface AuDQueue {

	// fügt ein Element in die Queue ein
    public void enqueue(int value);

	// entfernt ein Element aus der Queue
    public int dequeue();

	// Typ der Queue (relevant für den Aufruf von "dequeue")
    enum Type {
		FIFO,
		LIFO;
    }
}
