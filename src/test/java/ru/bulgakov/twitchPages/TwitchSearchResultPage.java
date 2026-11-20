package ru.bulgakov.twitchPages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TwitchSearchResultPage {

    @Step("Кликнуть на картинку игры с названием: {expectedGameName}")
    public TwitchGamePage clickGameArt(String expectedGameName) {
        $("[alt='" + expectedGameName + "']").click();
        return new TwitchGamePage();
    }

    @Step("Проверить, что картинка игры '{expectedGameName}' отсутствует на странице")
    public TwitchSearchResultPage checkGameArtIsAbsent(String expectedGameName) {
        // Ищем элемент. Если его нет — тест зеленый. Если есть — тест упадет (что правильно для негативного кейса).
        $("[alt='" + expectedGameName + "']").shouldNotBe(visible);
        return this;
    }
}
