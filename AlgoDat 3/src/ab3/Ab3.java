package ab3;

public interface Ab3 {
    /**
     * Dekomprimiert die übergeben Daten. Das erste Byte der Daten gibt die Bitlänge
     * der Codewörter an (zB 12 Bit). Die Codewort belegt im Array genau die
     * angegebenen Anzahl an Bits (zB 12 Bit pro Block, es werden auch führende 0en
     * gespeichert). Beachten Sie, dass wenn die Länge der Daten in Bit nicht durch
     * die Länge der Codewörter geteilt werden kann, dass am Ende der Daten ein
     * 0-Padding vorhanden ist, sodass das letzte Byte voll ist. Dieses Padding ist
     * bei der Dekompression nicht relevant.
     * 
     * @param compressedData
     *                           die LZW-komprimierten Daten (das erste Byte
     *                           entspricht der Bitlänge der Codewörter)
     * @return die dekomprimierten Daten
     */
    byte[] uncompress(byte[] compressedData);

    /**
     * Liefert einen kürzesten Pfad vom Knoten from zum Knoten to. Existiert kein
     * Pfad, so liefert die Methode null. Beachten Sie, dass die Kosten der Kanten
     * auch negativ sein können. Der Wert null bedeutet, dass es keine Verbindung
     * gibt.
     * 
     * @param costMatrix
     *                       Kostenmatrix des Graphen (null bedeutet keine
     *                       Verbindung)
     * @param from
     *                       Startknoten (Index von 0 bis n-1)
     * @param to
     *                       Endknoten (Index von 0 bis n-1)
     * @return den Pfad [from, ..., to] oder null, wenn kein kürzester Weg vorhanden
     *         ist.
     */
    int[] shortestPath(Integer[][] costMatrix, int from, int to);

    /**
     * Erzeugt aus den übergebenen Schlüsseln (keine Duplikate vorhanden) einen
     * AVL-Baum.
     * 
     * @param keys
     *                 einzufügende Schlüssel
     * @return die WRL-Ordnung des erzeugten AVL-Baums
     */
    int[] buildTree(int[] keys);
}