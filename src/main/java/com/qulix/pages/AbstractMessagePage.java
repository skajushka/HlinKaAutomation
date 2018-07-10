package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractMessagePage extends AbstractPage {

    private static final By tabToMessagesList = By.linkText("Message List");
    private static final By messageHeadline = By.id("headline");
    private static final By messageText = By.id("text");

    public AbstractMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getTabToMessagesList() {
        return findPageElement(tabToMessagesList);
    }

    public ListMessagesPage clickTabToMessagesList() {
        this.getTabToMessagesList().click();
        return new ListMessagesPage(webDriver);
    }

    public WebElement editHeadline() {
        return findPageElement(messageHeadline);
    }

    public WebElement editText() {
        return findPageElement(messageText);
    }

    public WebElement getMessageHeadline() {
        return findPageElement(messageHeadline);
    }

    public WebElement getMessageText() {
        return findPageElement(messageText);
    }

    public void populateMessageFields(Message message){
        editHeadline().sendKeys(message.getHeadline());
        editText().sendKeys(message.getText());
    }
}
