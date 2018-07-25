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
    //todo last и pre перепутаны по-моему - все верно, prelast вызывается только если мессаджа не оказалось на last, где юзер находится в данный момент,
    // todo: и тогда кнопка предпоследней страницы будет последней кнопкой в списке
    // todo: а в случае с last, за ней еще идет кнопка Next, на которую кликать не нужно
    private static final By lastTablePageButton = By.xpath("//div[@class='paginateButtons']/a[last()-1]");
    private static final By preLastTablePageButton = By.xpath("//div[@class='paginateButtons']/a[last()]");
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

    private WebElement getLastPaginationButton() {
        return findPageElement(lastTablePageButton);
    }

    public void clickLastPaginationButton() {
        WebElement lastPageButton = getLastPaginationButton();
        if (lastPageButton != null && lastPageButton.isDisplayed()) {
            lastPageButton.click();
        }
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    private String getTextOfPageTitle() {
        return this.getPageTitle().getText();
    }

    public Boolean verifyMessageListPageTitle() {
        return this.getTextOfPageTitle().equals(LIST_MESSAGES_PAGE_TITLE);
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

    public Boolean verifyUserGreeting(String userName) {
        return checkTextOfUserGreeting().contains(userName);
    }

    private WebElement getRowWithMessage(Message message) {
        return getRowWithMessageOfCertainUser(message, Boolean.FALSE);
    }

    private WebElement getRowWithMessageOfCertainUser(Message message, Boolean considerUser) {
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
                }
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public EditMessagePage clickEditButtonInCertainRow(Message message) {
        WebElement tableRow = getRowWithMessage(message);

        if(tableRow != null) {
            WebElement buttonForEdition = tableRow.findElement(editButton);

            if(buttonForEdition != null && buttonForEdition.isDisplayed()) {
                buttonForEdition.click();
            }

            return new EditMessagePage(webDriver);
        }

        return null;
    }

    public ListMessagesPage clickDeleteButtonInCertainRow(Message message) {
        WebElement tableRow = getRowWithMessage(message);

        if(tableRow != null) {
            WebElement buttonForDeletion = tableRow.findElement(deleteButton);

            if(buttonForDeletion != null && buttonForDeletion.isDisplayed()) {
                buttonForDeletion.click();
            }

            return new ListMessagesPage(webDriver);
        }

        return null;
    }

    public ShowMessagePage clickViewButtonInCertainRow(Message message) {
        WebElement tableRow = getRowWithMessage(message);

        if(tableRow != null) {
            WebElement buttonForView = tableRow.findElement(viewButton);

            if (buttonForView != null && buttonForView.isDisplayed()) {
                buttonForView.click();
            }

            return new ShowMessagePage(webDriver);
        }

        return null;
    }

    public Boolean checkIfMessageExists(Message message) {
        WebElement tableRow = getRowWithMessage(message);
        return tableRow != null && tableRow.isDisplayed();
    }

    public Boolean checkIfMessageOfCertainUserExists(Message message, Boolean considerUser) {
        WebElement tableRow = getRowWithMessageOfCertainUser(message, considerUser);
        return tableRow != null && tableRow.isDisplayed();
    }

    public Boolean checkIfMessageExistsOnThePage (Message message) {
        return checkIfMessageOfCertainUserExistsOnThePage(message, Boolean.FALSE);
    }

    public Boolean checkIfMessageOfCertainUserExistsOnThePage (Message message, Boolean considerUser) {

        if(checkIfMessageOfCertainUserExists(message, considerUser)) {
            return Boolean.TRUE;
        }

        goToPreLastPage();

        if (checkIfMessageOfCertainUserExists(message, considerUser)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    private void goToPreLastPage() {
        findPageElement(preLastTablePageButton).click();
    }
}


