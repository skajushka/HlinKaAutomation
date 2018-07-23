import com.qulix.logger.Log;
import com.qulix.pages.StartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class AbstractTest {

    protected WebDriver webDriver;
    private static final String LINK_URL = "http://localhost:8080/QulixTeachingSite";

    @BeforeClass
    public static void setupDriver() {
        String osName = System.getProperty("os.name");

        if (osName.startsWith("Windows")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
    }

    public StartPage openWebsite() {
        webDriver = new ChromeDriver();
        webDriver.get(LINK_URL);
        return new StartPage(webDriver);
    }

    @AfterMethod
    public void tearDown() {
        Log.endLog("Test is ending!");
        webDriver.quit();
    }
}
