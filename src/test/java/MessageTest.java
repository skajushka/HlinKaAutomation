import com.qulix.pages.ListMessagesPage;
import com.qulix.pages.LoginPage;
import com.qulix.pages.CreateMessagePage;
import com.qulix.pages.ShowMessagePage;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;

public class MessageTest extends AbstractTest {

    private Properties messages;

    private static String RESOURCE_PATH = "MessageTest/settings.properties";
    private static String CRITICAL_ERROR_MESSAGE = "criticalError.message";
    private static String LOGIN_PAGE_SUCCESS = "loginPage.success";
    private static String LOGIN_PAGE_FAILED = "loginPage.failed";
    private static String CREATE_MESSAGE_PAGE_SUCCESS = "createMessagePage.success";
    private static String CREATE_MESSAGE_PAGE_FAILED = "createMessagePage.failed";
    private static String NO_NEXT_BUTTON_ERROR = "error.noNextButton";
    private static String LIST_MESSAGES_PAGE_SUCCESS = "listMessagePage.success";
    private static String LIST_MESSAGES_PAGE_FAILED = "listMessagePage.failed";
    private static String SHOW_MESSAGE_HEADLINE = "showMessageHeadline";
    private static String SHOW_MESSAGE_PAGE_SUCCESS = "showMessagePage.success";
    private static String SHOW_MESSAGE_PAGE_FAILED = "showMessagePage.failed";

    private String MESSAGE_HEADLINE = "New Message by Kate";
    private String MESSAGE_TEXT = "This is the text of a new message";

    public MessageTest() {
        this.messages = ResourceFactory.getResources(MessageTest.RESOURCE_PATH);
    }

    @Test
    @Parameters({"Login", "Password"})
    public void testCreateMessage(String login, String password) {

        //steps 1 and 2 are executed in @BeforeTest section of AbstractTest
        //step 3 - login

        LoginPage pageLogin = new LoginPage(webDriver);

        try {
            pageLogin.editLogin();
            System.out.println(messages.get(LOGIN_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(LOGIN_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        pageLogin.enterLogin(login);
        pageLogin.enterPassword(password);
        pageLogin.submit();

        //step 3 - make sure that Message list page is opened

        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);

        try {
            listMessagesPage.findPageTitle();
            System.out.println(messages.get(LIST_MESSAGES_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(LIST_MESSAGES_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        listMessagesPage.findNewMessageTab().click();

        // step 4 - open Create message page

        CreateMessagePage messagePage = new CreateMessagePage(webDriver);

        try {
            messagePage.findButtonCreate();
            System.out.println(messages.get(CREATE_MESSAGE_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(CREATE_MESSAGE_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        // step 5 - send message

        messagePage.sendMessage();

        //step 6 - verify that Show message page is opened

        ShowMessagePage showMessagePage = new ShowMessagePage(webDriver);

        try {
            showMessagePage.findShowMessageNote().getText().contains((CharSequence) messages.get(SHOW_MESSAGE_HEADLINE));
            System.out.println(messages.get(SHOW_MESSAGE_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(SHOW_MESSAGE_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        //step 7 - open Message List page and verify that new message is presented in the list

        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void testShowMessage (String login, String password) {
        //steps 1 and 2 are executed in @BeforeTest section of AbstractTest
        //step 3 - login

        LoginPage pageLogin = new LoginPage(webDriver);

        try {
            pageLogin.editLogin();
            System.out.println(messages.get(LOGIN_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(LOGIN_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        pageLogin.enterLogin(login);
        pageLogin.enterPassword(password);
        pageLogin.submit();

        //step 3 - make sure that Message list page is opened
        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);

        try {
            listMessagesPage.findPageTitle();
            System.out.println(messages.get(LIST_MESSAGES_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(LIST_MESSAGES_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        // step 4 - go to New Message page
        listMessagesPage.findNewMessageTab().click();

        // step 5 - send new message

        CreateMessagePage messagePage = new CreateMessagePage(webDriver);

        try {
            messagePage.findButtonCreate();
            System.out.println(messages.get(CREATE_MESSAGE_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(CREATE_MESSAGE_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        messagePage.sendMessage();

        // step 6 - assert if message shown is the same that was created
        ShowMessagePage showMessagePage = new ShowMessagePage(webDriver);

        try {
            showMessagePage.findShowMessageNote().getText().contains((CharSequence) messages.get(SHOW_MESSAGE_HEADLINE));
            System.out.println(messages.get(SHOW_MESSAGE_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(SHOW_MESSAGE_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        Assert.assertTrue(showMessagePage.findMessageHeadline().getText().contains(MESSAGE_HEADLINE));
        Assert.assertTrue(showMessagePage.findMessageText().getText().contains(MESSAGE_TEXT));

        //step 7 - find newly created message in Message List

        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));

        //step 8 - select and view newly created message
        listMessagesPage.findViewButton().click();

        try {
            showMessagePage.findShowMessageNote().getText().contains((CharSequence) messages.get(SHOW_MESSAGE_HEADLINE));
            System.out.println(messages.get(SHOW_MESSAGE_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(SHOW_MESSAGE_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        Assert.assertTrue(showMessagePage.findMessageHeadline().getText().contains(MESSAGE_HEADLINE));
        Assert.assertTrue(showMessagePage.findMessageText().getText().contains(MESSAGE_TEXT));

        //step 9 - go to Messages List and view created message in the table

        showMessagePage.findTabToMessagesList().click();

        try {
            listMessagesPage.findPageTitle();
            System.out.println(messages.get(LIST_MESSAGES_PAGE_SUCCESS));
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(LIST_MESSAGES_PAGE_FAILED));
            webDriver.quit();
            throw new RuntimeException((String) messages.get(CRITICAL_ERROR_MESSAGE));
        }

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        Assert.assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));
    }
}

