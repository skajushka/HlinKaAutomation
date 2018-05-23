import com.qulix.pages.LoginPage;

import com.qulix.pages.MessagePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

        pageLogin.enterLogin(login);
        pageLogin.enterPassword(password);
        pageLogin.submit();

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

    @Test
    @Parameters({"Login", "Password"})
    public void testShowMessage (String login, String password) {

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

        pageLogin.enterLogin(login);
        pageLogin.enterPassword(password);
        pageLogin.submit();

        //create new message

        webElement = webDriver.findElement(By.linkText("New Message"));
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

        // assert if message shown is the same that was created

        try {
            WebElement showMessageNote = webDriver.findElement(By.xpath("/html/body/div[5]/h1"));
            showMessageNote.getText().contains("Show Message");
            System.out.println("Show Message page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Create Message page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with a critical error");
        }

        WebElement showMessageHeadline = webDriver.findElement(By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[1]/td[2]"));
        Assert.assertTrue(showMessageHeadline.getText().contains("New Message by Kate"));
        WebElement showMessageText = webDriver.findElement(By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[3]/td[2]"));
        Assert.assertTrue(showMessageText.getText().contains("This is the text of a new message"));

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

        WebElement lastRow1 = webDriver.findElement(By.xpath("//table/tbody/tr[last()]"));
        Assert.assertTrue(lastRow1.getText().contains("New Message by Kate"));
        Assert.assertTrue(lastRow1.getText().contains("This is the text of a new message"));
    }
}