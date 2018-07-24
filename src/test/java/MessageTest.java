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
    private static final String SECOND_USER_NAME = "second.user.name";
    private static final String SECOND_USER_GREETING = "second.user.greeting";
    private static final Boolean CLICK_SAVE_BUTTON = Boolean.TRUE;

    @BeforeClass
    public static void initMessages() {
        messages = ResourceFactory.getResources(MessageTest.RESOURCE_PATH);
    }

    @BeforeMethod
    public void setup() {
        startPage = openWebsite();
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);
        assertTrue(showMessagePage.verifyShowMessagePageTitle());

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void showMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        listMessagesPage.clickViewButtonInCertainRow(message);
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        EditMessagePage editMessagePage = listMessagesPage.clickEditButtonInCertainRow(message);

        assertTrue(editMessagePage.checkTheMessageOpened(message));

        String editedHeadline = createMessagePage.generateUniqueString();
        String editedText = createMessagePage.generateUniqueString();

        Message editedMessage = new Message(editedHeadline, editedText);

        editMessagePage.populateMessageFields(editedMessage);
        editMessagePage.submitEditedMessage();

        assertTrue(showMessagePage.checkTheMessageViewed(editedMessage));

        editMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(editedMessage));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        listMessagesPage.clickDeleteButtonInCertainRow(message);
        listMessagesPage.clickLastPaginationButton();

        assertFalse(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageWithoutSavingTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        createMessagePage.createMessage(message, !CLICK_SAVE_BUTTON);
        createMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertFalse(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createTwoMessagesTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message firstMessage = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(firstMessage));

        showMessagePage.clickTabToNewMessagePage();

        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String secondHeadline = createMessagePage.generateUniqueString();
        String secondText = createMessagePage.generateUniqueString();

        Message secondMessage = new Message(secondHeadline, secondText);

        createMessagePage.createMessage(secondMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(secondMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExistsOnThePage(firstMessage));
        assertTrue(listMessagesPage.checkIfMessageExistsOnThePage(secondMessage));
    }

    @Test
    @Parameters({"Login", "SecondUserLogin", "Password"})
    public void viewOtherUsersMessagesTest(String login, String secondUserLogin, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();
        String firstUserName = messages.getProperty(ADMIN_USER_NAME);

        Message firstUserMessage = new Message(headline, text, firstUserName);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstUserMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        listMessagesPage.clickViewButtonInCertainRow(firstUserMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        LoginPage loginPage = listMessagesPage.clickLogoutButton();

        assertTrue(loginPage.checkIfLoginFieldIsPresent());

        loginPage.login(secondUserLogin, password);

        assertTrue(listMessagesPage.verifyMessageListPageTitle());
        assertTrue(listMessagesPage.verifyUserGreeting(messages.getProperty(SECOND_USER_GREETING)));

        String secondHeadline = createMessagePage.generateUniqueString();
        String secondText = createMessagePage.generateUniqueString();
        String secondUserName = messages.getProperty(SECOND_USER_NAME);

        Message secondUserMessage = new Message(secondHeadline, secondText, secondUserName);

        listMessagesPage.clickNewMessageTab().createMessage(secondUserMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(secondUserMessage));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(secondUserMessage));

        listMessagesPage.clickLogoutButton();

        assertTrue(loginPage.checkIfLoginButtonIsPresent());

        loginPage.login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());
        assertTrue(listMessagesPage.verifyUserGreeting(messages.getProperty(ADMIN_USER_GREETING)));

        listMessagesPage.clickAllUsersMessagesCheckbox();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExistsOnThePage(firstUserMessage));
        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExistsOnThePage(secondUserMessage));

        listMessagesPage.clickAllUsersMessagesCheckbox();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExistsOnThePage(firstUserMessage));
        assertFalse(listMessagesPage.checkIfMessageOfCertainUserExistsOnThePage(secondUserMessage));
    }
}
