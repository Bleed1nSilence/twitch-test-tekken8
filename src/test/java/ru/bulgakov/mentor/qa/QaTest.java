package ru.bulgakov.mentor.qa;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.bulgakov.mentor.pages.*;
import ru.bulgakov.twitchPages.TwitchMainPage;
import ru.bulgakov.webshop.TestBase;

import static com.codeborne.selenide.Selenide.*;

@Epic("Домашнее задание")
@Feature("Проверки веб-сайтов")
public class QaTest extends TestBase {

    @Test
    @DisplayName("Проверить, что цена обучения - 47 000 рублей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Bleed1nSilence")
    @Link("TASK-1")
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



    @ParameterizedTest(name = "Игра: {0}")
    @ValueSource(strings = {"TEKKEN 8", "Street Fighter 6", "Mortal Kombat 1"})
    @DisplayName("Проверить, что на странице игры указано верное название")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Bleed1nSilence")
    @Link("TASK-2")
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

    @ParameterizedTest(name = "Игра: {0}")
    @ValueSource(strings = {"TEKKEN 9", "Street Fighter 8", "Mortal Kombat Y"})
    @DisplayName("Проверить, что при поиске несуществующей игры ничего не найдено")
    @Severity(SeverityLevel.MINOR)
    @Owner("Bleed1nSilence")
    @Link("TASK-3")
    @Tag("NEGATIVE")
    @Tag("small")
    void twitchSearchShouldFindIncorrectGameTest(String expectedGameName){

        // Тест-кейс - проверить, что на сайте Твич, на странице игры неверно указано название самой игры.

        open("https://www.twitch.tv/", TwitchMainPage.class)
                .search(expectedGameName)
                .clickSearch()
                .checkGameArtIsAbsent(expectedGameName);
    }
}

