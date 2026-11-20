package ru.bulgakov.twitchPages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TwitchGamePage {

    @Step("Проверить, что заголовок страницы содержит название: {expectedGameName}")
    public TwitchGamePage gameNameShouldBe(String expectedGameName) {
        $(".hlnHjk h1").shouldHave(text(expectedGameName));
        return this;
    }
}
