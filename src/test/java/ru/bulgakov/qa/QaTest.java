package ru.bulgakov.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class QaTest {

    @Test
    void mentoringPriceShouldBe47000Test() {
        /*
        * Тест-кейс - проверить, что предоплата обучения - 47000 рублей
        * 1. Открыть поисковик (Яндекс)
        * 2. Ввести данные сайта (bulgakov.qa)
        * 3. Нажать кнопку поиска
        * 4. В поисковой выдаче найти нужный сайт, кликнуть на него
        * 5. Нажать на кнопку "Стоимость"
        * 6. Нажать на кнопку "Вкатиться в QA"
        * 7. Нажать на кнопку "Бегу оплачивать"
        * 8. Проверить, что к оплате 47 000 рублей
        **/

        open("https://ya.ru/");
        $("#text").setValue("bulgakov qa");
        $("[type=submit]").click();
        //$("button.DistributionButton").click(); - кнопка "сделать Яндекс дефолтной страницей"
        $(byText("ivanbulgakovqa.ru")).click();

        sleep(3000);
        switchTo().window(1);
        $$(".t-menu__list li").last().click();
        $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
        $(byText("Бегу оплачивать")).click();

        switchTo().window(2);
        sleep(6000);
        $("[class*='_prices'] [data-at='H3']").shouldHave(Condition.partialText("47 000"));
        }


    @Test
    void twitchSearchShouldFindTekken8Test(){
        /*
    * Тест-кейс - проверить, что на сайте Твич, на странице игры Tekken 8 верно указано название самой игры.
    * (Тест-кейс без поисковика из-за капчи)
    * 1. Перейти на twitch.tv
    * 2. В поисковой строке ввести "Tekken 8"
    * 3. Кликнуть на название категории игры
    * 4. Проверить, что в названии игры указано "Tekken 8"
     **/
        open("https://www.twitch.tv/");
        $("[type=search]").setValue("Tekken 8");
        $("[aria-label='Кнопка поиска']").click();
        $("[alt='TEKKEN 8']").click();
        $(".hlnHjk h1").shouldHave(text("Tekken 8"));
    }
}