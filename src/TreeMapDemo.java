import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1, 1);
        treeMap.put(2, 2);
        treeMap.put(3, 3);
        treeMap.put(4, 4);
        treeMap.put(5, 5);
        treeMap.keySet();
        System.out.println(treeMap);
    }
}
