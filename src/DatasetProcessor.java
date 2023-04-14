import DataStructures.ArrayListDS;
import DataStructures.DataStructure;
import DataStructures.LinkedListDS;
import DataStructures.TreeMapDS;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DatasetProcessor<K extends Comparable<K>, V> {
    private final LinkedListDS<K, V> linkedListTable;
    private final ArrayListDS<K, V> arrayListTable;
    private final TreeMapDS<K, V> treeMapTable;

    public DatasetProcessor(LinkedListDS<K, V> linkedListTable, ArrayListDS<K, V> arrayListTable, TreeMapDS<K, V> treeMapTable) {
        this.linkedListTable = linkedListTable;
        this.arrayListTable = arrayListTable;
        this.treeMapTable = treeMapTable;
    }
    // Algorithm 1: Sort items using a comparator
    public List<V> sortItems(Comparator<V> comparator, int dataStructureIndex) {
        DataStructure<K, V> dataStructure = getSelectedDataStructure(dataStructureIndex);
        Iterable<V> items = getSelectedDataStructure(dataStructureIndex).values();
        if (dataStructure instanceof LinkedListDS) {
            linkedListTable.bubbleSort(comparator);
        } else if (dataStructure instanceof ArrayListDS) {
            arrayListTable.quickSort(comparator);
        }  // TreeMap is already sorted by its natural ordering

        return StreamSupport.stream(items.spliterator(), false)
                .collect(Collectors.toList());
    }

    // Algorithm 2: Search item by criteria (predicate)
    public Optional<V> searchItemByCriteria(Predicate<V> criteria, int dataStructureIndex) {
        Iterable<V> items = getSelectedDataStructure(dataStructureIndex).values();
        return StreamSupport.stream(items.spliterator(), false)
                .filter(criteria)
                .findFirst();
    }

    // Algorithm 3: Sort items using natural order
    public List<V> sortItemsByNaturalOrder(int dataStructureIndex) {
        Iterable<V> items = getSelectedDataStructure(dataStructureIndex).values();
        return StreamSupport.stream(items.spliterator(), false)
                .sorted()
                .collect(Collectors.toList());
    }

    // Algorithm 4: Search items by a specific field value (using a function)
    public List<V> searchItemsByFieldValue(Function<V, String> fieldExtractor, String value, int dataStructureIndex) {
        Iterable<V> items = getSelectedDataStructure(dataStructureIndex).values();
        return StreamSupport.stream(items.spliterator(), false)
                .filter(item -> fieldExtractor.apply(item).equals(value))
                .collect(Collectors.toList());
    }

    private DataStructure<K, V> getSelectedDataStructure(int dataStructureIndex) {
        return switch (dataStructureIndex) {
            case 0 -> linkedListTable;
            case 1 -> arrayListTable;
            case 2 -> treeMapTable;
            default -> throw new IllegalArgumentException("Invalid data structure index");
        };
    }
}
