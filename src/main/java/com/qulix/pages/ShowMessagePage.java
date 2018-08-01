package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShowMessagePage extends AbstractPage {

    private static final By tabToMessagesList = By.linkText("Message List");
    private static final By pageTitle = By.tagName("h1");
    private static final By messageHeadline = By.xpath("//td[contains(text(),'Headline')]/following-sibling::td");
    private static final By messageText = By.xpath("//td[contains(text(),'Text')]/following-sibling::td");
    private static final By tabToNewMessage = By.linkText("New Message");
    private static final String SHOW_MESSAGE_PAGE_TITLE = "Show Message";

    ShowMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    private WebElement getTabToMessagesList() {
        return findPageElement(tabToMessagesList);
    }

    private WebElement getTabToNewMessagePage() {
        return findPageElement(tabToNewMessage);
    }

    public ListMessagesPage clickTabToMessagesList() {
        getTabToMessagesList().click();
        return new ListMessagesPage(webDriver);
    }

    private String getTextOfPageTitle() {
        return this.getPageTitle().getText();
    }

    public void verifyShowMessagePageTitle() {

        if (!this.getTextOfPageTitle().equals(SHOW_MESSAGE_PAGE_TITLE)) {

            throw new RuntimeException("Actual page title is '" + getTextOfPageTitle() + "' instead of expected '" + SHOW_MESSAGE_PAGE_TITLE + "'" );
        }
    }

    private WebElement getMessageHeadline() {
        return findPageElement(messageHeadline);
    }

    private String getTextOfMessageHeadline() {
        return this.getMessageHeadline().getText();
    }

    private WebElement getMessageText() {
        return findPageElement(messageText);
    }

    private String getTextOfMessageBody() {
        return this.getMessageText().getText();
    }

    public CreateMessagePage clickTabToNewMessagePage() {
        this.getTabToNewMessagePage().click();
        return new CreateMessagePage(webDriver);
    }

    public boolean checkTheMessageViewed(Message message) {
        return(getTextOfMessageHeadline().equals(message.getHeadline())&&getTextOfMessageBody().equals(message.getText()));
    }
}

