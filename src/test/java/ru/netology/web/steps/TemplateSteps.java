package ru.netology.web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;

    @Пусть("пользователь залогинен под тестовыми данными")
    public void openAuthPage() {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = loginPage.validLogin(DataHelper.getAuthInfo().getLogin(), DataHelper.getAuthInfo().getPassword());
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode().getCode());
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою 1 карту с главной страницы")
    public void transferFromSecondToFirst(int amount, String cardNumber){
        dashboardPage.transferMoneyTo(DataHelper.getCardsInfo().getFirstCard().getTestId(),cardNumber,amount);
    }

    @Тогда("баланс его 1 карты из списка на главной странице должен стать {int} рублей")
    public void verifyUpdatedBalance(int finalAmount){
        assertEquals(dashboardPage.getCardBalance(DataHelper.getCardsInfo().getFirstCard().getTestId()),finalAmount);
    }
}