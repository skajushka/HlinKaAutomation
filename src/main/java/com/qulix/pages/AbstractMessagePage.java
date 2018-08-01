package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Date;

public abstract class AbstractMessagePage extends AbstractPage {

    private static final By tabToMessagesList = By.linkText("Message List");
    private static final By messageHeadline = By.id("headline");
    private static final By messageText = By.id("text");
    private static final By submitButton = By.className("save");

    protected AbstractMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getTabToMessagesList() {
        return findPageElement(tabToMessagesList);
    }

    protected WebElement editHeadline() {
        return findPageElement(messageHeadline);
    }

    protected WebElement editText() {
        return findPageElement(messageText);
    }

    protected WebElement getMessageHeadline() {
        return findPageElement(messageHeadline);
    }

    protected WebElement getMessageText() {
        return findPageElement(messageText);
    }

    protected WebElement getSubmitButton() {
        return findPageElement(submitButton);
    }

    protected void clickSubmitButton() {
        this.getSubmitButton().click();
    }

    public void populateMessageFields(Message message){
        editHeadline().clear();
        editHeadline().sendKeys(message.getHeadline());
        editText().clear();
        editText().sendKeys(message.getText());
    }

    public ListMessagesPage clickTabToMessagesList() {
        this.getTabToMessagesList().click();
        return new ListMessagesPage(webDriver);
    }
}
