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
    private static final By lastTablePageButton = By.xpath("//div[@class='paginateButtons']/a[last()-1]");
    private static final By preLastTablePageButton = By.xpath("//div[@class='paginateButtons']/a[last()]");

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
        } else {
            // TODO: log that the button was not found
        }
    }

/*    private void goToPreLastPage() {
        findPageElement(preLastTablePageButton).click();
    }

    private WebElement getBeforeTheLastTableRow() {
        try {
            return findPageElement(beforeLastTableRow);
        } catch(java.util.NoSuchElementException e) {//todo не то исключение перехватываешь
            this.goToPreLastPage();
            return this.getLastTableRow();
        }
    }*/

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    public String checkPageTitle() {
        return this.getPageTitle().getText();
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

    public String checkTextOfUserGreeting() {
        return this.getUserGreeting().getText();
    }

    private WebElement getRowWithMessage(Message message) {

        String messageText = message.getText();
        String messageHeadline = message.getHeadline();
        String messageUserName = message.getUserName();

        List<WebElement> tableRows = webDriver.findElements(By.xpath("//tbody/tr"));

        for (WebElement tableRow : tableRows) {

            try {
                WebElement cellWithMessageText = tableRow.findElement(By.xpath("./td[contains(text(), '" + messageText + "')]"));
                WebElement cellWithMessageHeadline = tableRow.findElement(By.xpath("./td[contains(text(), '" + messageHeadline + "')]"));
                WebElement cellWithUserName = tableRow.findElement(By.xpath("./td[contains(text(), '" + messageUserName + "')]"));

                if (cellWithMessageText.isDisplayed() && cellWithMessageHeadline.isDisplayed() && cellWithUserName.isDisplayed()) {
                    return tableRow;
                }
            } catch (NoSuchElementException e) {
                //TODO: Log that no such row exists on the page
            }
        } return null;
    }

    public EditMessagePage clickEditButtonInCertainRow(Message message) {
        WebElement tableRow = getRowWithMessage(message);
        WebElement buttonForEdition = tableRow.findElement(editButton);
        if (buttonForEdition != null && buttonForEdition.isDisplayed()) {
            buttonForEdition.click();
        } else {
            // TODO: log that the button was not found
        }
        return new EditMessagePage(webDriver);
    }

    public ListMessagesPage clickDeleteButtonInCertainRow(Message message) {
        WebElement tableRow = getRowWithMessage(message);
        WebElement buttonForDeletion = tableRow.findElement(deleteButton);
        if (buttonForDeletion != null && buttonForDeletion.isDisplayed()) {
            buttonForDeletion.click();
        } else {
            // TODO: log that the button was not found
        }
        return new ListMessagesPage(webDriver);
    }

    public ShowMessagePage clickViewButtonInCertainRow(Message message) {
        WebElement tableRow = getRowWithMessage(message);
        WebElement buttonForView = tableRow.findElement(viewButton);
        if (buttonForView != null && buttonForView.isDisplayed()) {
            buttonForView.click();
        } else {
            // TODO: log that the button was not found
        }
        return new ShowMessagePage(webDriver);
    }

    public Boolean checkIfMessageExists(Message message) {
        WebElement tableRow = getRowWithMessage(message);
        return (tableRow != null && tableRow.isDisplayed());
    }
}

