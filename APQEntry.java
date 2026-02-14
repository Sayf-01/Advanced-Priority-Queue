// APQTest.java
// Advanced Priority Queue (APQ) implementation + test suite


// Simple entry storing a key, value and its index in the heap array
class APQEntry<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private int index; // position in heap

    public APQEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.index = -1;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
    public int getIndex() { return index; }

    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    public void setIndex(int i) { this.index = i; }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

