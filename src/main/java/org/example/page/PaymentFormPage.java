package org.example.page;

import org.example.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentFormPage extends BasePage {

    private WebDriverWait wait;

    private final By iframe = By.xpath("//iframe[contains(@src, 'pay')]");

    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    private WebElement phoneField;

    @FindBy(xpath = "//input[@placeholder='Номер абонента']")
    private WebElement internetField;

    @FindBy(xpath = "//input[contains(@placeholder, 'Номер счета') or contains(@placeholder, 'Номер договора')]")
    private WebElement installmentField;

    @FindBy(xpath = "//input[@placeholder='Номер счета']")
    private WebElement debtField;

    @FindBy(xpath = "//input[@placeholder='Сумма']")
    private WebElement amountField;

    @FindBy(xpath = "//button[contains(text(),'Продолжить')]")
    private WebElement continueButton;


    public PaymentFormPage() {
        super();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private void switchToIframe() {
        try {
            driver.switchTo().defaultContent();
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(iframe));
            driver.switchTo().frame(frame);
        } catch (Exception e) {
        }
    }

    private void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    private WebElement getFieldByPaymentType(String paymentType) {
        switch (paymentType) {
            case Constants.CONNECTION:
                return phoneField;
            case Constants.HOME_INTERNET:
                return internetField;
            case Constants.INSTALLMENT:
                return installmentField;
            case Constants.DEBT:
                return debtField;
            default:
                throw new IllegalArgumentException("Неизвестный тип оплаты: " + paymentType);
        }
    }

    public String getPlaceholder(String paymentType) {
        if (!paymentType.equals(Constants.CONNECTION)) {
            switchToIframe();
        }

        WebElement field = getFieldByPaymentType(paymentType);
        String placeholder = field.getAttribute("placeholder");

        if (!paymentType.equals(Constants.CONNECTION)) {
            switchToDefault();
        }
        return placeholder;
    }

    public boolean isFieldEmpty(String paymentType) {
        if (!paymentType.equals(Constants.CONNECTION)) {
            switchToIframe();
        }

        WebElement field = getFieldByPaymentType(paymentType);
        String value = field.getAttribute("value");
        boolean isEmpty = value == null || value.isEmpty();

        if (!paymentType.equals(Constants.CONNECTION)) {
            switchToDefault();
        }

        return isEmpty;
    }

    public PaymentFormPage enterData(String paymentType, String value, String amount) {
        WebElement field = getFieldByPaymentType(paymentType);
        field.clear();
        field.sendKeys(value);

        amountField.clear();
        amountField.sendKeys(amount);

        return this;
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }
}