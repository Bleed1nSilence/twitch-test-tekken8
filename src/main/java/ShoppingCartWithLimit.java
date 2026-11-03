import java.util.ArrayList;
import java.util.List;

public class ShoppingCartWithLimit {

    public static void main(String[] args) {

        List<Double> prices = new ArrayList<>();

        prices.add(199.99);
        prices.add(450.50);
        prices.add(199.99);
        prices.add(199.99);
        prices.add(199.99);

        double budget = 5000;

        double total = 0;
        int i = 0;

        while (i < prices.size() && total + prices.get(i) <= budget) {
            total += prices.get(i);
            i++;
        }

        System.out.printf("Куплено: %d товара на сумму %.2f%n", i, total);
        System.out.printf("Остаток бюджета: %.2f%n", budget - total);
        System.out.println("Не куплено: " + (prices.size() - i) + " товара");
    }
}
