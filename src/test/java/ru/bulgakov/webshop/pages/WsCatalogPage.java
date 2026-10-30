package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class WsCatalogPage {
    private final SelenideElement firstProductCard = $$("div.product-grid div").get(0);

    public WsProductPage clickFirstProduct() {
        firstProductCard.click();
        return new WsProductPage();
    }
}
