import com.qulix.helpers.ResourceFactory;
import com.qulix.pages.*;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.testng.Assert.*;

public class MessageTest extends AbstractTest {

    private Properties messages;

    private static String RESOURCE_PATH = "MessageTest/settings.properties";
    private static String NO_NEXT_BUTTON_ERROR = "error.noNextButton";
    private static String MESSAGE_HEADLINE = "New Message by Kate";
    private static String MESSAGE_TEXT = "This is the text of a new message";
    private String EDITED_MESSAGE_HEADLINE = "Edited Message by Kate";
    private String EDITED_MESSAGE_TEXT = "This is the text of edited message";

    public MessageTest() {
        this.messages = ResourceFactory.getResources(MessageTest.RESOURCE_PATH);
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);
        CreateMessagePage createMessagePage = new CreateMessagePage(webDriver);
        ShowMessagePage showMessagePage = new ShowMessagePage(webDriver);

        //steps 1 and 2 are executed in @BeforeTest section of AbstractTest

        //step 3 - login and make sure that Message list page is opened
        loginToTestSite(login,password);
        assertTrue(listMessagesPage.findPageTitle().isDisplayed());

        // step 4 - open Create message page
        listMessagesPage.findNewMessageTab().click();
        assertTrue(createMessagePage.findButtonCreate().isDisplayed());

        // step 5 - send message?????
        createMessagePage.sentMessage();

        //step 6 - verify that Show message page is opened
        assertTrue(showMessagePage.findShowMessageNote().isDisplayed());

        //step 7 - open Message List page and verify that new message is presented in the list
        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void showMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);
        CreateMessagePage createMessagePage = new CreateMessagePage(webDriver);
        ShowMessagePage showMessagePage = new ShowMessagePage(webDriver);

        //steps 1 and 2 are executed in @BeforeTest section of AbstractTest
        //step 3 - login and make sure that Message list page is opened

        loginToTestSite(login, password);

        // step 4 - go to New Message page
        listMessagesPage.findNewMessageTab().click();

        // step 5 - send new message?????
        createMessagePage.sentMessage();

        // step 6 - assert if message shown is the same that was created
        assertTrue(showMessagePage.findMessageHeadline().getText().contains(MESSAGE_HEADLINE));
        assertTrue(showMessagePage.findMessageText().getText().contains(MESSAGE_TEXT));

        //step 7 - find newly created message in Message List
        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));

        //step 8 - select and view newly created message
        listMessagesPage.getLastRowViewButton().click();
        assertTrue(showMessagePage.findMessageHeadline().getText().contains(MESSAGE_HEADLINE));
        assertTrue(showMessagePage.findMessageText().getText().contains(MESSAGE_TEXT));

        //step 9 - go to Messages List and view created message in the table
        showMessagePage.findTabToMessagesList().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);
        CreateMessagePage createMessagePage = new CreateMessagePage(webDriver);
        ShowMessagePage showMessagePage = new ShowMessagePage(webDriver);
        EditMessagePage editMessagePage = new EditMessagePage(webDriver);

        //steps 1 and 2 are executed in @BeforeTest section of AbstractTest
        //step 3 - login and make sure that Message list page is opened

        loginToTestSite(login, password);

        // step 4 - go to New Message page
        listMessagesPage.findNewMessageTab().click();

        // step 5 - send new message?????
        createMessagePage.sentMessage();

        //step 6 - assert that Show message page is displayed and message is shown correctly
        assertTrue(showMessagePage.findMessageHeadline().getText().contains(MESSAGE_HEADLINE));
        assertTrue(showMessagePage.findMessageText().getText().contains(MESSAGE_TEXT));

        //step 7 - go to Message List page and assert that the list contains newly created message
        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));

        //step 8 - assert that Edit Message page is opened and message is displayed correctly
        listMessagesPage.getLastRowEditButton().click();
        /*listMessagesPage.findEditButton().click();*/
        assertTrue(editMessagePage.findMessageHeadline().getAttribute("value").contains(MESSAGE_HEADLINE));
        assertTrue(editMessagePage.findMessageText().getAttribute("value").contains(MESSAGE_TEXT));

       //step 9 - type in other message headline and text
        editMessagePage.changeMessageHeadlineAndText();
        assertTrue(editMessagePage.findMessageHeadline().getAttribute("value").contains(EDITED_MESSAGE_HEADLINE));
        assertTrue(editMessagePage.findMessageText().getAttribute("value").contains(EDITED_MESSAGE_TEXT));

        //step 10 - save changes and make sure new values are shown on Show Message page
        editMessagePage.findSaveButton().click();
        assertTrue(showMessagePage.findMessageHeadline().getText().contains(EDITED_MESSAGE_HEADLINE));
        assertTrue(showMessagePage.findMessageText().getText().contains(EDITED_MESSAGE_TEXT));

        //step 11 - go to Message List and make sure new values for the message are displayed there
        editMessagePage.findTabToMessagesList().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(EDITED_MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(EDITED_MESSAGE_TEXT));
    }

    private void loginToTestSite(String login, String password) {
        StartPage startPage = new StartPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        startPage.findUserControllerLink().click();
        loginPage.login(login, password);

    }
}
