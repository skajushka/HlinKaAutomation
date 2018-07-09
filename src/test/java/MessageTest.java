import com.qulix.helpers.ResourceFactory;
import com.qulix.message.Message;
import com.qulix.pages.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.testng.Assert.*;

public class MessageTest extends AbstractTest {

    Properties messages;

    private static String RESOURCE_PATH = "MessageTest/settings.properties";
    private static String ADMIN_USER_NAME = "admin.user.name";
    private static String ADMIN_USER_GREETING = "admin.user.greeting";
    private static String ADMIN_MESSAGE_HEADLINE = "admin.message.headline";
    private static String ADMIN_MESSAGE_TEXT = "admin.message.text";
    private static String SECOND_USER_NAME = "second.user.name";
    private static String SECOND_USER_GREETING = "second.user.greeting";
    private static String SECOND_USER_MESSAGE_HEADLINE = "second.user.message.headline";
    private static String SECOND_USER_MESSAGE_TEXT = "second.user.message.text";
    private static String EDITED_MESSAGE_HEADLINE = "edited.message.headline";
    private static String EDITED_MESSAGE_TEXT = "edited.message.text";
    private static String FIRST_TEST_MESSAGE_HEADLINE = "first.test.message.headline";
    private static String FIRST_TEST_MESSAGE_TEXT = "first.test.message.text";
    private static String SECOND_TEST_MESSAGE_HEADLINE = "second.test.message.headline";
    private static String SECOND_TEST_MESSAGE_TEXT = "second.test.message.text";
    private static String LIST_MESSAGES_PAGE_TITLE = "list.messages.page.title";
    private static String CREATE_MESSAGE_PAGE_TITLE = "create.message.page.title";
    private static String SHOW_MESSAGE_PAGE_TITLE = "show.message.page.title";


    public MessageTest() {
        this.messages = ResourceFactory.getResources(MessageTest.RESOURCE_PATH);
    }

    private ListMessagesPage listMessagesPage;
    private CreateMessagePage createMessagePage;
    private ShowMessagePage showMessagePage;
    private EditMessagePage editMessagePage;
    private LoginPage loginPage;
    private Message message;


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
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));

        // step 4 - open Create message page
        listMessagesPage.clickNewMessageTab();
        assertEquals(createMessagePage.checkPageTitle(), messages.getProperty(CREATE_MESSAGE_PAGE_TITLE));

        // step 5 - send message
        //may be additional check is needed to make sure that input looks like expected
        message = new Message(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));
        createMessagePage.createMessage(message.getHeadline(), message.getText());
        createMessagePage.clickCreateButton();

        //step 6 - verify that Show message page is opened
        assertEquals(showMessagePage.checkPageTitle(), messages.getProperty(SHOW_MESSAGE_PAGE_TITLE));

        //step 7 - open Message List page and verify that new message is presented in the list
        showMessagePage.clickTabToMessagesList();

        goToLastPage();

        //may be we need to check the match more precisely? E.x., using assertEquals. That is a question for the most parts of asserts.
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getText()));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void showMessageTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened
        loginToTestSite(login, password);

        // step 4 - go to New Message page
        listMessagesPage.clickNewMessageTab();

        // step 5 - send new message
        //may be additional check is needed to make sure that input looks like expected
        message = new Message(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));
        createMessagePage.createMessage(message.getHeadline(), message.getText());
        createMessagePage.clickCreateButton();

        // step 6 - assert if message shown is the same that was created
        assertEquals(showMessagePage.getTextOfMessageHeadline(), message.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), message.getText());

        //step 7 - find newly created message in Message List
        showMessagePage.clickTabToMessagesList();
        goToLastPage();

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getText()));

        //step 8 - select and view newly created message
        listMessagesPage.clickLastRowViewButton();
        assertEquals(showMessagePage.getTextOfMessageHeadline(), message.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), message.getText());

        //step 9 - go to Messages List and view created message in the table
        showMessagePage.clickTabToMessagesList();
        goToLastPage();

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getText()));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened

        loginToTestSite(login, password);

        // step 4 - go to New Message page
        listMessagesPage.clickNewMessageTab();

        // step 5 - send new message
        //may be additional check is needed to make sure that input looks like expected
        message = new Message(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));
        createMessagePage.createMessage(message.getHeadline(), message.getText());
        createMessagePage.clickCreateButton();

        //step 6 - assert that Show message page is displayed and message is shown correctly
        assertEquals(showMessagePage.getTextOfMessageHeadline(), message.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), message.getText());

        //step 7 - go to Message List page and assert that the list contains newly created message
        showMessagePage.clickTabToMessagesList();
        goToLastPage();

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getText()));

        //step 8 - assert that Edit Message page is opened and message is displayed correctly
        listMessagesPage.clickLastRowEditButton();
        assertTrue(editMessagePage.getTextOfMessageHeadline().contains(message.getHeadline()));
        assertTrue(editMessagePage.getTextOfMessageBody().contains(message.getText()));

       //step 9 - type in other message headline and text
        Message editedMessage = new Message(messages.getProperty(EDITED_MESSAGE_HEADLINE), messages.getProperty(EDITED_MESSAGE_TEXT));
        editMessagePage.createMessage(editedMessage.getHeadline(),editedMessage.getText());
        assertTrue(editMessagePage.getTextOfMessageHeadline().contains(editedMessage.getHeadline()));
        assertTrue(editMessagePage.getTextOfMessageBody().contains(editedMessage.getText()));

        //step 10 - save changes and make sure new values are shown on Show Message page
        editMessagePage.clickSaveButton();
        assertEquals(showMessagePage.getTextOfMessageHeadline(), editedMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), editedMessage.getText());

        //step 11 - go to Message List and make sure new values for the message are displayed there
        editMessagePage.clickTabToMessagesList();
        goToLastPage();

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(editedMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(editedMessage.getText()));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.clickNewMessageTab();

        //step 5 - sent message
        //may be additional check is needed to make sure that input looks like expected
        message = new Message(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));
        createMessagePage.createMessage(message.getHeadline(), message.getText());
        createMessagePage.clickCreateButton();

        //step 6 - make sure that newly created message is correctly displayed on Show Message page
        assertEquals(showMessagePage.getTextOfMessageHeadline(), message.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), message.getText());

        //step 7 - go to Message List page and make sure that new message is displayed there
        showMessagePage.clickTabToMessagesList();
        goToLastPage();

        //step 8 - delete recently created message and make sure it's not displayed in the list anymore

        String lastMessageDate = listMessagesPage.getLastMessageCreationDate().toString();
        listMessagesPage.clickLastRowDeleteButton();
        goToLastPage();

        assertFalse(listMessagesPage.getTextOfTheLastTableRow().contains(lastMessageDate));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageWithoutSavingTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);

        goToLastPage();

        //remember the last row in the Message List
        String lastTableRowText = listMessagesPage.getTextOfTheLastTableRow();

        //step 4 - go to Create Message page
        listMessagesPage.clickNewMessageTab();

        //step 5 - go to Messages List without saving new message
        message = new Message(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));
        createMessagePage.createMessage(message.getHeadline(), message.getText());

        //step 6 - verify that no new messages were added to the list
        createMessagePage.clickTabToMessagesList();

        goToLastPage();

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(lastTableRowText));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createTwoMessagesTest(String login, String password) {
        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.clickNewMessageTab();

        //step 5 - create message
        Message firstMessage = new Message(messages.getProperty(FIRST_TEST_MESSAGE_HEADLINE), messages.getProperty(FIRST_TEST_MESSAGE_TEXT));
        createMessagePage.createMessage(firstMessage.getHeadline(), firstMessage.getText());
        createMessagePage.clickCreateButton();

        //step 6 - send message
        assertEquals(showMessagePage.getTextOfMessageHeadline(), firstMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), firstMessage.getText());

        //step 7 - open New Message page
        showMessagePage.clickTabToNewMessagePage();
        assertEquals(createMessagePage.checkPageTitle(), messages.getProperty(CREATE_MESSAGE_PAGE_TITLE));

        //step 8 - fill in Message fields
        Message secondMessage = new Message(messages.getProperty(SECOND_TEST_MESSAGE_HEADLINE), messages.getProperty(SECOND_TEST_MESSAGE_TEXT));
        createMessagePage.createMessage(secondMessage.getHeadline(), secondMessage.getText());
        createMessagePage.clickCreateButton();

        //step 9 - click create and verify the message was created with correct data
        assertEquals(showMessagePage.getTextOfMessageHeadline(), secondMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), secondMessage.getText());

        showMessagePage.clickTabToMessagesList();
        goToLastPage();
        System.out.println(listMessagesPage.getLastPaginationButton().getText());

        //step 10 - verify that both messages are in the Message list
        assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(firstMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondMessage.getHeadline()));
    }

    @Test
    @Parameters({"Login", "SecondUserLogin", "Password"})
    public void viewOtherUsersMessagesTest(String login, String secondUserLogin, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.clickNewMessageTab();

        //step5 - send new message
        message = new Message(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));
        createMessagePage.createMessage(message.getHeadline(), message.getText());
        createMessagePage.clickCreateButton();

        //strep6 - verify that correct message is shown on Show page
        assertEquals(showMessagePage.getTextOfMessageHeadline(), message.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(),message.getText());

        //step 7 - Go to Message List and verify that message is shown there
        showMessagePage.clickTabToMessagesList();

        goToLastPage();

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getText()));

        String adminMessage = listMessagesPage.getTextOfTheLastTableRow();

        //step 8 - view created message
        listMessagesPage.clickLastRowViewButton();
        assertEquals(showMessagePage.getTextOfMessageHeadline(), message.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), message.getText());

        //step 9 - return to message list
        showMessagePage.clickTabToMessagesList();
        goToLastPage();

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getText()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(message.getHeadline()));

        //step 10
        listMessagesPage.clickLogoutButton();
        assertTrue(loginPage.checkIfLoginFieldIsPresent());

        //step 11
        loginPage.login(secondUserLogin, password);

        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));
        assertTrue(listMessagesPage.getUserGreeting().getText().contains(messages.getProperty(SECOND_USER_GREETING)));

        //step 12
        listMessagesPage.clickNewMessageTab();

        Message secondUserMessage = new Message(messages.getProperty(SECOND_USER_MESSAGE_HEADLINE), messages.getProperty(SECOND_USER_MESSAGE_TEXT));
        createMessagePage.createMessage(secondUserMessage.getHeadline(), secondUserMessage.getText());
        createMessagePage.clickCreateButton();

        assertEquals(showMessagePage.getMessageHeadline().getText(), secondUserMessage.getHeadline());
        assertEquals(showMessagePage.getMessageText().getText(), secondUserMessage.getText());

        showMessagePage.clickTabToMessagesList();
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));

        goToLastPage();

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getText()));

        listMessagesPage.clickLogoutButton();
        assertTrue(loginPage.getButtonLogin().isDisplayed());

        // step 13 - login as admin user
        loginPage.login(login, password);
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));
        assertEquals(listMessagesPage.checkTextOfUserGreeting(), messages.getProperty(ADMIN_USER_GREETING));

        //step 14 - select checkbox and check that massages of both users are shown
        listMessagesPage.selectAllUsersMessagesCheckbox();
        goToLastPage();

        assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(message.getText()));
        assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(message.getHeadline()));
        assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(messages.getProperty(ADMIN_USER_NAME)));


        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getText()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(messages.getProperty(SECOND_USER_NAME)));

        listMessagesPage.selectAllUsersMessagesCheckbox();
        goToLastPage();

        assertEquals(listMessagesPage.getTextOfTheLastTableRow(),adminMessage);
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getText()));
        assertFalse(listMessagesPage.getTextOfTheLastTableRow().contains(SECOND_USER_NAME));
    }

    private void loginToTestSite(String login, String password) {
        StartPage startPage = new StartPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        startPage.clickOnUserControllerLink();
        loginPage.login(login, password);
    }

    private void goToLastPage() {
        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }
    }
}
