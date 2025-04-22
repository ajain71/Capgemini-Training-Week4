import java.util.*;

public class ListRotator {

    public static <T> List<T> rotateList(List<T> list, int positions) {
        int size = list.size();
        positions = positions % size;
        List<T> rotated = new ArrayList<>();

        for (int i = positions; i < size; i++) {
            rotated.add(list.get(i));
        }

        for (int i = 0; i < positions; i++) {
            rotated.add(list.get(i));
        }

        return rotated;
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(10, 20, 30, 40, 50);
        int rotateBy = 2;

        List<Integer> output = rotateList(input, rotateBy);

        System.out.println("Original List: " + input);
        System.out.println("Rotated List: " + output);
    }
}