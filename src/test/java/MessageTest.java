import com.qulix.helpers.ResourceFactory;
import com.qulix.message.Message;
import com.qulix.pages.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.testng.Assert.*;

public class MessageTest extends AbstractTest {

    private static Properties messages;
    private StartPage startPage;

    private static final String RESOURCE_PATH = "MessageTest/settings.properties";
    private static final String ADMIN_USER_NAME = "admin.user.name";
    private static final String ADMIN_USER_GREETING = "admin.user.greeting";
    private static final String ADMIN_MESSAGE_HEADLINE = "admin.message.headline";
    private static final String ADMIN_MESSAGE_TEXT = "admin.message.text";
    private static final String SECOND_USER_NAME = "second.user.name";
    private static final String SECOND_USER_GREETING = "second.user.greeting";
    private static final String SECOND_USER_MESSAGE_HEADLINE = "second.user.message.headline";
    private static final String SECOND_USER_MESSAGE_TEXT = "second.user.message.text";
    private static final String EDITED_MESSAGE_HEADLINE = "edited.message.headline";
    private static final String EDITED_MESSAGE_TEXT = "edited.message.text";
    private static final String FIRST_TEST_MESSAGE_HEADLINE = "first.test.message.headline";
    private static final String FIRST_TEST_MESSAGE_TEXT = "first.test.message.text";
    private static final String SECOND_TEST_MESSAGE_HEADLINE = "second.test.message.headline";
    private static final String SECOND_TEST_MESSAGE_TEXT = "second.test.message.text";
    private static final String LIST_MESSAGES_PAGE_TITLE = "list.messages.page.title";
    private static final String CREATE_MESSAGE_PAGE_TITLE = "create.message.page.title";
    private static final String SHOW_MESSAGE_PAGE_TITLE = "show.message.page.title";
    private static final Boolean CLICK_SAVE_BUTTON = Boolean.TRUE;

    private static Message adminMessage;
    private static Message firstMessage;
    private static Message secondMessage;
    private static Message editedMessage;    
    private static Message secondUserMessage;

    @BeforeClass
    public static void initMessages() {
        messages = ResourceFactory.getResources(MessageTest.RESOURCE_PATH);
        adminMessage = new Message(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));
        editedMessage = new Message(messages.getProperty(EDITED_MESSAGE_HEADLINE), messages.getProperty(EDITED_MESSAGE_TEXT));
        firstMessage = new Message(messages.getProperty(FIRST_TEST_MESSAGE_HEADLINE), messages.getProperty(FIRST_TEST_MESSAGE_TEXT));
        secondMessage = new Message(messages.getProperty(SECOND_TEST_MESSAGE_HEADLINE), messages.getProperty(SECOND_TEST_MESSAGE_TEXT));
        secondUserMessage = new Message(messages.getProperty(SECOND_USER_MESSAGE_HEADLINE), messages.getProperty(SECOND_USER_MESSAGE_TEXT));
    }

    @BeforeMethod
    public void setup() {
        startPage = openWebsite();
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertEquals(createMessagePage.checkPageTitle(), messages.getProperty(CREATE_MESSAGE_PAGE_TITLE));

        ShowMessagePage showMessagePage = createMessagePage.createMessage(adminMessage);
        assertEquals(showMessagePage.checkPageTitle(), messages.getProperty(SHOW_MESSAGE_PAGE_TITLE));

        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        System.out.println(listMessagesPage.getTextOfTheLastTableRow());

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getText()));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void showMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        ShowMessagePage showMessagePage = createMessagePage.createMessage(adminMessage);

        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        System.out.println(listMessagesPage.getTextOfTheLastTableRow());
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getText()));

        //step 8 - select and view newly created message
        listMessagesPage.clickLastRowViewButton();
        assertEquals(showMessagePage.getTextOfMessageHeadline(), adminMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), adminMessage.getText());

        //step 9 - go to Messages List and view created message in the table
        showMessagePage.clickTabToMessagesList();
        /*goToLastPage();*/
        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        System.out.println(listMessagesPage.getTextOfTheLastTableRow());
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getText()));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        ShowMessagePage showMessagePage = createMessagePage.createMessage(adminMessage);

        assertEquals(showMessagePage.getTextOfMessageHeadline(), adminMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), adminMessage.getText());

        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getText()));

        EditMessagePage editMessagePage = listMessagesPage.clickLastRowEditButton();

        System.out.println(editMessagePage.getTextOfMessageHeadline());

        assertTrue(editMessagePage.getTextOfMessageHeadline().contains(adminMessage.getHeadline()));
        assertTrue(editMessagePage.getTextOfMessageBody().contains(adminMessage.getText()));

        editMessagePage.editMessage(editedMessage);
        assertTrue(editMessagePage.getTextOfMessageHeadline().contains(editedMessage.getHeadline()));
        assertTrue(editMessagePage.getTextOfMessageBody().contains(editedMessage.getText()));

        //step 10 - save changes and make sure new values are shown on Show Message page
        editMessagePage.submitEditedMessage();
        assertEquals(showMessagePage.getTextOfMessageHeadline(), editedMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), editedMessage.getText());

        //step 11 - go to Message List and make sure new values for the message are displayed there
        editMessagePage.clickTabToMessagesList();
        /*goToLastPage();*/
        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(editedMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(editedMessage.getText()));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        ShowMessagePage showMessagePage = createMessagePage.createMessage(adminMessage);

        //step 6 - make sure that newly created message is correctly displayed on Show Message page
        assertEquals(showMessagePage.getTextOfMessageHeadline(), adminMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), adminMessage.getText());

        //step 7 - go to Message List page and make sure that new message is displayed there
        showMessagePage.clickTabToMessagesList();
        /*goToLastPage();*/
        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        //step 8 - delete recently created message and make sure it's not displayed in the list anymore

        String lastMessageDate = listMessagesPage.getLastMessageCreationDate().toString();
        listMessagesPage.clickLastRowDeleteButton();
        /*goToLastPage();*/
        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertFalse(listMessagesPage.getTextOfTheLastTableRow().contains(lastMessageDate));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageWithoutSavingTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);

        /*goToLastPage();*/
        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        //remember the last row in the Message List
        String lastTableRowText = listMessagesPage.getTextOfTheLastTableRow();

        //step 4 - go to Create Message page
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();

        //step 5 - go to Messages List without saving new message
        createMessagePage.createMessage(adminMessage, !CLICK_SAVE_BUTTON);

        //step 6 - verify that no new messages were added to the list
        createMessagePage.clickTabToMessagesList();

        /*goToLastPage();*/
        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(lastTableRowText));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createTwoMessagesTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstMessage);

        //step 6 - send message
        assertEquals(showMessagePage.getTextOfMessageHeadline(), firstMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), firstMessage.getText());

        //step 7 - open New Message page
        showMessagePage.clickTabToNewMessagePage();
        assertEquals(createMessagePage.checkPageTitle(), messages.getProperty(CREATE_MESSAGE_PAGE_TITLE));

        //step 8 - fill in Message fields
        createMessagePage.createMessage(secondMessage);

        //step 9 - click create and verify the message was created with correct data
        assertEquals(showMessagePage.getTextOfMessageHeadline(), secondMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), secondMessage.getText());

        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        System.out.println(listMessagesPage.getTextOfBeforeTheLastTableRow());
        System.out.println(listMessagesPage.getTextOfTheLastTableRow());

        //step 10 - verify that both messages are in the Message list
        assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(firstMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondMessage.getHeadline()));
    }

    @Test
    @Parameters({"Login", "SecondUserLogin", "Password"})
    public void viewOtherUsersMessagesTest(String login, String secondUserLogin, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        ShowMessagePage showMessagePage = createMessagePage.createMessage(adminMessage);

        //strep6 - verify that correct message is shown on Show page
        assertEquals(showMessagePage.getTextOfMessageHeadline(), adminMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(),adminMessage.getText());

        //step 7 - Go to Message List and verify that message is shown there
        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getText()));

        String adminMessageText = listMessagesPage.getTextOfTheLastTableRow();

        //step 8 - view created message
        listMessagesPage.clickLastRowViewButton();
        assertEquals(showMessagePage.getTextOfMessageHeadline(), adminMessage.getHeadline());
        assertEquals(showMessagePage.getTextOfMessageBody(), adminMessage.getText());

        //step 9 - return to message list
        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getText()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(adminMessage.getHeadline()));

        //step 10
        LoginPage loginPage = listMessagesPage.clickLogoutButton();
        assertTrue(loginPage.checkIfLoginFieldIsPresent());

        //step 11
        loginPage.login(secondUserLogin, password);

        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));
        assertTrue(listMessagesPage.getUserGreeting().getText().contains(messages.getProperty(SECOND_USER_GREETING)));

        //step 12
        listMessagesPage.clickNewMessageTab().createMessage(secondUserMessage);

        assertEquals(showMessagePage.getMessageHeadline().getText(), secondUserMessage.getHeadline());
        assertEquals(showMessagePage.getMessageText().getText(), secondUserMessage.getText());

        showMessagePage.clickTabToMessagesList();
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        System.out.println(listMessagesPage.getTextOfTheLastTableRow());

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getText()));

        listMessagesPage.clickLogoutButton();
        assertTrue(loginPage.getButtonLogin().isDisplayed());

        loginPage.login(login, password);
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));
        assertEquals(listMessagesPage.checkTextOfUserGreeting(), messages.getProperty(ADMIN_USER_GREETING));

        //step 14 - select checkbox and check that massages of both users are shown
        listMessagesPage.selectAllUsersMessagesCheckbox();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(adminMessage.getText()));
        assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(adminMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(messages.getProperty(ADMIN_USER_NAME)));

        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getText()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(messages.getProperty(SECOND_USER_NAME)));

        listMessagesPage.selectAllUsersMessagesCheckbox();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertEquals(listMessagesPage.getTextOfTheLastTableRow(),adminMessageText);
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getHeadline()));
        assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(secondUserMessage.getText()));
        assertFalse(listMessagesPage.getTextOfTheLastTableRow().contains(SECOND_USER_NAME));
    }
}
