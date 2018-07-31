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
        assertTrue(listMessagesPage.verifyMessageListPageTitle()); //TODO Не очень удобно. В случае ошибки у тебя assert будет с expected true but found false. Чтобы понять, что произошло надо будет смотреть stacktrace и искать нужную строку

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        Message message = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);
        assertTrue(showMessagePage.verifyShowMessagePageTitle());

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickAllUsersMessagesCheckbox(); // to delete!

        assertTrue(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void showMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);//TODO Вот это у тебя 10 раз будет. Не проще ли создать метод login(String, String)?
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        Message message = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);
        assertTrue(showMessagePage.verifyShowMessagePageTitle());

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickAllUsersMessagesCheckbox(); // to delete!

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        listMessagesPage.clickViewButtonInCertainRow(message);
        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        Message message = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickAllUsersMessagesCheckbox(); // to delete!

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        EditMessagePage editMessagePage = listMessagesPage.clickEditButtonInCertainRow(message);

        assertTrue(editMessagePage.verifyEditMessagePageTitle());
        assertTrue(editMessagePage.checkTheMessageOpened(message));

        Message editedMessage = Message.createUniqueMessage();

        editMessagePage.populateMessageFields(editedMessage);
        editMessagePage.submitEditedMessage();

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(editedMessage));

        editMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(editedMessage));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        Message message = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickAllUsersMessagesCheckbox(); // to delete!

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        listMessagesPage.clickDeleteButtonInCertainRow(message);

        assertFalse(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageWithoutSavingTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        Message message = Message.createUniqueMessage();

        createMessagePage.createMessage(message, !CLICK_SAVE_BUTTON);
        createMessagePage.clickTabToMessagesList();
        listMessagesPage.clickAllUsersMessagesCheckbox(); // to delete!

        assertFalse(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createTwoMessagesTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        Message firstMessage = Message.createUniqueMessage();

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstMessage);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(firstMessage));

        showMessagePage.clickTabToNewMessagePage();
        listMessagesPage.clickAllUsersMessagesCheckbox(); // to delete!

        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        Message secondMessage = Message.createUniqueMessage();

        createMessagePage.createMessage(secondMessage);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(secondMessage));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(firstMessage));
        assertTrue(listMessagesPage.checkIfMessageExists(secondMessage));
    }

    @Test
    @Parameters({"Login", "SecondUserLogin", "Password"})
    public void viewOtherUsersMessagesTest(String login, String secondUserLogin, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        assertTrue(listMessagesPage.verifyMessageListPageTitle());

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertTrue(createMessagePage.verifyCreateMessagePageTitle());

        Message firstUserMessage = Message.createUniqueMessageWithUserName(ADMIN_USER_NAME);//TODO Поучему ADMIN_USER_NAME? А если firstLogin другого пользователя передам?

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstUserMessage);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        listMessagesPage.clickViewButtonInCertainRow(firstUserMessage);

        assertTrue(showMessagePage.verifyShowMessagePageTitle());
        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.clickAllUsersMessagesCheckbox(); // to delete!

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        LoginPage loginPage = listMessagesPage.clickLogoutButton();

        assertTrue(loginPage.checkIfLoginFieldIsPresent());

        loginPage.login(secondUserLogin, password);

        assertTrue(listMessagesPage.verifyMessageListPageTitle());
        assertTrue(listMessagesPage.verifyUserGreeting(SECOND_USER_GREETING));

        Message secondUserMessage = Message.createUniqueMessageWithUserName(SECOND_USER_NAME);

        listMessagesPage.clickNewMessageTab().createMessage(secondUserMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(secondUserMessage));

        showMessagePage.clickTabToMessagesList();

        assertTrue(listMessagesPage.checkIfMessageExists(secondUserMessage));

        listMessagesPage.clickLogoutButton();

        assertTrue(loginPage.checkIfLoginButtonIsPresent());

        loginPage.login(login, password);
        assertTrue(listMessagesPage.verifyUserGreeting(ADMIN_USER_GREETING));

        listMessagesPage.clickAllUsersMessagesCheckbox();

        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExists(firstUserMessage, CONSIDER_USER));
        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExists(secondUserMessage, CONSIDER_USER));

        listMessagesPage.clickAllUsersMessagesCheckbox();

        assertTrue(listMessagesPage.checkIfMessageOfCertainUserExists(firstUserMessage, CONSIDER_USER));
        assertFalse(listMessagesPage.checkIfMessageOfCertainUserExists(secondUserMessage, CONSIDER_USER));
    }

}
