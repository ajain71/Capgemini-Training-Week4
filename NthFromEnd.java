import java.util.LinkedList;
import java.util.ListIterator;

public class NthFromEnd {

    public static <T> T findNthFromEnd(LinkedList<T> list, int n) {
        ListIterator<T> fast = list.listIterator();
        ListIterator<T> slow = list.listIterator();

        for (int i = 0; i < n; i++) {
            if (!fast.hasNext()) {
                return null;
            }
            fast.next();
        }

        while (fast.hasNext()) {
            fast.next();
            slow.next();
        }

        return slow.next();
    }

    public static void main(String[] args) {
        LinkedList<String> input = new LinkedList<>();
        input.add("A");
        input.add("B");
        input.add("C");
        input.add("D");
        input.add("E");

        int n = 2;
        String result = findNthFromEnd(input, n);

        System.out.println("Nth element from the end: " + result);
    }
}