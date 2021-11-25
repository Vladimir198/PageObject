package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LogInTest {

    @BeforeEach
    void setupAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginValid() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.validVerifyLogin(verificationCode);
    }

    @Test
    void shouldLoginWithTheWrongCode() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getOtherAuthInfo("petya", "123qwerty");
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.invalidVerifyCode(verificationCode);
        $("[data-test-id='error-notification']").shouldBe(Condition.exactTextCaseSensitive("Ошибка\n" +
                "Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }

    @Test
    void shouldInvalidLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getOtherAuthInfo("vova", "Mfg1565");
        loginPage.invalidLogin(authInfo);
        $("[data-test-id='error-notification']").shouldBe(Condition.exactTextCaseSensitive("Ошибка\n" +
                "Ошибка! Неверно указан логин или пароль"));
    }
}
