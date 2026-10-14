package ru.bulgakov.twitchPages;

import static com.codeborne.selenide.Selenide.$;

public class TwitchSearchResultPage {
    public TwitchGamePage clickGameArt() {
        $("[alt='TEKKEN 8']").click();

        return new TwitchGamePage();
    }
}
