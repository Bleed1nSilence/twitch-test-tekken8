package ru.bulgakov.webshop.steps;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import ru.bulgakov.webshop.pages.WsRegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION_URL;

public class AuthSteps {
    private static final Faker faker = new Faker();


    @Step("Зарегистрировать нового пользователя")
    public void registerNewUser() {
        open (WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                .register(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.harryPotter().character() + faker.number().positive());
    }
}
