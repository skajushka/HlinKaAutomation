package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AbstractMessagePage extends BasePage {

    private final By tabToMessagesList = By.linkText("Message List");
    private final By messageHeadline = By.id("headline");
    private final By messageText = By.id("text");

    public AbstractMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getTabToMessagesList() {
        return webDriver.findElement(tabToMessagesList);
    }

    public void clickTabToMessagesList() {
        this.getTabToMessagesList().click();
    }

    public WebElement editHeadline() {
        return webDriver.findElement(messageHeadline);
    }

    public WebElement editText() {
        return webDriver.findElement(messageText);
    }

    public WebElement getMessageHeadline() {
        return webDriver.findElement(messageHeadline);
    }

    public WebElement getMessageText() {
        return webDriver.findElement(messageText);
    }

    public Message createMessage(String headline, String text){
        Message message = new Message(headline, text);
        editHeadline().sendKeys(message.getHeadline());
        editText().sendKeys(message.getText());
        return message;
    }
}
