package org.example.page;

import org.example.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    @FindBy(xpath = "//*[text()='Принять']")
    private WebElement cookieButton;

    @FindBy(xpath = "//h2[contains(normalize-space(), 'Онлайн пополнение')]")
    private WebElement blockTitle;

    @FindBy(xpath = "//img[@alt='Visa']")
    private WebElement visaLogo;

    @FindBy(xpath = "//img[@alt='Verified By Visa']")
    private WebElement verifiedLogo;

    @FindBy(xpath = "(//img[@alt='MasterCard'])[1]")
    private WebElement mastercardLogo;

    @FindBy(xpath = "//img[@alt='MasterCard Secure Code']")
    private WebElement secureCodeLogo;

    @FindBy(xpath = "(//img[@alt='Белкарт'])[1]")
    private WebElement belkartLogo;

    @FindBy(xpath = "//a[contains(text(),'Подробнее о сервисе')]")
    private WebElement detailsLink;

    private final By DROPDOWN_SELECTOR = By.xpath("//div[contains(@class, 'select__selected-value')]");

    public MainPage openPage(String url) {
        driver.get(url);
        return this;
    }
    public MainPage acceptCookie() {
        try {
            cookieButton.click();
        } catch (Exception e) {
        }
        return this;
    }

    public String getBlockTitle() {
        return blockTitle.getText().replaceAll("\\s+", " ").trim();
    }

    public boolean isVisaLogoDisplayed() {
        return visaLogo.isDisplayed();
    }

    public boolean isVerifiedLogoDisplayed() {
        return verifiedLogo.isDisplayed();
    }

    public boolean isMastercardLogoDisplayed() {
        return mastercardLogo.isDisplayed();
    }

    public boolean isSecureCodeLogoDisplayed() {
        return secureCodeLogo.isDisplayed();
    }

    public boolean isBelkartLogoDisplayed() {
        return belkartLogo.isDisplayed();
    }

    public MainPage clickDetailsLink() {
        detailsLink.click();
        return this;
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public PaymentFormPage selectPaymentType(String paymentType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        if (paymentType.equals(Constants.CONNECTION)) {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'" + paymentType + "')]")
            ));
            element.click();
            System.out.println("✓ Выбрано: " + paymentType);
        } else {

            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(DROPDOWN_SELECTOR));
            dropdown.click();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String xpath;
            switch (paymentType) {
                case Constants.HOME_INTERNET:
                    xpath = "/html/body/div[6]/main/div/div[3]/div[2]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button/span[1]";
                    break;
                case Constants.INSTALLMENT:
                    xpath = "//span[contains(text(),'Рассрочка')]";
                    break;
                case Constants.DEBT:
                    xpath = "//span[contains(text(),'Задолженность')]";
                    break;
                default:
                    xpath = "//span[contains(text(),'" + paymentType + "')]";
            }

            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            element.click();
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new PaymentFormPage();
    }
}