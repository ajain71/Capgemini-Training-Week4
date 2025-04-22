import java.util.*;

public class ShoppingCart {
    private HashMap<String, Double> priceMap = new HashMap<>();
    private LinkedHashMap<String, Integer> cartOrder = new LinkedHashMap<>();

    public void addProduct(String product, double price, int quantity) {
        priceMap.put(product, price);
        cartOrder.put(product, cartOrder.getOrDefault(product, 0) + quantity);
    }

    public void displayCartInInsertionOrder() {
        System.out.println("Cart Items (Insertion Order):");
        for (Map.Entry<String, Integer> entry : cartOrder.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            double price = priceMap.get(product);
            System.out.println(product + " x" + quantity + " - $" + price);
        }
    }

    public void displayCartSortedByPrice() {
        TreeMap<Double, List<String>> sortedByPrice = new TreeMap<>();

        for (String product : cartOrder.keySet()) {
            double price = priceMap.get(product);
            sortedByPrice.putIfAbsent(price, new ArrayList<>());
            sortedByPrice.get(price).add(product);
        }

        System.out.println("Cart Items (Sorted by Price):");
        for (Map.Entry<Double, List<String>> entry : sortedByPrice.entrySet()) {
            double price = entry.getKey();
            for (String product : entry.getValue()) {
                int quantity = cartOrder.get(product);
                System.out.println(product + " x" + quantity + " - $" + price);
            }
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Apple", 1.2, 3);
        cart.addProduct("Banana", 0.8, 2);
        cart.addProduct("Orange", 1.5, 4);
        cart.addProduct("Banana", 0.8, 1); 

        cart.displayCartInInsertionOrder();
        System.out.println();
        cart.displayCartSortedByPrice();
    }
}