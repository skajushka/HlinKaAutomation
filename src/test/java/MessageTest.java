import com.qulix.helpers.ResourceFactory;
import com.qulix.pages.*;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Properties;

import static org.testng.Assert.*;

public class MessageTest extends AbstractTest {

    Properties messages;

    private static String RESOURCE_PATH = "MessageTest/settings.properties";
    private static String ADMIN_USER_NAME = "admin.user.name";
    private static String ADMIN_USER_GREETING = "admin.user.greeting";
    private static String ADMIN_MESSAGE_HEADLINE = "admin.message.headline";
    private static String ADMIN_MESSAGE_TEXT = "admin.message.text";
    private static String SECOND_USER_LOGIN = "second.user.login";
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
        assertTrue(listMessagesPage.getPageTitle().isDisplayed());

        // step 4 - open Create message page
        listMessagesPage.getNewMessageTab().click();
        assertTrue(createMessagePage.getButtonCreate().isDisplayed());

        // step 5 - send message
        //may be additional check is needed to make sure that input looks like expected
        createMessagePage.createMessage(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));

        //step 6 - verify that Show message page is opened
        assertTrue(showMessagePage.getShowMessageNote().isDisplayed());

        //step 7 - open Message List page and verify that new message is presented in the list
        showMessagePage.getTabToMessagesList().click();

        goToLastPage();

        //may be we need to check the match more precisely? E.x., using assertEquals. That is a question for the most parts of asserts.
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void showMessageTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened

        loginToTestSite(login, password);

        // step 4 - go to New Message page
        listMessagesPage.getNewMessageTab().click();

        // step 5 - send new message
        //may be additional check is needed to make sure that input looks like expected
        createMessagePage.createMessage(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));

        // step 6 - assert if message shown is the same that was created
        assertTrue(showMessagePage.getMessageHeadline().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(showMessagePage.getMessageText().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

        //step 7 - find newly created message in Message List
        showMessagePage.getTabToMessagesList().click();
        goToLastPage();

        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

        //step 8 - select and view newly created message
        listMessagesPage.getLastRowViewButton().click();
        assertTrue(showMessagePage.getMessageHeadline().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(showMessagePage.getMessageText().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

        //step 9 - go to Messages List and view created message in the table
        showMessagePage.getTabToMessagesList().click();
        goToLastPage();

        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened

        loginToTestSite(login, password);

        // step 4 - go to New Message page
        listMessagesPage.getNewMessageTab().click();

        // step 5 - send new message
        //may be additional check is needed to make sure that input looks like expected
        createMessagePage.createMessage(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));

        //step 6 - assert that Show message page is displayed and message is shown correctly
        assertTrue(showMessagePage.getMessageHeadline().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(showMessagePage.getMessageText().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

        //step 7 - go to Message List page and assert that the list contains newly created message
        showMessagePage.getTabToMessagesList().click();
        goToLastPage();

        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

        //step 8 - assert that Edit Message page is opened and message is displayed correctly
        listMessagesPage.getLastRowEditButton().click();
        assertTrue(editMessagePage.getMessageHeadline().getAttribute("value").contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(editMessagePage.getMessageText().getAttribute("value").contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

       //step 9 - type in other message headline and text
        editMessagePage.changeMessageHeadlineAndText(messages.getProperty(EDITED_MESSAGE_HEADLINE), messages.getProperty(EDITED_MESSAGE_TEXT));
        assertTrue(editMessagePage.getMessageHeadline().getAttribute("value").contains(messages.getProperty(EDITED_MESSAGE_HEADLINE)));
        assertTrue(editMessagePage.getMessageText().getAttribute("value").contains(messages.getProperty(EDITED_MESSAGE_TEXT)));

        //step 10 - save changes and make sure new values are shown on Show Message page
        editMessagePage.getSaveButton().click();
        assertTrue(showMessagePage.getMessageHeadline().getText().contains(messages.getProperty(EDITED_MESSAGE_HEADLINE)));
        assertTrue(showMessagePage.getMessageText().getText().contains(messages.getProperty(EDITED_MESSAGE_TEXT)));

        //step 11 - go to Message List and make sure new values for the message are displayed there
        editMessagePage.getTabToMessagesList().click();
        goToLastPage();

        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(EDITED_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(EDITED_MESSAGE_TEXT)));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.getNewMessageTab().click();

        //step 5 - sent message
        //may be additional check is needed to make sure that input looks like expected
        createMessagePage.createMessage(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));

        //step 6 - make sure that newly created message is correctly displayed on Show Message page
        assertTrue(showMessagePage.getMessageHeadline().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(showMessagePage.getMessageText().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

        //step 7 - go to Message List page and make sure that new message is displayed there
        showMessagePage.getTabToMessagesList().click();
        goToLastPage();

        //step 8 - delete recently created message and make sure it's not displayed in the list anymore

        String lastMessageDate = listMessagesPage.getLastMessageCreationDate().toString();
        listMessagesPage.getLastRowDeleteButton().click();
        goToLastPage();

        assertFalse(listMessagesPage.getLastTableRow().getText().contains(lastMessageDate));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageWithoutSavingTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);

        goToLastPage();

        //remember the last row in the Message List
        String lastTableRowText = listMessagesPage.getLastTableRow().getText();

        //step 4 - go to Create Message page
        listMessagesPage.getNewMessageTab().click();

        //step 5 - go to Messages List without saving new message
        createMessagePage.fillInMessageFields(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));

        //step 6 - verify that no new messages were added to the list
        createMessagePage.getTabToMessagesList().click();

        goToLastPage();

        assertTrue(listMessagesPage.getLastTableRow().getText().contains(lastTableRowText));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createTwoMessagesTest(String login, String password) {
        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.getNewMessageTab().click();

        //step 5 - create message
        createMessagePage.createMessage(messages.getProperty(FIRST_TEST_MESSAGE_HEADLINE), messages.getProperty(FIRST_TEST_MESSAGE_TEXT));

        //step 6 - send message
        assertEquals(showMessagePage.getMessageHeadline().getText(), messages.getProperty(FIRST_TEST_MESSAGE_HEADLINE));
        assertEquals(showMessagePage.getMessageText().getText(), messages.getProperty(FIRST_TEST_MESSAGE_TEXT));

        //step 7 - open New Message page
        showMessagePage.getTabToNewMessagePage().click();
        assertTrue(createMessagePage.getButtonCreate().isDisplayed());

        //step 8 - fill in Message fields
        createMessagePage.createMessage(messages.getProperty(SECOND_TEST_MESSAGE_HEADLINE), messages.getProperty(SECOND_TEST_MESSAGE_TEXT));

        //step 9 - click create and verify the message was created with correct data
        assertEquals(showMessagePage.getMessageHeadline().getText(), messages.getProperty(SECOND_TEST_MESSAGE_HEADLINE));
        assertEquals(showMessagePage.getMessageText().getText(), messages.getProperty(SECOND_TEST_MESSAGE_TEXT));

        showMessagePage.getTabToMessagesList().click();
        goToLastPage();

        //step 10 - verify that both messages are in the Message list
        assertTrue(getBeforeTheLastTableRow().getText().contains(messages.getProperty(FIRST_TEST_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(SECOND_TEST_MESSAGE_HEADLINE)));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void viewOtherUsersMessagesTest(String login, String password) {

        //steps 1 and 2 are tested in LoginTest
        //step 3 - login and make sure that Message list page is opened; remember the creation date of the last message
        loginToTestSite(login, password);

        //step 4 - go to Create Message page
        listMessagesPage.getNewMessageTab().click();

        //step5 - send new message
        createMessagePage.createMessage(messages.getProperty(ADMIN_MESSAGE_HEADLINE), messages.getProperty(ADMIN_MESSAGE_TEXT));

        //strep6 - verify that correct message is shown on Show page
        assertEquals(showMessagePage.getMessageHeadline().getText(), messages.getProperty(ADMIN_MESSAGE_HEADLINE));
        assertEquals(showMessagePage.getMessageText().getText(), messages.getProperty(ADMIN_MESSAGE_TEXT));

        //step 7 - Go to Message List and verify that message is shown there
        showMessagePage.getTabToMessagesList().click();

        goToLastPage();

        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));
        String adminMessage = listMessagesPage.getLastTableRow().getText();

        //step 8 - view created message
        listMessagesPage.getLastRowViewButton().click();
        assertTrue(showMessagePage.getMessageHeadline().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(showMessagePage.getMessageText().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

        //step 9 - return to message list
        showMessagePage.getTabToMessagesList().click();
        goToLastPage();

        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));

        //step 10
        listMessagesPage.getLogoutButton().click();
        assertTrue(loginPage.getButtonLogin().isDisplayed());

        //step 11
        loginPage.login(messages.getProperty(SECOND_USER_LOGIN), password);

        assertTrue(listMessagesPage.getPageTitle().isDisplayed());
        assertTrue(listMessagesPage.getUserGreeting().getText().contains(messages.getProperty(SECOND_USER_GREETING)));

        //step 12
        listMessagesPage.getNewMessageTab().click();
        createMessagePage.createMessage(messages.getProperty(SECOND_USER_MESSAGE_HEADLINE), messages.getProperty(SECOND_USER_MESSAGE_TEXT));

        assertEquals(showMessagePage.getMessageHeadline().getText(), messages.getProperty(SECOND_USER_MESSAGE_HEADLINE));
        assertEquals(showMessagePage.getMessageText().getText(), messages.getProperty(SECOND_USER_MESSAGE_TEXT));

        showMessagePage.getTabToMessagesList().click();
        assertTrue(listMessagesPage.getPageTitle().isDisplayed());

        goToLastPage();

        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(SECOND_USER_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(SECOND_USER_MESSAGE_TEXT)));

        listMessagesPage.getLogoutButton().click();
        assertTrue(loginPage.getButtonLogin().isDisplayed());

        // step 13 - login as admin user
        loginPage.login(login, password);
        assertTrue(listMessagesPage.getPageTitle().isDisplayed());
        assertTrue(listMessagesPage.getUserGreeting().getText().contains(messages.getProperty(ADMIN_USER_GREETING)));

        //step 14 - select checkbox and check that massages of both users are shown
        listMessagesPage.getAllUsersMessagesCheckbox().click();
        goToLastPage();

        WebElement beforeTheLastTableRow = getBeforeTheLastTableRow();

        assertTrue(beforeTheLastTableRow.getText().contains(messages.getProperty(ADMIN_MESSAGE_TEXT)));
        assertTrue(beforeTheLastTableRow.getText().contains(messages.getProperty(ADMIN_MESSAGE_HEADLINE)));
        assertTrue(beforeTheLastTableRow.getText().contains(messages.getProperty(ADMIN_USER_NAME)));


        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(SECOND_USER_MESSAGE_TEXT)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(SECOND_USER_MESSAGE_HEADLINE)));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(messages.getProperty(SECOND_USER_NAME)));

        listMessagesPage.getAllUsersMessagesCheckbox().click();
        goToLastPage();

        assertEquals(listMessagesPage.getLastTableRow().getText(),adminMessage);
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(SECOND_USER_MESSAGE_TEXT));
        assertTrue(listMessagesPage.getLastTableRow().getText().contains(SECOND_USER_MESSAGE_HEADLINE));
        assertFalse(listMessagesPage.getLastTableRow().getText().contains(SECOND_USER_NAME));
    }

    private void loginToTestSite(String login, String password) {
        StartPage startPage = new StartPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        startPage.getUserControllerLink().click();
        loginPage.login(login, password);
    }

    private void goToLastPage() {
        try {
            listMessagesPage.getLastPage().click();
        } catch (NullPointerException e) {
            //do nothing
        }
    }

    private WebElement getBeforeTheLastTableRow() {
        try {
            return listMessagesPage.getBeforeTheLastTableRow();
        } catch(NoSuchElementException e) {
            listMessagesPage.getPreLastPage();
            return listMessagesPage.getLastTableRow();
        }
    }
}
