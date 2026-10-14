package ru.bulgakov.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.bulgakov.pages.*;
import ru.bulgakov.twitchPages.TwitchMainPage;

import static com.codeborne.selenide.Selenide.*;

public class QaTest {

    @Test
    @DisplayName("Проверить, что цена обучения - 47 000 рублей")
    @Tag("POSITIVE")
    void mentoringPriceShouldBe47000Test() {
        open("https://ya.ru",YandexSearchPage.class)
                .search("bulgakov qa")
                .submit()
                //.closeDefaultBrowserSelectWindow()
                .openLink("ivanbulgakovqa.ru")
                .clickPrice()
                .clickConfirmButton()
                .clickConfirmPayment()
                .priceShouldBe47000();
        }


    @Test
    @DisplayName("Проверить, что на странице игры указано верное название")
    @Tag("POSITIVE")
    void twitchSearchShouldFindTekken8Test(){

        // Тест-кейс - проверить, что на сайте Твич, на странице игры Tekken 8 верно указано название самой игры.

                // 1. Перейти на twitch.tv
                open("https://www.twitch.tv/", TwitchMainPage.class)
                // 2. В поисковой строке ввести "Tekken 8"
                .search("Tekken 8")
                // 3. Кликнуть кнопку поиска
                .clickSearch()
                // 4. Кликнуть на название категории игры
                .clickGameArt()
                // 5. Проверить, что в названии игры указано "Tekken 8"
                .gameNameShouldBeTekken8();
    }
}