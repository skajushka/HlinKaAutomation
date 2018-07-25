import com.qulix.message.Message;
import com.qulix.pages.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MessageTest extends AbstractTest {

    private StartPage startPage;

    private static final String ADMIN_USER_NAME = "Administrator";
    private static final String ADMIN_USER_GREETING = "Hello Administrator!";
    private static final String SECOND_USER_NAME = "John Doe";
    private static final String SECOND_USER_GREETING = "Hello John Doe!";
    private static final Boolean CLICK_SAVE_BUTTON = Boolean.TRUE;
    private static final Boolean CONSIDER_USER = Boolean.TRUE;


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

        listMessagesPage.clickViewButtonInCertainRow(message);
        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        EditMessagePage editMessagePage = listMessagesPage.clickEditButtonInCertainRow(message);

        assertTrue(editMessagePage.verifyEditMessagePageTitle());
        assertTrue(editMessagePage.checkTheMessageOpened(message));

        String editedHeadline = createMessagePage.generateUniqueString();
        String editedText = createMessagePage.generateUniqueString();

        Message editedMessage = new Message(editedHeadline, editedText);

        editMessagePage.populateMessageFields(editedMessage);
        editMessagePage.submitEditedMessage();

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(editedMessage));

        editMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(editedMessage));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
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
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

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
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message firstMessage = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstMessage);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(firstMessage));

        showMessagePage.clickTabToNewMessagePage();

        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String secondHeadline = createMessagePage.generateUniqueString();
        String secondText = createMessagePage.generateUniqueString();

        Message secondMessage = new Message(secondHeadline, secondText);

        createMessagePage.createMessage(secondMessage);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
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
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message firstUserMessage = new Message(headline, text, ADMIN_USER_NAME);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstUserMessage);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        listMessagesPage.clickViewButtonInCertainRow(firstUserMessage);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        LoginPage loginPage = listMessagesPage.clickLogoutButton();

        assertTrue(loginPage.checkIfLoginFieldIsPresent());

        loginPage.login(secondUserLogin, password);

        assertTrue(listMessagesPage.verifyMessageListPageTitle());
        assertTrue(listMessagesPage.verifyUserGreeting(SECOND_USER_GREETING));

        String secondHeadline = createMessagePage.generateUniqueString();
        String secondText = createMessagePage.generateUniqueString();

        Message secondUserMessage = new Message(secondHeadline, secondText, SECOND_USER_NAME);

        listMessagesPage.clickNewMessageTab().createMessage(secondUserMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(secondUserMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageExists(secondUserMessage));

        listMessagesPage.clickLogoutButton();

        assertTrue(loginPage.checkIfLoginButtonIsPresent());

        loginPage.login(login, password);
        assertTrue(listMessagesPage.verifyUserGreeting(ADMIN_USER_GREETING));

        listMessagesPage.clickAllUsersMessagesCheckbox();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExistsOnThePage(firstUserMessage, CONSIDER_USER));
        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExistsOnThePage(secondUserMessage, CONSIDER_USER));

        listMessagesPage.clickAllUsersMessagesCheckbox();
        listMessagesPage.clickLastPaginationButton();

        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExistsOnThePage(firstUserMessage, CONSIDER_USER));
        assertFalse(listMessagesPage.checkIfMessageOfCertainUserExistsOnThePage(secondUserMessage, CONSIDER_USER));
    }
}
