package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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

    @Step("Получить размер наценки для процессора с индексом {index}")
    public double getProcessorSurcharge(int index) {
        return switch (index) {
            case 1 -> 15.00;  // Medium
            case 2 -> 100.00; // Fast
            default -> 0.00;  // Slow (0)
        };
    }

    @Step("Проверить, что цена товара равна {expectedPrice}")
    public WsProductPage verifyPrice(double expectedPrice) {
        String expectedPriceString = String.format(java.util.Locale.US, "%.2f", expectedPrice);
        productPrice.shouldHave(com.codeborne.selenide.Condition.text(expectedPriceString));
        return this;
    }

    @Step("Получить цену товара как число")
    public double getProductPriceAsNumber() {
        String priceText = productPrice.getText();
        String cleanPrice = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleanPrice);
    }

    @Step("Получить название товара из карточки")
    public String getProductName() {
        return productName.getText();
    }


    @Step("Получить цену товара в виде строки")
    public String getProductPrice() {
        return productPrice.getText();
    }

    @Step("Выбрать процессор с индексом {index}")
    public WsProductPage selectProcessor(int index) {
        ElementsCollection radios = $$("input[type='radio']");
        radios.get(index).click();
        Selenide.sleep(500);
        return this;
    }

    @Step("Установить количество товара: {quantity}")
    public WsProductPage setQuantity(String quantity) {
        qtyInput.setValue(quantity);
        return this;
    }

    @Step("Нажать кнопку 'Добавить в корзину'")
    public WsProductPage clickAddToCart() {
        addToCartBtn.click();
        return this;
    }

    @Step("Проверить, что появилось уведомление об успешном добавлении")
    public WsProductPage verifySuccessNotification() {
        successNotification.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что в иконке корзины отображается количество: {expectedQty}")
    public WsProductPage verifyCartQuantity(String expectedQty) {
        cartQtySpan.shouldHave(text("(" + expectedQty + ")"));
        return this;
    }

    @Step("Перейти в корзину")
    public WsCartPage goToCart() {
        cartLink.click();
        return new WsCartPage();
    }


}
