package ru.netology.deliverycard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void shouldOrderCardDeliveryTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Калуга");
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[placeholder='Город']").sendKeys(Keys.ESCAPE);
        $("[name='name']").setValue("Петров-Сидоров Иван");
        $("[name='phone']").setValue("+79999999999");
        $("[class='checkbox__text']").click();
        $("[class='button__text']").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + inputDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
