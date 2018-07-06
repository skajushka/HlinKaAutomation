import com.qulix.pages.StartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

    protected WebDriver webDriver;

    @BeforeClass
    public static void setupDriver() {
        String osName = System.getProperty("os.name");

        if (osName.startsWith("Windows")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
    }

    @BeforeMethod
    public void openWebsite() {
        webDriver = new ChromeDriver();
        StartPage startPage = new StartPage(webDriver);
        webDriver.get(startPage.getLinkURL());
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}
