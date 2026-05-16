package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebTesting {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://www.mts.by/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Тест для проверки названия блока
    @Test(priority = 1)
    public void testBlockTitle() {
        final By COOKIE_BUTTON = By.xpath("//*[text() = 'Принять']");
        final By NAME_BLOCK = By.xpath("//h2[contains(normalize-space(), 'Онлайн пополнение')]");

        // Принимаем cookies
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON));
            cookieButton.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(COOKIE_BUTTON));
            System.out.println("Куки приняты");
        } catch (Exception e) {
            System.out.println("Кнопка cookie не найдена или уже принята");
        }

        // Проверка заголовка блока
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_BLOCK));
        String actualTitle = titleElement.getText().replaceAll("\\s+", " ").trim();
        String expectedTitle = "Онлайн пополнение без комиссии";

        Assert.assertEquals(actualTitle, expectedTitle,
                "Заголовок блока не соответствует ожидаемому. Ожидалось: " + expectedTitle + ", получено: " + actualTitle);

        System.out.println("Проверка заголовка: Успешно");
    }

    // Тест для проверки наличия логотипов платежных систем
    @Test(priority = 2)
    public void testPaymentLogos() {
        final By COOKIE_BUTTON = By.xpath("//*[text() = 'Принять']");
        final By LOGO_VISA = By.xpath("//img[@alt='Visa']");
        final By LOGO_VERIFIED = By.xpath("//li/img[@alt='Verified By Visa']");
        final By LOGO_MASTERCARD = By.xpath("(//div/ul/li/img[@alt='MasterCard'])[1]");
        final By LOGO_MASTERCARD_SECURE = By.xpath("//img[@alt='MasterCard Secure Code']");
        final By LOGO_BELKART = By.xpath("(//div/ul/li/img[@alt='Белкарт'])[1]");

        // Проверка наличия логотипов платежных систем
        Assert.assertTrue(isElementDisplayed(LOGO_VISA), "Логотип Visa не отображается");
        System.out.println("✓ Логотип Visa найден");

        Assert.assertTrue(isElementDisplayed(LOGO_VERIFIED), "Логотип Verified By Visa не отображается");
        System.out.println("✓ Логотип Verified By Visa найден");

        Assert.assertTrue(isElementDisplayed(LOGO_MASTERCARD), "Логотип MasterCard не отображается");
        System.out.println("✓ Логотип MasterCard найден");

        Assert.assertTrue(isElementDisplayed(LOGO_MASTERCARD_SECURE), "Логотип MasterCard Secure Code не отображается");
        System.out.println("✓ Логотип MasterCard Secure Code найден");

        Assert.assertTrue(isElementDisplayed(LOGO_BELKART), "Логотип Белкарт не отображается");
        System.out.println("✓ Логотип Белкарт найден");

        System.out.println("Проверка логотипов: Успешно");
    }

    // Вспомогательный метод для проверки отображения элемента
    private boolean isElementDisplayed(By locator) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
