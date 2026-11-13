package ru.bulgakov.twitchPages;

import static com.codeborne.selenide.Selenide.$;

public class TwitchSearchResultPage {
    public TwitchGamePage clickGameArt(String expectedGameName) {
        $("[alt='" + expectedGameName + "']").click();

        return new TwitchGamePage();
    }
}
