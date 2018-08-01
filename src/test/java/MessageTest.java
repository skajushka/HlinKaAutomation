import com.qulix.message.Message;
import com.qulix.pages.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MessageTest extends AbstractTest {

    private StartPage startPage;

    private static final boolean CONSIDER_USER = Boolean.TRUE;

    @BeforeMethod
    public void setup() {
        startPage = openWebsite();
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = loginToTestSite(login, password);
        listMessagesPage.verifyMessageListPageTitle();

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        createMessagePage.verifyCreateMessagePageTitle();

        Message message = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);
        showMessagePage.verifyShowMessagePageTitle();

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void showMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = loginToTestSite(login, password);
        listMessagesPage.verifyMessageListPageTitle();

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        createMessagePage.verifyCreateMessagePageTitle();

        Message message = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);
        showMessagePage.verifyShowMessagePageTitle();
        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        listMessagesPage.clickViewButtonInCertainRow(message);
        showMessagePage.verifyShowMessagePageTitle();
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = loginToTestSite(login, password);
        listMessagesPage.verifyMessageListPageTitle();

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        createMessagePage.verifyCreateMessagePageTitle();

        Message message = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        showMessagePage.verifyShowMessagePageTitle();
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        EditMessagePage editMessagePage = listMessagesPage.clickEditButtonInCertainRow(message);

        assertTrue(editMessagePage.verifyEditMessagePageTitle());
        assertTrue(editMessagePage.checkTheMessageOpened(message));

        Message editedMessage = Message.createUniqueMessage();

        editMessagePage.populateMessageFields(editedMessage);
        editMessagePage.submitEditedMessage();

        showMessagePage.verifyShowMessagePageTitle();
        assertTrue(showMessagePage.checkTheMessageViewed(editedMessage));

        editMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(editedMessage));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = loginToTestSite(login, password);
        listMessagesPage.verifyMessageListPageTitle();

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        createMessagePage.verifyCreateMessagePageTitle();

        Message message = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        showMessagePage.verifyShowMessagePageTitle();
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        listMessagesPage.clickDeleteButtonInCertainRow(message);

        assertFalse(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageWithoutSavingTest(String login, String password) {

        ListMessagesPage listMessagesPage = loginToTestSite(login, password);
        listMessagesPage.verifyMessageListPageTitle();

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        createMessagePage.verifyCreateMessagePageTitle();

        Message message = Message.createUniqueMessage();

        createMessagePage.populateMessageFields(message);
        createMessagePage.clickTabToMessagesList();

        assertFalse(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createTwoMessagesTest(String login, String password) {

        ListMessagesPage listMessagesPage = loginToTestSite(login, password);
        listMessagesPage.verifyMessageListPageTitle();

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        createMessagePage.verifyCreateMessagePageTitle();

        Message firstMessage = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstMessage);

        showMessagePage.verifyShowMessagePageTitle();
        assertTrue(showMessagePage.checkTheMessageViewed(firstMessage));

        showMessagePage.clickTabToNewMessagePage();

        createMessagePage.verifyCreateMessagePageTitle();

        Message secondMessage = Message.createUniqueMessage();

        createMessagePage.createMessage(secondMessage);

        showMessagePage.verifyShowMessagePageTitle();
        assertTrue(showMessagePage.checkTheMessageViewed(secondMessage));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(firstMessage));
        assertTrue(listMessagesPage.checkIfMessageExists(secondMessage));
    }

    @Test
    @Parameters({"Login", "Password", "UserName", "SecondUserLogin", "SecondUserName"})
    public void viewOtherUsersMessagesTest(String login, String password, String userName, String secondUserLogin, String secondUserName) {

        ListMessagesPage listMessagesPage = loginToTestSite(login, password);
        listMessagesPage.verifyMessageListPageTitle();

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        createMessagePage.verifyCreateMessagePageTitle();

        Message firstUserMessage = Message.createUniqueMessageWithUserName(userName);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstUserMessage);

        showMessagePage.verifyShowMessagePageTitle();
        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        listMessagesPage.clickViewButtonInCertainRow(firstUserMessage);

        showMessagePage.verifyShowMessagePageTitle();
        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        LoginPage loginPage = listMessagesPage.clickLogoutButton();

        assertTrue(loginPage.checkIfLoginFieldIsPresent());

        loginPage.login(secondUserLogin, password);

        listMessagesPage.verifyMessageListPageTitle();
        listMessagesPage.verifyUserGreeting(secondUserName);

        Message secondUserMessage = Message.createUniqueMessageWithUserName(secondUserName);

        listMessagesPage.clickNewMessageTab().createMessage(secondUserMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(secondUserMessage));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(secondUserMessage));

        listMessagesPage.clickLogoutButton();
        loginPage.verifyLoginPageTitle();

        loginPage.login(login, password);
        listMessagesPage.verifyUserGreeting(userName);
        listMessagesPage.clickAllUsersMessagesCheckbox();

        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExists(firstUserMessage, CONSIDER_USER));
        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExists(secondUserMessage, CONSIDER_USER));

        listMessagesPage.clickAllUsersMessagesCheckbox();

        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExists(firstUserMessage, CONSIDER_USER));
        assertFalse(listMessagesPage.checkIfMessageOfCertainUserExists(secondUserMessage, CONSIDER_USER));
    }

    private ListMessagesPage loginToTestSite(String login, String password) {
        return startPage.clickOnUserControllerLink().login(login, password);
    }
}
