import com.qulix.helpers.ResourceFactory;
import com.qulix.pages.*;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
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
    private String FIRST_TEST_MESSAGE_HEADLINE = "First test message";
    private String FIRST_TEST_MESSAGE_TEXT = "This is the text of the first message";
    private String SECOND_TEST_MESSAGE_HEADLINE = "Second test message";
    private String SECOND_TEST_MESSAGE_TEXT = "This is the text of the second message";
    private String SECOND_USER_LOGIN = "jdoe";
    private String SECOND_USER_MESSAGE_HEADLINE = "New Message by John Doe";
    private String SECOND_USER_MESSAGE_TEXT = "This is the text of a new John's message";

    public MessageTest() {
        this.messages = ResourceFactory.getResources(MessageTest.RESOURCE_PATH);
    }

    private ListMessagesPage listMessagesPage;
    private CreateMessagePage createMessagePage;
    private ShowMessagePage showMessagePage;
    private EditMessagePage editMessagePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void initPages() {
        listMessagesPage = new ListMessagesPage(webDriver);
        createMessagePage = new CreateMessagePage(webDriver);
        showMessagePage = new ShowMessagePage(webDriver);
        editMessagePage = new EditMessagePage(webDriver);
        loginPage = new LoginPage(webDriver);
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageTest(String login, String password) {

         //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened
        loginToTestSite(login,password);
        assertTrue(listMessagesPage.findPageTitle().isDisplayed());

        // step 4 - open Create message page
        listMessagesPage.findNewMessageTab().click();
        assertTrue(createMessagePage.findButtonCreate().isDisplayed());

        // step 5 - send message?????
        createMessagePage.createMessage(MESSAGE_HEADLINE, MESSAGE_TEXT);

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

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened

        loginToTestSite(login, password);

        // step 4 - go to New Message page
        listMessagesPage.findNewMessageTab().click();

        // step 5 - send new message?????
        createMessagePage.createMessage(MESSAGE_HEADLINE, MESSAGE_TEXT);

        // step 6 - assert if message shown is the same that was created
        assertTrue(showMessagePage.findMessageHeadline().getText().contains(MESSAGE_HEADLINE));
        assertTrue(showMessagePage.findMessageText().getText().contains(MESSAGE_TEXT));

        //step 7 - find newly created message in Message List
        showMessagePage.findTabToMessagesList().click();
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

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened

        loginToTestSite(login, password);

        // step 4 - go to New Message page
        listMessagesPage.findNewMessageTab().click();

        // step 5 - send new message?????
        createMessagePage.createMessage(MESSAGE_HEADLINE, MESSAGE_TEXT);

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

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.findNewMessageTab().click();

        //step 5 - sent message?????
        createMessagePage.createMessage(MESSAGE_HEADLINE, MESSAGE_TEXT); //may be additional check is needed

        //step 6 - make sure that newly created message is correctly displayed on Show Message page
        assertTrue(showMessagePage.findMessageHeadline().getText().contains(MESSAGE_HEADLINE)); //may be more precise assertion is needed??? (to use date, for example)
        assertTrue(showMessagePage.findMessageText().getText().contains(MESSAGE_TEXT));

        //step 7 - go to Message List page and make sure that new message is displayed there
        showMessagePage.findTabToMessagesList().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        //step 8 - delete recently created message and make sure it's not displayed in the list anymore

        String lastMessageDate = listMessagesPage.getLastMessageCreationDate().toString();
        listMessagesPage.getLastRowDeleteButton().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertFalse(listMessagesPage.findLastTableRow().getText().contains(lastMessageDate));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageWithoutSavingTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        //remember the last row in the Message List
        String lastTableRowText = listMessagesPage.findLastTableRow().getText();

        //step 4 - go to Create Message page
        listMessagesPage.findNewMessageTab().click();

        //step 5 - go to Messages List without saving new message
        createMessagePage.fillInMessageFields(MESSAGE_HEADLINE, MESSAGE_TEXT);

        //step 6 - verify that no new messages were added to the list
        createMessagePage.findTabToMessagesList().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(lastTableRowText));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createTwoMessagesTest(String login, String password) {
        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.findNewMessageTab().click();

        //step 5 - create message
        createMessagePage.createMessage(FIRST_TEST_MESSAGE_HEADLINE, FIRST_TEST_MESSAGE_TEXT);

        //step 6 - send message
        assertEquals(showMessagePage.findMessageHeadline().getText(), FIRST_TEST_MESSAGE_HEADLINE);
        assertEquals(showMessagePage.findMessageText().getText(), FIRST_TEST_MESSAGE_TEXT);

        //step 7 - open New Message page
        showMessagePage.findTabToNewMessage().click();
        assertTrue(createMessagePage.findButtonCreate().isDisplayed());

        //step 8 - fill in Message fields
        createMessagePage.createMessage(SECOND_TEST_MESSAGE_HEADLINE, SECOND_TEST_MESSAGE_TEXT);

        //step 9 - click create and verify the message was created with correct data
        assertEquals(showMessagePage.findMessageHeadline().getText(), SECOND_TEST_MESSAGE_HEADLINE);
        assertEquals(showMessagePage.findMessageText().getText(), SECOND_TEST_MESSAGE_TEXT);

        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        //step 10 - verify that both messages are in the Message list
        assertTrue(listMessagesPage.findBeforeTheLastTableRow().getText().contains(FIRST_TEST_MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(SECOND_TEST_MESSAGE_HEADLINE));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void viewOtherUsersMessagesTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.findNewMessageTab().click();

        //step5 - send new message
        createMessagePage.createMessage(MESSAGE_HEADLINE, MESSAGE_TEXT);

        //strep6 - verify that correct message is shown on Show page
        assertEquals(showMessagePage.findMessageHeadline().getText(), MESSAGE_HEADLINE);
        assertEquals(showMessagePage.findMessageText().getText(), MESSAGE_TEXT);

        //step 7 - Go to Message List and verify that message is shown there
        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));

        //step 8 - view created message
        listMessagesPage.getLastRowViewButton().click();
        assertTrue(showMessagePage.findMessageHeadline().getText().contains(MESSAGE_HEADLINE));
        assertTrue(showMessagePage.findMessageText().getText().contains(MESSAGE_TEXT));

        //step 9 - return to message list
        showMessagePage.findTabToMessagesList().click();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(MESSAGE_TEXT));
        String adminMessage = listMessagesPage.findLastTableRow().getText();

        //step 10
        listMessagesPage.findLogoutButton().click();
        assertTrue(loginPage.findButtonLogin().isDisplayed());

        //step 11
        loginPage.login(SECOND_USER_LOGIN, password);

        assertTrue(listMessagesPage.findPageTitle().isDisplayed());
        assertTrue(listMessagesPage.finsUserGreeting().getText().contains("Hello John Doe!"));

        //step 12
        listMessagesPage.findNewMessageTab().click();
        createMessagePage.createMessage(SECOND_USER_MESSAGE_HEADLINE, SECOND_USER_MESSAGE_TEXT);

        assertEquals(showMessagePage.findMessageHeadline().getText(), SECOND_USER_MESSAGE_HEADLINE);
        assertEquals(showMessagePage.findMessageText().getText(), SECOND_USER_MESSAGE_TEXT);

        showMessagePage.findTabToMessagesList().click();
        listMessagesPage.findAllUsersMessagesOption().click(); //we have a bug here - need to recheck

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertTrue(listMessagesPage.findLastTableRow().getText().contains(SECOND_USER_MESSAGE_HEADLINE));
        assertTrue(listMessagesPage.findLastTableRow().getText().contains(SECOND_USER_MESSAGE_TEXT));
        String johnMessage = listMessagesPage.findLastTableRow().getText();

        listMessagesPage.findLogoutButton().click();
        assertTrue(loginPage.findButtonLogin().isDisplayed());

        // step 13 - login as admin user
        loginPage.login(login, password);
        assertTrue(listMessagesPage.findPageTitle().isDisplayed());
        assertTrue(listMessagesPage.finsUserGreeting().getText().contains("Hello Administrator!"));

        //step 14 - select checkbox and check that massages of both users are shown
        listMessagesPage.findAllUsersMessagesOption().click();
        webDriver.navigate().refresh();

        try {
            listMessagesPage.clickNextButton();
        } catch (NoSuchElementException e) {
            System.out.println(messages.get(NO_NEXT_BUTTON_ERROR));
        }

        assertEquals(listMessagesPage.findBeforeTheLastTableRow().getText(),adminMessage);
        assertEquals(listMessagesPage.findLastTableRow().getText(), johnMessage);


    }


    private void loginToTestSite(String login, String password) {
        StartPage startPage = new StartPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        startPage.findUserControllerLink().click();
        loginPage.login(login, password);
    }
}
