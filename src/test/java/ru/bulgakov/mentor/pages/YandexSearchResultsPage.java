package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class YandexSearchResultsPage {
    private final SelenideElement closeWindow = $("button.DistributionButton");


    @Step("Закрыть всплывающее окно выбора браузера по умолчанию")
    public YandexSearchResultsPage closeDefaultBrowserSelectWindow() {
        closeWindow.click();
        return this;
    }

    @Step("Кликнуть на ссылку сайта: {webSiteName} и переключиться на новую вкладку")
    public WelcomePage openLink(String webSiteName) {
        $(byText(webSiteName)).click();
        switchTo().window(1);

        return new WelcomePage();
    }
}
