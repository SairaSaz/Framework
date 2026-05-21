import jdk.jfr.Description;
import org.example.service.MainPageService;
import org.example.service.PaymentFormService;
import org.example.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WebTesting extends BaseTest {

    @Test(priority = 1)
    @Description("test Block Title")
    public void testBlockTitle() {
        String actualTitle = mainPageService.getBlockTitle();
        String expectedTitle = "Онлайн пополнение без комиссии";

        Assert.assertEquals(actualTitle, expectedTitle, "Заголовок блока не соответствует!");
    }
    @Test(priority = 2)
    @Description("test Payment Logos")
    public void testPaymentLogos() {
        Assert.assertTrue(mainPageService.areAllLogosDisplayed(), "Не все логотипы отображаются");
    }

    @Test(priority = 3)
    @Description("testn Details Link")
    public void testDetailsLink() {
        String oldUrl = mainPageService.getCurrentUrl();
        mainPageService.clickDetailsLink();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String newUrl = mainPageService.getCurrentUrl();
        Assert.assertNotEquals(oldUrl, newUrl, "Ссылка 'Подробнее о сервисе' не работает");

    }

    @Test(priority = 4)
    @Description("test continue button")
    public void testContinueButton() {
        PaymentFormService form = mainPageService.selectPaymentType(Constants.CONNECTION);
        form.enterData(Constants.CONNECTION, Constants.TEST_PHONE, Constants.TEST_AMOUNT);

        Assert.assertTrue(form.isContinueButtonEnabled(), "Кнопка 'Продолжить' не активна");
    }
    @Test(priority = 5, dataProvider = "paymentTypes")
    @Description("Test Placeholder")
    public void testPlaceholders(String paymentType, String expectedPlaceholder) {
        PaymentFormService form = mainPageService.selectPaymentType(paymentType);

        String actualPlaceholder = form.getPlaceholder(paymentType);
        Assert.assertEquals(actualPlaceholder, expectedPlaceholder,
                "Плейсхолдер для " + paymentType + " не соответствует!");

        Assert.assertTrue(form.isFieldEmpty(paymentType),
                "Поле для " + paymentType + " должно быть пустым!");
    }
    @DataProvider(name = "paymentTypes")
    public Object[][] paymentTypes() {
        return new Object[][]{
                {Constants.CONNECTION, Constants.PHONE_PLACEHOLDER},
                {Constants.HOME_INTERNET, Constants.INTERNET_PLACEHOLDER},
                {Constants.INSTALLMENT, Constants.INSTALLMENT_PLACEHOLDER},
                {Constants.DEBT, Constants.DEBT_PLACEHOLDER}
        };
    }
}