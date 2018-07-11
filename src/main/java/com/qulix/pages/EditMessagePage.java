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

    public String getTextOfMessageHeadline() {
        return this.getMessageHeadline().getAttribute(messageAttributeName);
    }

    public String getTextOfMessageBody() {
        return this.getMessageText().getAttribute(messageAttributeName);
    }

    private WebElement getSaveButton() {
        return findPageElement(saveButton);
    }

    public ShowMessagePage submitEditedMessage() {
        this.getSaveButton().click();
        return new ShowMessagePage(webDriver);
    }

    public void editMessage(Message message) {
        editHeadline().clear(); //todo заполнение полей ввода всегда предполагает затирание текущего значение, потому clear должно делаться в populate
        editText().clear();
        populateMessageFields(message);
    }
}
