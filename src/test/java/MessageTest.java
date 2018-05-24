import com.qulix.pages.ListMessagesPage;
import com.qulix.pages.LoginPage;

import com.qulix.pages.CreateMessagePage;
import com.qulix.pages.ShowMessagePage;
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
            pageLogin.editLogin();
            System.out.println("Login page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Login page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with critical error");
        }

        pageLogin.enterLogin(login);
        pageLogin.enterPassword(password);
        pageLogin.submit();

        // go to New Message page

        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);
        listMessagesPage.findNewMessageTab().click();

        // send new message

        CreateMessagePage messagePage = new CreateMessagePage(webDriver);

        try {
            messagePage.findButtonCreate();
            System.out.println("Create Message page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Create Message page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with a critical error");
        }

        messagePage.sendMessage();

        //find newly created message in Message List

        ShowMessagePage showMessagePage = new ShowMessagePage(webDriver);
        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            do {
                listMessagesPage.findNextButton().click();
            } while (listMessagesPage.findNextButton().isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("Next button is not present on the page");
        }

        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains("New Message by Kate"));
        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains("This is the text of a new message"));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void testShowMessage (String login, String password) {

        //login

        LoginPage pageLogin = new LoginPage(webDriver);

        try {
            pageLogin.editLogin();
            System.out.println("Login page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Login page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with critical error");
        }

        pageLogin.enterLogin(login);
        pageLogin.enterPassword(password);
        pageLogin.submit();

        //make sure that Message list page is opened
        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);

        try {
            listMessagesPage.findPageTitle();
            System.out.println("Message List page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Message List page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with critical error");
        }

        // go to New Message page
        listMessagesPage.findNewMessageTab().click();

        // send new message

        CreateMessagePage messagePage = new CreateMessagePage(webDriver);

        try {
            messagePage.findButtonCreate();
            System.out.println("Create Message page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Create Message page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with a critical error");
        }

        messagePage.sendMessage();

        // assert if message shown is the same that was created
        ShowMessagePage showMessagePage = new ShowMessagePage(webDriver);

        try {
            showMessagePage.findShowMessageNote().getText().contains("Show Message");
            System.out.println("Show Message page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Create Message page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with a critical error");
        }


        Assert.assertTrue(showMessagePage.findMessageHeadline().getText().contains("New Message by Kate"));
        Assert.assertTrue(showMessagePage.findMessageText().getText().contains("This is the text of a new message"));

        //find newly created message in Message List

        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            do {
                listMessagesPage.findNextButton().click();
            } while (listMessagesPage.findNextButton().isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("Next button is not present on the page");
        }

        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains("New Message by Kate"));
        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains("This is the text of a new message"));

        //select and view newly created message
        listMessagesPage.findViewButton().click();

        try {
            showMessagePage.findShowMessageNote().getText().contains("Show Message");
            System.out.println("Show Message page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Create Message page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with a critical error");
        }

        Assert.assertTrue(showMessagePage.findMessageHeadline().getText().contains("New Message by Kate"));
        Assert.assertTrue(showMessagePage.findMessageText().getText().contains("This is the text of a new message"));

        //go to Messages List and view created message in the table

        showMessagePage.findTabToMessagesList().click();

        try {
            listMessagesPage.findPageTitle();
            System.out.println("Message List page is opened");
        } catch (NoSuchElementException e) {
            System.out.println("Message List page is not opened");
            webDriver.quit();
            throw new RuntimeException("Test ended with critical error");
        }

        try {
            do {
                listMessagesPage.findNextButton().click();
            } while (listMessagesPage.findNextButton().isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("Next button is not present on the page");
        }

        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains("New Message by Kate"));
        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains("This is the text of a new message"));
    }
}

