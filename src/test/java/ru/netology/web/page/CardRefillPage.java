package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardRefillPage {
    private final SelenideElement sumButton = $("[data-test-id='amount'] .input__control");
    private final SelenideElement number = $("[data-test-id='from'] .input__control");
    private final SelenideElement button = $("[data-test-id='action-transfer']");

    public CardRefillPage() {
        SelenideElement heading = $(byText("Пополнение карты"));
        heading.shouldBe(visible);
    }

    public DashboardPage inputTheTransfer(String num, String summa) {
        sumButton.setValue(summa);
        number.setValue(num);
        button.click();
        return new DashboardPage();
    }

    public void clear() {
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        sumButton.setValue(deleteString);
        number.setValue(deleteString);
    }
}
