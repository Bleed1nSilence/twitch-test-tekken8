package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsProductPage {

    private final SelenideElement productName = $("[itemprop=name]");
    private final SelenideElement productPrice = $("[itemprop=price]");
    private final SelenideElement qtyInput = $("input.qty-input");
    private final SelenideElement addToCartBtn = $("input.add-to-cart-button");
    private final SelenideElement successNotification = $("div.bar-notification.success");
    private final SelenideElement cartQtySpan = $("span.cart-qty");
    private final SelenideElement cartLink = $("a.ico-cart");
    private final ElementsCollection processorRadios = $$("dl dd ul li input[type='radio']");

    public double getProcessorSurcharge(int index) {
        return switch (index) {
            case 1 -> 15.00;  // Medium
            case 2 -> 100.00; // Fast
            default -> 0.00;  // Slow (0)
        };
    }

    public WsProductPage verifyPrice(double expectedPrice) {
        String expectedPriceString = String.format(java.util.Locale.US, "%.2f", expectedPrice);
        productPrice.shouldHave(com.codeborne.selenide.Condition.text(expectedPriceString));
        return this;
    }

    public double getProductPriceAsNumber() {
        String priceText = productPrice.getText();
        String cleanPrice = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleanPrice);
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public WsProductPage selectProcessor(int index) {
        ElementsCollection radios = $$("input[type='radio']");
        radios.get(index).click();
        Selenide.sleep(500);
        return this;
    }

    public WsProductPage setQuantity(String quantity) {
        qtyInput.setValue(quantity);
        return this;
    }

    public WsProductPage clickAddToCart() {
        addToCartBtn.click();
        return this;
    }

    public WsProductPage verifySuccessNotification() {
        successNotification.shouldBe(visible);
        return this;
    }

    public WsProductPage verifyCartQuantity(String expectedQty) {
        cartQtySpan.shouldHave(text("(" + expectedQty + ")"));
        return this;
    }

    public WsCartPage goToCart() {
        cartLink.click();
        return new WsCartPage();
    }


}
