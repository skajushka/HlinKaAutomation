package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditMessagePage extends AbstractMessagePage {

    private static final By saveButton = By.name("_action_save");
    private static final By pageTitle = By.tagName("h1");
    private static final String messageAttributeName = "value";
    private static final String PAGE_TITLE = "Edit Message";

    EditMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getPageTitle() {
        return webDriver.findElement(pageTitle);
    }

    private String getTextOfPageTitle() {
        return getPageTitle().getText();
    }

    public Boolean verifyEditMessagePageTitle() {
        return getTextOfPageTitle().equals(PAGE_TITLE);
    }

    private String getTextOfMessageHeadline() {
        return this.getMessageHeadline().getAttribute(messageAttributeName);
    }

    private String getTextOfMessageBody() {
        return this.getMessageText().getAttribute(messageAttributeName);
    }

    private WebElement getSaveButton() {
        return findPageElement(saveButton);
    }

    public ShowMessagePage submitEditedMessage() {
        this.getSaveButton().click();
        return new ShowMessagePage(webDriver);
    }

    public Boolean checkTheMessageOpened(Message message) {
        return(getTextOfMessageHeadline().equals(message.getHeadline())&&getTextOfMessageBody().equals(message.getText()));
    }
}
