package ru.bulgakov.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage {

    public WelcomePage clickPrice()   {
        $$(".t-menu__list li").last().click();
        return this;
    }

    public WelcomePage clickConfirmButton() {
        $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
        return this;
    }

    public PaymentPage clickConfirmPayment() {
        $(byText("Бегу оплачивать")).click();
        switchTo().window(2);
        return new PaymentPage();
    }
}
