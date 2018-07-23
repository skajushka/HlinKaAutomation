import com.qulix.logger.Log;
import com.qulix.pages.LoginPage;
import com.qulix.pages.StartPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends AbstractTest {

    private StartPage startPage;

    @BeforeMethod
    public void setup() {
        Log.startLog("Test is starting!");
        startPage = openWebsite();
    }

    @Test
    public void startPageOpenedTest() {
        assertTrue(startPage.checkIfUserControllerLinkIsPresent());
    }

    @Test
    public void loginPageOpenedTest() {
        LoginPage loginPage = startPage.clickOnUserControllerLink();

        assertTrue(loginPage.checkIfLoginFieldIsPresent());
        assertTrue(loginPage.checkIfPasswordFieldIsPresent());
        assertTrue(loginPage.checkIfLoginButtonIsPresent());
    }
}
