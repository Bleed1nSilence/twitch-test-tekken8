package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {
    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");
    private final SelenideElement checkLogged = $$("div.header-links ul li a").get(0);
    private final SelenideElement ComputersMenu = $$("ul.top-menu li a").get(1);
    private final SelenideElement DesktopsButton = $(byText("Desktops"));

    @Step("Открыть страницу регистрации")
    public WsRegistrationPage openRegistration() {
        registerButton.click();
        return new WsRegistrationPage();
    }

    @Step("Открыть страницу авторизации")
    public WsLoginPage openLogin() {
        loginButton.click();
        return new WsLoginPage();
    }

    @Step("Проверить, что пользователь авторизован под почтой: {email}")
    public WsWelcomePage checkUserLoggedIn(String email) {
        checkLogged.shouldHave(text(email));
        return this;
    }

    @Step("Навести курсор на меню 'Computers'")
    public WsWelcomePage hoverComputersMenu() {
        ComputersMenu.hover();
        return this;
    }

    @Step("Нажать на кнопку 'Desktops' в выпадающем меню")
    public WsCatalogPage pressDesktopsButton() {
        DesktopsButton.click();
        return new WsCatalogPage();
    }

}
