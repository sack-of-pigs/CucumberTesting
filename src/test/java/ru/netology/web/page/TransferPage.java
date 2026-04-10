package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $("h1");
    private SelenideElement transferAmountInput = $("[data-test-id=amount] input");
    private SelenideElement transferFromInput = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransferPage() {
        heading.should(Condition.text("Пополнение карты")).should(Condition.visible);
    }

    public DashboardPage doValidTransfer(String amount, String cardNumber) {
        transferAmountInput.setValue(amount);
        transferFromInput.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    }
}