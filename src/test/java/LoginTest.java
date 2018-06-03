import com.qulix.pages.LoginPage;
import com.qulix.pages.StartPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends AbstractTest {

    @Test
    public void startPageOpenedTest() {
        //check that right page is opened
        StartPage startPage = new StartPage(webDriver);
        assertTrue(startPage.getUserControllerLink().isDisplayed());
    }

    @Test
    public void loginPageOpenedTest() {
        StartPage startPage = new StartPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        //check that Login page is opened after click on UserController
        startPage.getUserControllerLink().click();
        assertTrue(loginPage.getLoginField().isDisplayed());
        assertTrue(loginPage.getPasswordField().isDisplayed());
        assertTrue(loginPage.getButtonLogin().isDisplayed());
    }
}
