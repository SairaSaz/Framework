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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLogos() {

        WebElement cookie = driver.findElement(By.xpath("//*[text()='Принять']"));
        cookie.click();wait.until(ExpectedConditions.invisibilityOf(cookie));

        WebElement visa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Visa']")));
        Assert.assertTrue(visa.isDisplayed(), "Логотип Visa не найден");

        WebElement verified = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Verified By Visa']")));
        Assert.assertTrue(verified.isDisplayed(), "Логотип Verified By Visa не найден");

        WebElement mastercard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@alt='MasterCard'])[1]")));
        Assert.assertTrue(mastercard.isDisplayed(), "Логотип MasterCard не найден");

        WebElement secure = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='MasterCard Secure Code']")));
        Assert.assertTrue(secure.isDisplayed(), "Логотип MasterCard Secure Code не найден");

        WebElement belkart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@alt='Белкарт'])[1]")));
        Assert.assertTrue(belkart.isDisplayed(), "Логотип Белкарт не найден");
    }
    @Test
    public void testBlockTitle() {
        final By COOKIE_BUTTON = By.xpath("//*[text() = 'Принять']");
        final By NAME_BLOCK = By.xpath("//h2[contains(normalize-space(), 'Онлайн пополнение без комиссии')]");

        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_BLOCK));
        String actualTitle = titleElement.getText().replaceAll("\\s+", " ").trim();
        String expectedTitle = "Онлайн пополнение без комиссии";

        Assert.assertEquals(actualTitle, expectedTitle, "Заголовок блока не соответствует");
    }

    @Test
    public void testLinkDetails() {
            WebElement cookie = driver.findElement(By.xpath("//*[text()='Принять']"));
            cookie.click();
            wait.until(ExpectedConditions.invisibilityOf(cookie));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Подробнее о сервисе')]")));
        String oldUrl = driver.getCurrentUrl();
        link.click();

        String newUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(oldUrl, newUrl, "Ссылка не работает");
    }

    @Test
    public void testButtonContinue() {
            WebElement cookie = driver.findElement(By.xpath("//*[text()='Принять']"));
            cookie.click();
            wait.until(ExpectedConditions.invisibilityOf(cookie));

        WebElement service = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Услуги связи')]")));
        service.click();

        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Номер телефона']")));
        phone.clear();
        phone.sendKeys("297777777");

        WebElement sum = driver.findElement(By.xpath("//form[1]//input[@placeholder='Сумма']"));
        sum.clear();
        sum.sendKeys("11");

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Продолжить')]")));
        Assert.assertTrue(button.isEnabled(), "Кнопка не активна");
        button.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}