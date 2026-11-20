package ru.bulgakov.twitchPages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class TwitchMainPage {

    @Step("Ввести в поисковую строку название игры: {gameName}")
    public TwitchMainPage search(String gameName) {
    $("[type=search]").setValue(gameName);
    return this;
}

    @Step("Нажать на кнопку поиска")
    public TwitchSearchResultPage clickSearch() {
    $("[aria-label='Кнопка поиска']").click();
    return new TwitchSearchResultPage();
}
}
