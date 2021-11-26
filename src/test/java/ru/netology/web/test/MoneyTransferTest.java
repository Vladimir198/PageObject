package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
//java -jar ./artifacts/app-ibank-build-for-testers.jar -P:profile=test
  @BeforeEach
  void setupAll() {
    open("http://localhost:9999");
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor();
    verificationPage.validVerify(verificationCode);
  }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
      var dashBoardPage1 = new DashboardPage();
      var numberCard = DataHelper.CardRefill.getCard();
      var cardInfoFirst = numberCard.getNumberFirstCard();
      var cardInfoSecond = numberCard.getNumberSecondCard();

      var randomSum = "5000";
      var randomSumDouble = Double.parseDouble(randomSum);
      var sumFirst = dashBoardPage1.getCardBalance(dashBoardPage1.getId1());
      var sumSecond = dashBoardPage1.getCardBalance(dashBoardPage1.getId2());
      var expectedFirst = sumFirst + randomSumDouble;
      var expectedSecond = sumSecond - randomSumDouble;

      var cardRefillPage1 = dashBoardPage1.transferToFirstCard();
      cardRefillPage1.clear();
      var dashBoardPage2 = cardRefillPage1.inputTheTransfer(cardInfoSecond, randomSum);
      var balanceFirst = dashBoardPage2.getCardBalance(dashBoardPage1.getId1());
      var balanceSecond = dashBoardPage2.getCardBalance(dashBoardPage1.getId2());

      //вернуть баланс карт к исходному
      var cardRefillPage2 = dashBoardPage2.transferToSecondCard();
      cardRefillPage2.clear();
      cardRefillPage2.inputTheTransfer(cardInfoFirst, randomSum);

      Assertions.assertEquals(expectedFirst, balanceFirst);
      Assertions.assertEquals(expectedSecond, balanceSecond);
      Assertions.assertTrue(balanceSecond >= 0);
    }
}
