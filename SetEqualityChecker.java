import java.util.HashSet;
import java.util.Set;

public class SetEqualityChecker {
    public static <T> boolean areSetsEqual(Set<T> set1, Set<T> set2) {
        return set1.equals(set2);
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(3);
        set2.add(2);
        set2.add(1);

        boolean result = areSetsEqual(set1, set2);
        System.out.println("Are the sets equal? " + result);
    }
}