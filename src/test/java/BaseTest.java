import org.example.DriverManager;
import org.example.service.MainPageService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected MainPageService mainPageService;

    @BeforeMethod
    public void startTests() {
        mainPageService = new MainPageService();
        mainPageService.openPageAndCookie();
    }

    @AfterMethod
    public void stopBrowser() {
        DriverManager.quitDriver();
    }
}