import java.util.ArrayList;
import java.util.List;

public class SecondShoppingCart {

    public static void main(String[] args) {

        List<Double> prices = new ArrayList<>();

        prices.add(199.99);
        prices.add(450.50);
        prices.add(199.99);
        prices.add(199.99);
        prices.add(199.99);

        double budget = 5000;

        //Задача 1: Посчитать общую сумму товаров
        double total = 0;

        for (double price : prices) {
            total += price;
        }
        System.out.println("Общая сумма корзины: " + total);
        System.out.println("--------------------------------");

        // Задача 2: Проверить, хватает ли бюджета
        System.out.println("Бюджет: " + budget);

        if (budget >= total) {
            System.out.println("Бюджета хватает! Остаток: " + (budget - total));
        } else {
                System.out.println("Бюджета не хватает! Недостаток: " + (total - budget));
            }
        }
    }
