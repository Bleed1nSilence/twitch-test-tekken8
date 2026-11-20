package ru.bulgakov.mentor.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage {

    @Step("Кликнуть на кнопку 'Цена' в верхнем меню сайта")
    public WelcomePage clickPrice()   {
        $$(".t-menu__list li").last().click();
        return this;
    }

    @Step("Нажать кнопку подтверждения")
    public WelcomePage clickConfirmButton() {
        $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
        return this;
    }

    @Step("Нажать кнопку 'Бегу оплачивать'")
    public PaymentPage clickConfirmPayment() {
        $(byText("Бегу оплачивать")).click();
        switchTo().window(2);
        return new PaymentPage();
    }
}
