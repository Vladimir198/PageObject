package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
  private DataHelper() {}

  @Value
  public static class AuthInfo {
    private String login;
    private String password;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
  }

  public static AuthInfo getOtherAuthInfo(String login, String password) {
    return new AuthInfo(login, password);
  }

  @Value
  public static class VerificationCode {
    private String code = "12345";
    private String invalidCode = "1234";
  }

  public static VerificationCode getVerificationCodeFor() {
    return new VerificationCode();
  }

  @Value
  public static class CardRefill {
    private String numberFirstCard = "5559 0000 0000 0001";
    private String numberSecondCard = "5559 0000 0000 0002";

    public static CardRefill getCard() {
      return new CardRefill();
    }
  }
}
