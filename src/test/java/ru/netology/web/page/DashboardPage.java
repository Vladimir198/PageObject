package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
  private final String id1 = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
  private final String id2 = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
  private final SelenideElement TopUpFirstCardButton = $("[data-test-id='"+ id1 +"'] button");
  private final SelenideElement TopUpSecondCardButton = $("[data-test-id='"+ id2 +"'] button");

  public DashboardPage() {
    SelenideElement heading = $("[data-test-id=dashboard]");
    heading.shouldBe(visible);
  }

  public double getCardBalance(String id) {
    val text = $("[data-test-id='"+ id +"']").text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    var tex = text.split(":");
    val value = tex[1].substring(0, tex[1].indexOf("р.")).trim();
    return Integer.parseInt(value);
  }

  public CardRefillPage transferToFirstCard() {
    TopUpFirstCardButton.click();
    return new CardRefillPage();
  }

  public CardRefillPage transferToSecondCard() {
    TopUpSecondCardButton.click();
    return new CardRefillPage();
  }

  public String getId1() {
    return id1;
  }

  public String getId2() {
    return id2;
  }
}
