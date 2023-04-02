package DataStructures;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

public class ArrayListDS<K, V> implements DataStructure<K, V> {
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

