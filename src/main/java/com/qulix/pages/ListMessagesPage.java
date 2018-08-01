package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListMessagesPage extends AbstractPage {

    private static final By pageTitle = By.tagName("h1");
    private static final By userGreeting = By.className("message");
    private static final By newMessageTab = By.linkText("New Message");
    private static final By allUsersMessagesOption = By.name("allUsers");
    private static final By viewButton = By.xpath("./td/a[contains(text(),'View')]");
    private static final By editButton = By.xpath("./td/a[contains(text(),'Edit')]");
    private static final By deleteButton = By.xpath("./td/a[contains(text(),'Delete')]");
    private static final By logoutButton = By.linkText("Logout");
    private static final By nextButton = By.className("nextLink");
    private static final String LIST_MESSAGES_PAGE_TITLE = "Message List";

    ListMessagesPage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getNewMessageTab() {
        return findPageElement(newMessageTab);
    }

    public CreateMessagePage clickNewMessageTab() {
        getNewMessageTab().click();
        return new CreateMessagePage(webDriver);
    }

    private WebElement getAllUsersMessagesCheckbox() {
        return findPageElement(allUsersMessagesOption);
    }

    public void clickAllUsersMessagesCheckbox() {
        this.getAllUsersMessagesCheckbox().click();
    }

    private WebElement getNextButton() {
        return webDriver.findElement(nextButton);
    }

    private boolean checkIfNextButtonExists() {
        return getNextButton().isDisplayed();
    }

    private ListMessagesPage clickNextButton() {
        getNextButton().click();
        return new ListMessagesPage(webDriver);
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    private String getTextOfPageTitle() {
        return this.getPageTitle().getText();
    }

    public void verifyMessageListPageTitle() {

        if (!this.getTextOfPageTitle().equals(LIST_MESSAGES_PAGE_TITLE)) {

            throw new RuntimeException("Actual page title is '" + getTextOfPageTitle() + "' instead of expected '" + LIST_MESSAGES_PAGE_TITLE + "'" );
        }
    }

    private WebElement getLogoutButton() {
        return findPageElement(logoutButton);
    }

    public LoginPage clickLogoutButton() {
        getLogoutButton().click();
        return new LoginPage(webDriver);
    }

    private WebElement getUserGreeting() {
        return findPageElement(userGreeting);
    }

    private String checkTextOfUserGreeting() {
        return this.getUserGreeting().getText();
    }

    public boolean verifyUserGreeting(String userName) {
        return checkTextOfUserGreeting().contains(userName);
    }

    private WebElement getRowWithMessageOfCertainUser(Message message, boolean considerUser) {

        String messageText = message.getText();
        String messageHeadline = message.getHeadline();
        String messageUserName = message.getUserName();

        List<WebElement> tableRows = webDriver.findElements(By.xpath("//tbody/tr"));

        for (WebElement tableRow : tableRows) {

            try {
                WebElement cellWithMessageText = tableRow.findElement(By.xpath("./td[contains(text(), '" + messageText + "')]"));
                WebElement cellWithMessageHeadline = tableRow.findElement(By.xpath("./td[contains(text(), '" + messageHeadline + "')]"));

                if (cellWithMessageText.isDisplayed() && cellWithMessageHeadline.isDisplayed()) {

                    if (considerUser) {
                        WebElement cellWithUserName = tableRow.findElement(By.xpath("./td[contains(text(), '" + messageUserName + "')]"));

                        if(cellWithUserName.isDisplayed()) {
                            return tableRow;
                        }
                    } else {
                        return tableRow;
                    }

                    return tableRow;
                }
            } catch (NoSuchElementException ignore) {
            }
        }
        return null;
    }

    private WebElement findRowWithTheMessageOfCertainUser(Message message, boolean considerUser) {

        WebElement tableRow = getRowWithMessageOfCertainUser(message, considerUser);

        while (tableRow == null) {

            try {
                if (checkIfNextButtonExists()) {
                    clickNextButton();
                }
            } catch (NoSuchElementException e){
                break;
            }

            tableRow = getRowWithMessageOfCertainUser(message,considerUser);
        }
        return tableRow;
    }

    private WebElement findRowWithTheMessage(Message message) {
        return findRowWithTheMessageOfCertainUser(message, Boolean.FALSE);
    }

    public EditMessagePage clickEditButtonInCertainRow(Message message) {
        WebElement tableRow = findRowWithTheMessage(message);

        if(tableRow != null) {
            WebElement buttonForEdition = tableRow.findElement(editButton);

            if(buttonForEdition != null && buttonForEdition.isDisplayed()) {
                buttonForEdition.click();
            }

            return new EditMessagePage(webDriver);
        }

        throw new RuntimeException("Can't edit message " + message.toString() + ". Such message not found");
    }

    public ListMessagesPage clickDeleteButtonInCertainRow(Message message) {
        WebElement tableRow = findRowWithTheMessage(message);

        if(tableRow != null) {
            WebElement buttonForDeletion = tableRow.findElement(deleteButton);

            if(buttonForDeletion != null && buttonForDeletion.isDisplayed()) {
                buttonForDeletion.click();
            }

            return new ListMessagesPage(webDriver);
        }

        throw new RuntimeException("Can't delete message " + message.toString() + ". Such message not found");
    }

    public ShowMessagePage clickViewButtonInCertainRow(Message message) {
        WebElement tableRow = findRowWithTheMessage(message);

        if(tableRow != null) {
            WebElement buttonForView = tableRow.findElement(viewButton);

            if (buttonForView != null && buttonForView.isDisplayed()) {
                buttonForView.click();
            }

            return new ShowMessagePage(webDriver);
        }

        throw new RuntimeException("Can't view message " + message.toString() + ". Such message not found");
    }

    public boolean checkIfMessageExists(Message message) {
        WebElement tableRow = findRowWithTheMessage(message);
        return tableRow != null;
    }

    public boolean checkIfMessageOfCertainUserExists(Message message, boolean considerUser) {
        WebElement tableRow = findRowWithTheMessageOfCertainUser(message, considerUser);
        return tableRow != null;
    }
}


