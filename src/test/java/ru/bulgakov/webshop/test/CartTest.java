package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.*;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION_URL;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

@Epic("Покупки")
@Feature("Корзина")
public class CartTest extends TestBase {
    private static final Faker faker = new Faker();
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    @DisplayName("Добавление товара в корзину с изменением количества и процессора")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Bleed1nSilence")
    @Link("TASK-123")
    @Story("Добавление товара")
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
