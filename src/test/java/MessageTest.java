import com.qulix.pages.LoginPage;

import com.qulix.pages.MessagePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MessageTest extends AbstractTest {

    @Test
    @Parameters({"Login", "Password"})
    public void testCreateMessage(String login, String password) {

        //login
        LoginPage pageLogin = new LoginPage(webDriver);

        try {
            webDriver.findElement(By.id("login"));
            System.out.println("Login page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Login page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with critical error");
        }

        pageLogin.signIn(login, password);

        // create new message

        WebElement webElement = webDriver.findElement(By.linkText("New Message"));
        webElement.click();

        try {
            webDriver.findElement(By.name("create"));
            System.out.println("Create Message page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Create Message page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with a critical error");
        }

        //send new message

        MessagePage messagePage = new MessagePage(webDriver);
        messagePage.sendMessage();

        //find newly created message in Message List

        webElement = webDriver.findElement(By.linkText("Message List"));
        webElement.click();

        webElement = webDriver.findElement(By.name("allUsers"));
        webElement.click();

        try {
            do {
                webDriver.findElement(By.linkText("Next")).click();
            } while (webDriver.findElement(By.linkText("Next")).isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("Next button is not present on the page");
        }

        WebElement lastRow = webDriver.findElement(By.xpath("//table/tbody/tr[last()]"));
        Assert.assertTrue(lastRow.getText().contains("New Message by Kate"));
        Assert.assertTrue(lastRow.getText().contains("This is the text of a new message"));
    }
}
