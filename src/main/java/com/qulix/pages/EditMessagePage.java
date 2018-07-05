package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditMessagePage extends AbstractMessagePage {

    private final By saveButton = By.name("_action_save");

    public EditMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTextOfMessageHeadline() {
        String result = this.getMessageHeadline().getAttribute("value");
        return result;
    }

    public String getTextOfMessageBody() {
        String result = this.getMessageText().getAttribute("value");
        return result;
    }

    public WebElement getSaveButton() {
        return webDriver.findElement(saveButton);
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
