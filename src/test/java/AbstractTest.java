import com.qulix.pages.StartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

    protected static WebDriver webDriver;

    @BeforeMethod
    public void openWebsite() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/SidorenkoEV/IdeaProjects/HlinKaAutomation/chromedriver.exe");
        webDriver = new ChromeDriver();
        StartPage startPage = new StartPage(webDriver);
        webDriver.get(startPage.getLinkURL());
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}
