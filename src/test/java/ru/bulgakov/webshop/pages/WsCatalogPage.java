package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class WsCatalogPage {
    private final SelenideElement firstProductCard = $$("div.product-grid div").get(0);

    @Step("Кликнуть на первый товар в каталоге")
    public WsProductPage clickFirstProduct() {
        firstProductCard.click();
        return new WsProductPage();
    }
}
