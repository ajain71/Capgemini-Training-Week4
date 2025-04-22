import java.util.*;
public class MapInverter{
    public static <K, V> Map<V, List<K>> invertMap(Map<K, V> original){
        Map<V, List<K>> inverted = new HashMap<>();
        for(Map.Entry<K, V> entry:original.entrySet()){
            V value = entry.getValue();
            K key = entry.getKey();
            inverted.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }
        return inverted;
    }
    public static void main(String[] args){
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("A", 1);
        originalMap.put("B", 2);
        originalMap.put("C", 1);
        Map<Integer, List<String>> invertedMap = invertMap(originalMap);
        System.out.println(invertedMap);
    }
}