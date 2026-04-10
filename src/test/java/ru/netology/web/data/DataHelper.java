package ru.netology.web.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
// Данный класс как пример генерации тестовых данных
// Вместа передачи данных через сценарий (feature)
// можно вызывать подобные методы непосредственно в шагах сценария (steps)
public class DataHelper {

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class CardsInfo {
        private Card FirstCard;
        private Card SecondCard;
    }

    public static CardsInfo getCardsInfo() { return new CardsInfo(
            new Card("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0"),
            new Card("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d"));
    }

    @Value
    public static class Card {
        private String cardNumber;
        private String testId;
    }
}