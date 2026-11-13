package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    public PaymentPage priceShouldBe47000() {
        $("[class*='_prices'] [data-at='H3']").shouldBe(visible, java.time.Duration.ofSeconds(15)).shouldHave(Condition.partialText("47 000")); // Страница оплаты
        return this;
    }
}
