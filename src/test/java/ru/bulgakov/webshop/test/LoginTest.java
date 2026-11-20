package ru.bulgakov.webshop.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsLoginPage;
import ru.bulgakov.webshop.pages.WsRegistrationPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.*;

@Epic("Авторизация")
@Feature("Логин")
public class LoginTest extends TestBase {
    private static final Faker faker = new Faker();
    private String email;
    private String password;

    @Nested
    @DisplayName("Позитивные тесты")
    public class PositiveTests {
        @BeforeEach
        void beforeEach() {
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
        @DisplayName("Успешный вход с валидными данными")
        @Severity(SeverityLevel.CRITICAL)
        @Owner("Bleed1nSIlence")
        @Link("TASK-121")
        @Story("Вход в систему")
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


       @ParameterizedTest(name = "Авторизация с невалидным email: {0}")
       @CsvFileSource(resources = "/email.csv")
       @DisplayName("Проверка валидации невалидного email")
       @Severity(SeverityLevel.NORMAL)
       @Owner("Bleed1nSilence")
       @Link("TASK-122")
       @Story("Валидация полей")
    void invalidEmailLoginTest(String email) {
           open(WEB_SHOP_LOGIN_URL, WsLoginPage.class)
                   .enterEmail(email)
                   .enterPassword("password")
                   .verifyEmailValidationErrorAppear()
                   .submitLogin();
       }
}
