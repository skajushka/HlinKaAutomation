import com.qulix.pages.LoginPage;
import com.qulix.pages.StartPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends AbstractTest {

    @Test
    public void startPageOpenedTest() {
        //check that right page is opened
        StartPage startPage = new StartPage(webDriver);
        assertTrue(startPage.findUserControllerLink().isDisplayed());
    }

    @Test
    public void loginPageOpenedTest() {
        StartPage startPage = new StartPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        //check that Login page is opened after click on UserController
        startPage.findUserControllerLink().click();
        assertTrue(loginPage.findLoginField().isDisplayed());
        assertTrue(loginPage.findPasswordField().isDisplayed());
        assertTrue(loginPage.findButtonLogin().isDisplayed());
    }
}
