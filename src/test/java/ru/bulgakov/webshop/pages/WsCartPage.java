package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WsCartPage {
    private final SelenideElement productNameElement = $("a.product-name");
    private final SelenideElement qtyInput = $("input.qty-input");
    private final SelenideElement subtotalElement = $("span.product-subtotal");

    public WsCartPage verifyProductName(String expectedName) {
        productNameElement.shouldHave(text(expectedName));
        return this;
    }

    public String getQuantityValue() {
        return qtyInput.getAttribute("value");
    }

    public WsCartPage verifySubtotalAsNumber(double itemPrice, String itemQuantity) {
        double expectedSubtotal = itemPrice * Double.parseDouble(itemQuantity);
        subtotalElement.shouldHave(text(String.valueOf(expectedSubtotal)));
        return this;
    }
}
