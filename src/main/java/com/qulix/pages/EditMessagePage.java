package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditMessagePage extends AbstractMessagePage {

    private static final By saveButton = By.name("_action_save");
    private static final String messageAttributeName = "value";

    EditMessagePage(WebDriver webDriver) {
        super(webDriver);
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
