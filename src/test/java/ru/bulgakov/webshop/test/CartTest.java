package ru.bulgakov.webshop.test;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.pages.*;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION_URL;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class CartTest {
    private static final Faker faker = new Faker();
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        WsWelcomePage welcomePage = open(WEB_SHOP_URL, WsWelcomePage.class);
        WsCatalogPage catalogPage = welcomePage
            .hoverComputersMenu()
            .pressDesktopsButton();
        WsProductPage productPage = catalogPage
            .clickFirstProduct();

        String itemName = productPage.getProductName();
        double basePrice = productPage.getProductPriceAsNumber();
        String itemQuantity = "4";

        int processorIndex = 2;
        productPage.selectProcessor(processorIndex);

        double surcharge = productPage.getProcessorSurcharge(processorIndex);
        double expectedNewPrice = basePrice + surcharge;

        WsCartPage cartPage = productPage
                .setQuantity(itemQuantity)
                .clickAddToCart()
                .verifySuccessNotification()
                .verifyCartQuantity(itemQuantity)
                .goToCart();

        cartPage.verifyProductName(itemName)
                .verifySubtotalAsNumber(expectedNewPrice, itemQuantity);

        String itemQuantityInCart = cartPage.getQuantityValue();
        assertEquals(itemQuantity, itemQuantityInCart);
    }
}
