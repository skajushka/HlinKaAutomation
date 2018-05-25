import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

    protected static WebDriver webDriver;
    protected static WebElement webElement;

    @BeforeMethod
    public void openWebsite() {
        System.setProperty("webdriver.chrome.driver", "D:/automation/teachingproject-master/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/QulixTeachingSite");
        webElement = webDriver.findElement(By.linkText("qulixteachingsite.UserController"));
        webElement.click();
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}
