package ru.bulgakov.webshop.test;

import com.codeborne.selenide.Configuration;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.pages.WsRegistrationPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION_URL;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class LoginTest {
    private static final Faker faker = new Faker();
    private String email;
    private String password;

    @BeforeEach
    void beforeAll() {
        password = faker.harryPotter().character() + faker.number().positive();
        email = faker.internet().emailAddress();

        open (WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                .register(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        email,
                        password)
                        .checkUserLoggedIn(email);

        clearBrowserCookies();
        clearBrowserLocalStorage();
    }



    @Test
    void succesLoginTest() {
        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openLogin()
                .checkLoginPageOpened()
                .enterEmail(email)
                .enterPassword(password)
                .checkRememberMe()
                .submitLogin()
                .checkUserLoggedIn(email);

       }
}
