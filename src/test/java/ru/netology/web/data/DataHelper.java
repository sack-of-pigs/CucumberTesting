package ru.netology.web.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataHelper {

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static Card getFirstCardInfo() { return new Card("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0"); }
    public static Card getSecondCardInfo() { return new Card("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d"); }

    @Value
    public static class Card {
        private String cardNumber;
        private String testId;
    }

    public static Card pickACard(int cardOrder){
        if (cardOrder == 1){
            return getFirstCardInfo();
        }
        return getSecondCardInfo();
    }
}