package ab2;

public interface AuDHashSet {

	// fügt ein Element in das HashSet ein
	//   value: der einzufügende Wert
    public void add(long value);

	// gibt "true" zurück, falls Ihr HashSet den Wert enthält; sonst "false"
	//   value: der zu suchende Wert
    public boolean contains(long value);
}
