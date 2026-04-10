package ru.netology.web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;
import ru.netology.web.page.VerificationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransferPage transferPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void openAuthPage(String name, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = loginPage.validLogin(name, password);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode().getCode());
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void doTransfer(int amount, String cardNumber, int cardOrder){
        var cardId = DataHelper.pickACard(cardOrder).getTestId();
        transferPage = dashboardPage.pressTransferButton(cardId);
        dashboardPage = transferPage.doValidTransfer(String.valueOf(amount),cardNumber);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void verifyUpdatedBalance(int cardOrder, int finalAmount){
        var cardId = DataHelper.pickACard(cardOrder).getTestId();
        assertEquals(dashboardPage.getCardBalance(cardId), finalAmount);
    }
}