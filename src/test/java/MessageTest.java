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
    private static final String LIST_MESSAGES_PAGE_TITLE = "list.messages.page.title";
    private static final String CREATE_MESSAGE_PAGE_TITLE = "create.message.page.title";
    private static final String SHOW_MESSAGE_PAGE_TITLE = "show.message.page.title";
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
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();
        assertEquals(createMessagePage.checkPageTitle(), messages.getProperty(CREATE_MESSAGE_PAGE_TITLE));

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();
        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertEquals(showMessagePage.checkPageTitle(), messages.getProperty(SHOW_MESSAGE_PAGE_TITLE));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.selectAllUsersMessagesCheckbox(); //row to delete!

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
            //todo откуда возьмется NPE? почему do nothing?
            //todo Это ко всем таким кейсам ниже
        }
        //TODO Почему вы все считаете, что проверять надо последнюю строку? В тексте кейса что-то говорится про последнюю строку?
        //TODO Это применимо ко всем тестам ниже
        //TODO Реализация должна быть вида: assertTrue(listMessagesPage.messageExists(message))
        //TODO listMessagesPage.viewMessage(message)
        //TODO listMessagesPage.editMessage(message)
        //TODO listMessagesPage.editMessage(message)
        //TODO Никаких привязок к порядковому номеру

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
        listMessagesPage.selectAllUsersMessagesCheckbox(); //row to delete!

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        listMessagesPage.clickLastRowViewButton();
        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

       assertTrue(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void editMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();
        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.selectAllUsersMessagesCheckbox(); //row to delete!

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.checkIfMessageExists(message));

        EditMessagePage editMessagePage = listMessagesPage.clickLastRowEditButton();

        assertTrue(editMessagePage.checkTheMessageViewed(message));

        String editedHeadline = createMessagePage.generateUniqueString();
        String editedText = createMessagePage.generateUniqueString();
        Message editedMessage = new Message(editedHeadline, editedText);

        editMessagePage.populateMessageFields(editedMessage);
        editMessagePage.submitEditedMessage();

        assertTrue(showMessagePage.checkTheMessageViewed(editedMessage));

        editMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.checkIfMessageExists(editedMessage));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void deleteMessageTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(message);

        assertTrue(showMessagePage.checkTheMessageViewed(message));

        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        listMessagesPage.clickLastRowDeleteButton();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertFalse(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createMessageWithoutSavingTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);

        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message message = new Message(headline, text);

        createMessagePage.createMessage(message, !CLICK_SAVE_BUTTON);
        createMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertFalse(listMessagesPage.checkIfMessageExists(message));
    }

    @Test
    @Parameters({"Login", "Password"})
    public void createTwoMessagesTest(String login, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message firstMessage = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(firstMessage));

        showMessagePage.clickTabToNewMessagePage();
        assertEquals(createMessagePage.checkPageTitle(), messages.getProperty(CREATE_MESSAGE_PAGE_TITLE));

        String secondHeadline = createMessagePage.generateUniqueString();
        String secondText = createMessagePage.generateUniqueString();

        Message secondMessage = new Message(secondHeadline, secondText);
        createMessagePage.createMessage(secondMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(secondMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.selectAllUsersMessagesCheckbox(); //row to delete!

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.checkIfMessageExists(firstMessage));
        assertTrue(listMessagesPage.checkIfMessageExists(secondMessage));
    }

    @Test
    @Parameters({"Login", "SecondUserLogin", "Password"})
    public void viewOtherUsersMessagesTest(String login, String secondUserLogin, String password) {

        ListMessagesPage listMessagesPage = startPage.clickOnUserControllerLink().login(login, password);
        CreateMessagePage createMessagePage = listMessagesPage.clickNewMessageTab();

        String headline = createMessagePage.generateUniqueString();
        String text = createMessagePage.generateUniqueString();

        Message firstUserMessage = new Message(headline, text);

        ShowMessagePage showMessagePage = createMessagePage.createMessage(firstUserMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();
        listMessagesPage.selectAllUsersMessagesCheckbox(); //row to delete!

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        listMessagesPage.clickLastRowViewButton();
        assertTrue(showMessagePage.checkTheMessageViewed(firstUserMessage));

        showMessagePage.clickTabToMessagesList();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));

        LoginPage loginPage = listMessagesPage.clickLogoutButton();
        assertTrue(loginPage.checkIfLoginFieldIsPresent());

        loginPage.login(secondUserLogin, password);

        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));
        assertTrue(listMessagesPage.checkTextOfUserGreeting().contains(messages.getProperty(SECOND_USER_GREETING)));

        String secondHeadline = createMessagePage.generateUniqueString();
        String secondText = createMessagePage.generateUniqueString();

        Message secondUserMessage = new Message(secondHeadline, secondText);

        listMessagesPage.clickNewMessageTab().createMessage(secondUserMessage);

        assertTrue(showMessagePage.checkTheMessageViewed(secondUserMessage));

        showMessagePage.clickTabToMessagesList();
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.checkIfMessageExists(secondUserMessage));

        listMessagesPage.clickLogoutButton();
        assertTrue(loginPage.checkIfLoginButtonIsPresent());

        loginPage.login(login, password);
        assertEquals(listMessagesPage.checkPageTitle(), messages.getProperty(LIST_MESSAGES_PAGE_TITLE));
        assertEquals(listMessagesPage.checkTextOfUserGreeting(), messages.getProperty(ADMIN_USER_GREETING));

        listMessagesPage.selectAllUsersMessagesCheckbox();

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));
        /*assertTrue(listMessagesPage.getTextOfBeforeTheLastTableRow().contains(messages.getProperty(ADMIN_USER_NAME)));*/

        assertTrue(listMessagesPage.checkIfMessageExists(secondUserMessage));
        /*assertTrue(listMessagesPage.getTextOfTheLastTableRow().contains(messages.getProperty(SECOND_USER_NAME)));*/

        try {
            listMessagesPage.clickLastPaginationButton();
        } catch (NullPointerException e) {
            //do nothing
        }

        System.out.println(webDriver.getPageSource());

        assertTrue(listMessagesPage.checkIfMessageExists(firstUserMessage));
        assertFalse(listMessagesPage.checkIfMessageExists(secondUserMessage));

        /*assertFalse(listMessagesPage.getTextOfTheLastTableRow().contains(SECOND_USER_NAME));*/
    }
}
