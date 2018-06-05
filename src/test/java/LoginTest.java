import com.qulix.pages.LoginPage;
import com.qulix.pages.StartPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends AbstractTest {

    private StartPage startPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void initPages() {
        startPage = new StartPage(webDriver);
        loginPage = new LoginPage(webDriver);
    }

    @Test
    public void startPageOpenedTest() {
        assertTrue(startPage.checkIfUserControllerLinkIsPresent());
    }

    @Test
    public void loginPageOpenedTest() {
        startPage.clickOnUserControllerLink();

        assertTrue(loginPage.checkIfLoginFieldIsPresent());
        assertTrue(loginPage.checkIfPasswordFieldIsPresent());
        assertTrue(loginPage.checkIfLoginButtonIsPresent());
    }
}
