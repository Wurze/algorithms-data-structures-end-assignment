package DataStructures;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayListDS<K extends Comparable<K>, V> implements DataStructure<K, V> {
    private final ArrayList<Entry<K, V>> table;

    public ArrayListDS() {
        table = new ArrayList<>();
    }

    public void put(K key, V value) {
        table.add(new Entry<>(key, value));
    }

    public V get(K key) {
        for (Entry<K, V> entry : table) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        Iterator<Entry<K, V>> iter = table.iterator();
        while (iter.hasNext()) {
            Entry<K, V> entry = iter.next();
            if (entry.getKey().equals(key)) {
                iter.remove();
                break;
            }
        }
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            values.add(entry.getValue());
        }
        return values;
    }
    public void quickSort(Comparator<V> comparator) {
        quickSort(0, table.size() - 1, comparator);
    }

    private void quickSort(int left, int right, Comparator<V> comparator) {
        if (left < right) {
            int partitionIndex = partition(left, right, comparator);
            quickSort(left, partitionIndex - 1, comparator);
            quickSort(partitionIndex + 1, right, comparator);
        }
    }

    private int partition(int left, int right, Comparator<V> comparator) {
        Entry<K, V> pivot = table.get(right);
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (comparator.compare(table.get(j).getValue(), pivot.getValue()) < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, right);
        return i + 1;
    }

    private void swap(int i, int j) {
        Entry<K, V> temp = table.get(i);
        table.set(i, table.get(j));
        table.set(j, temp);
    }

    private static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}

