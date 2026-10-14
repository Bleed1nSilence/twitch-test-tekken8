package ru.bulgakov.twitchPages;

import static com.codeborne.selenide.Selenide.$;

public class TwitchMainPage {

public TwitchMainPage search(String gamename) {
    $("[type=search]").setValue(gamename);

    return this;
}

public TwitchSearchResultPage clickSearch() {
    $("[aria-label='Кнопка поиска']").click();

    return new TwitchSearchResultPage();
}
}
