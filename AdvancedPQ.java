// Advanced Priority Queue supporting min/max toggle
public class AdvancedPQ<K extends Comparable<K>, V> {
    private APQEntry<K, V>[] heap;
    private int size;
    private boolean isMinHeap;
    private static final int INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public AdvancedPQ() {
        heap = (APQEntry<K, V>[]) new APQEntry[INITIAL_CAPACITY];
        size = 0;
        isMinHeap = true;
    }

    // flip heap mode
    public void toggle() { 
        isMinHeap = !isMinHeap; 
        heapify(); 
    }

    public APQEntry<K, V> insert(K key, V value) {
        if (size == heap.length) resize();
        APQEntry<K, V> entry = new APQEntry<>(key, value);
        heap[size] = entry;
        entry.setIndex(size);
        upHeap(size++);
        return entry;
    }

    public APQEntry<K, V> removeTop() {
        if (isEmpty()) throw new IllegalStateException("Empty queue");
        APQEntry<K, V> top = heap[0];
        heap[0] = heap[--size];
        heap[0].setIndex(0);
        heap[size] = null;
        if (size > 0) downHeap(0);
        return top;
    }

    public APQEntry<K, V> top() {
        if (isEmpty()) throw new IllegalStateException("Empty queue");
        return heap[0];
    }

    public APQEntry<K, V> remove(APQEntry<K, V> entry) {
        int idx = entry.getIndex();
        if (idx < 0 || idx >= size || heap[idx] != entry) throw new IllegalArgumentException("Invalid entry");
        heap[idx] = heap[--size];
        heap[idx].setIndex(idx);
        heap[size] = null;
        if (size > 0) {
            int parent = parent(idx);
            if (parent >= 0 && compare(heap[idx], heap[parent]) < 0) upHeap(idx);
            else downHeap(idx);
        }
        entry.setIndex(-1);
        return entry;
    }

    public K replaceKey(APQEntry<K, V> entry, K newKey) {
        int idx = entry.getIndex();
        if (idx < 0 || idx >= size || heap[idx] != entry) throw new IllegalArgumentException("Invalid entry");
        K oldKey = entry.getKey();
        entry.setKey(newKey);
        int parent = parent(idx);
        if (parent >= 0 && compare(heap[idx], heap[parent]) < 0) upHeap(idx);
        else downHeap(idx);
        return oldKey;
    }

    public V replaceValue(APQEntry<K, V> entry, V newValue) {
        int idx = entry.getIndex();
        if (idx < 0 || idx >= size || heap[idx] != entry) throw new IllegalArgumentException("Invalid entry");
        V oldValue = entry.getValue();
        entry.setValue(newValue);
        return oldValue;
    }

    public String state() { return isMinHeap ? "Min" : "Max"; }
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public APQEntry<K, V> peekAt(int n) {
        if (n < 1 || n > size) throw new IndexOutOfBoundsException();
        APQEntry<K, V>[] copy = heap.clone();
        for (int i = 0; i < n; i++) {
            int extreme = i;
            for (int j = i + 1; j < size; j++)
                if (compare(copy[j], copy[extreme]) < 0) extreme = j;
            APQEntry<K, V> temp = copy[i]; copy[i] = copy[extreme]; copy[extreme] = temp;
        }
        return copy[n - 1];
    }

    public void merge(AdvancedPQ<K, V> other) {
        if (other == null || other.isEmpty()) return;
        while (size + other.size > heap.length) resize();
        for (int i = 0; i < other.size; i++) {
            APQEntry<K, V> e = other.heap[i];
            APQEntry<K, V> newEntry = new APQEntry<>(e.getKey(), e.getValue());
            heap[size] = newEntry; newEntry.setIndex(size++);
        }
        heapify();
    }
    public void display() {
    System.out.println("APQ State: " + (isMinHeap ? "Min" : "Max") + ", Size: " + size);
    for (int i = 0; i < size; i++) {
        System.out.println("  [" + i + "] " + heap[i]);
    }
}



    // -------------------------- helpers -------------------------------------------------
    private int compare(APQEntry<K, V> e1, APQEntry<K, V> e2) {
        return isMinHeap ? e1.getKey().compareTo(e2.getKey()) : -e1.getKey().compareTo(e2.getKey());
    }
    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private void upHeap(int i) {
        while (i > 0) {
            int p = parent(i);
            if (compare(heap[i], heap[p]) >= 0) break;
            swap(i, p);
            i = p;
        }
    }

    private void downHeap(int i) {
        while (true) {
            int left = leftChild(i), right = rightChild(i), smallest = i;
            if (left < size && compare(heap[left], heap[smallest]) < 0) smallest = left;
            if (right < size && compare(heap[right], heap[smallest]) < 0) smallest = right;
            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        APQEntry<K, V> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        heap[i].setIndex(i); heap[j].setIndex(j);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        APQEntry<K, V>[] newHeap = (APQEntry<K, V>[]) new APQEntry[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) downHeap(i);
    }
}
