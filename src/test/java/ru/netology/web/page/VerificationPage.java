package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
  private final SelenideElement codeField = $("[data-test-id=code] input");
  private final SelenideElement verifyButton = $("[data-test-id=action-verify]");

  public VerificationPage() {
    codeField.shouldBe(visible);
  }

  public void validVerifyLogin(DataHelper.VerificationCode verificationCode) {
    codeField.setValue(verificationCode.getCode());
    verifyButton.click();
      new DashboardPage();
  }

  public void validVerify(DataHelper.VerificationCode verificationCode) {
    codeField.setValue(verificationCode.getCode());
    verifyButton.click();
    //для аннотации @BeforeAll
  }

  public void invalidVerifyCode(DataHelper.VerificationCode verificationCode) {
    codeField.setValue(verificationCode.getInvalidCode());
    verifyButton.click();
  }
}
