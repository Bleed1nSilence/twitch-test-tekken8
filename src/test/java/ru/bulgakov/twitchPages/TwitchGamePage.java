package ru.bulgakov.twitchPages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TwitchGamePage {
    public TwitchGamePage gameNameShouldBe(String expectedGameName) {
        $(".hlnHjk h1").shouldHave(text(expectedGameName));
        return this;
    }
}
