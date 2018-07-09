package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditMessagePage extends AbstractMessagePage {

    private final By saveButton = By.name("_action_save");
    private final String messageAttributeName = "value";

    public EditMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTextOfMessageHeadline() {
        return this.getMessageHeadline().getAttribute(messageAttributeName);
    }

    public String getTextOfMessageBody() {
        return this.getMessageText().getAttribute(messageAttributeName);
    }

    public WebElement getSaveButton() {
        return findPageElement(saveButton);
    }

    public void clickSaveButton() {
        this.getSaveButton().click();
    }

    @Override
    public Message createMessage(String headline, String text){
        Message message = new Message(headline, text);
        editHeadline().clear();
        editHeadline().sendKeys(message.getHeadline());
        editText().clear();
        editText().sendKeys(message.getText());
        return message;
    }
}
