import java.util.ArrayList;
import java.util.List;

interface Category {
    String getCategoryName();
}

class BookCategory implements Category {
    public String getCategoryName() {
        return "Books";
    }
}

class ClothingCategory implements Category {
    public String getCategoryName() {
        return "Clothing";
    }
}

class GadgetCategory implements Category {
    public String getCategoryName() {
        return "Gadgets";
    }
}

class Product<T extends Category> {
    private String name;
    private double price;
    private T category;

    public Product(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public T getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class ProductUtils {
    public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double discountedPrice = product.getPrice() * (1 - percentage / 100);
        product.setPrice(discountedPrice);
    }
}

class Catalog {
    private List<Product<? extends Category>> products = new ArrayList<>();

    public void addProduct(Product<? extends Category> product) {
        products.add(product);
    }

    public void showCatalog() {
        for (Product<? extends Category> product : products) {
            System.out.println(product.getName() + " | " + product.getCategory().getCategoryName() + " | $" + product.getPrice());
        }
    }
}

public class OnlineMarketplace {
    public static void main(String[] args) {
        Product<BookCategory> book = new Product<>("Java Programming", 45.0, new BookCategory());
        Product<ClothingCategory> shirt = new Product<>("Casual Shirt", 30.0, new ClothingCategory());
        Product<GadgetCategory> phone = new Product<>("Smartphone", 500.0, new GadgetCategory());

        ProductUtils.applyDiscount(book, 10);
        ProductUtils.applyDiscount(shirt, 20);
        ProductUtils.applyDiscount(phone, 15);

        Catalog catalog = new Catalog();
        catalog.addProduct(book);
        catalog.addProduct(shirt);
        catalog.addProduct(phone);

        catalog.showCatalog();
    }
}