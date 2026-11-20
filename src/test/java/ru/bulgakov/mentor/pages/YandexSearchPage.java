package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class YandexSearchPage {
    private final SelenideElement searchInput = $("#text");
    private final SelenideElement submitButton = $("[type=submit]");

    @Step("Ввести поисковый запрос: {query}")
    public YandexSearchPage search(String query) {
        searchInput.setValue(query);

        return this;
    }

    @Step("Нажать кнопку 'Найти'")
    public YandexSearchResultsPage submit() {
        submitButton.click();

        return new YandexSearchResultsPage();
    }
}
