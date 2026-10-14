package ru.bulgakov.twitchPages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TwitchGamePage {
    public TwitchGamePage gameNameShouldBeTekken8(){
        $(".hlnHjk h1").shouldHave(text("Tekken 8"));

        return this;
    }

}
