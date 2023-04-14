package DataStructures;

import java.util.*;

public class LinkedListDS<K extends Comparable<K>, V> implements DataStructure<K, V> {
    private final LinkedList<Node<K, V>> list;

    public LinkedListDS() {
        list = new LinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node != null ? node.value : null;
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> node = getNode(key);
        if (node != null) {
            node.value = value;
        } else {
            list.add(new Node<>(key, value));
        }
    }

    @Override
    public void remove(K key) {
        Node<K, V> node = getNode(key);
        if (node != null) {
            list.remove(node);
        }
    }
    public void clear() {
        list.clear();
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        list.forEach(node -> values.add(node.value));
        return values;
    }

    private Node<K, V> getNode(Object key) {
        for (Node<K, V> node : list) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    private static class Node<K, V> {
        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void bubbleSort(Comparator<V> comparator) {
        int n = list.size();
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(list.get(i - 1).value, list.get(i).value) > 0) {
                    Node<K, V> temp = list.get(i - 1);
                    list.set(i - 1, list.get(i));
                    list.set(i, temp);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }
}

