package ru.bulgakov.mentor.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.bulgakov.mentor.pages.*;
import ru.bulgakov.twitchPages.TwitchMainPage;
import ru.bulgakov.webshop.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class QaTest extends TestBase {

    @Test
    @DisplayName("Проверить, что цена обучения - 47 000 рублей")
    @Tag("POSITIVE")
    @Tag("big")
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



    @ParameterizedTest
    @ValueSource(strings = {
            "TEKKEN 8",
            "Street Fighter 6",
            "Mortal Kombat 1"
    })
    @DisplayName("Проверить, что на странице игры указано верное название")
    @Tag("POSITIVE")
    @Tag("small")
    void twitchSearchShouldFindGameTest(String expectedGameName){

        // Тест-кейс - проверить, что на сайте Твич, на странице игры верно указано название самой игры.

                // 1. Перейти на twitch.tv
                open("https://www.twitch.tv/", TwitchMainPage.class)
                // 2. В поисковой строке ввести название игры
                .search(expectedGameName)
                // 3. Кликнуть кнопку поиска
                .clickSearch()
                // 4. Кликнуть на название категории игры
                .clickGameArt(expectedGameName)
                // 5. Проверить, что название игры указано верно
                .gameNameShouldBe(expectedGameName);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "TEKKEN 9",
            "Street Fighter 8",
            "Mortal Kombat Y"
    })
    @DisplayName("Проверить, что на странице игры указано неверное название")
    @Tag("NEGATIVE")
    @Tag("small")
    void twitchSearchShouldFindIncorrectGameTest(String expectedGameName){

        // Тест-кейс - проверить, что на сайте Твич, на странице игры неверно указано название самой игры.

        open("https://www.twitch.tv/", TwitchMainPage.class)
                .search(expectedGameName)
                .clickSearch()
                .clickGameArt(expectedGameName)
                .gameNameShouldBe(expectedGameName);
    }
}

