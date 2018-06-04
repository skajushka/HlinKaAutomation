package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShowMessagePage extends PageObject {
    //TODO Переделать локаторы
    private final By tabToMessagesList = By.linkText("Message List");
    private final By showMessageNote = By.xpath("/html/body/div[5]/h1");
    private final By messageHeadline = By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[1]/td[2]");
    private final By messageText = By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[3]/td[2]");
    private final By tabToNewMessage = By.linkText("New Message");

    public ShowMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getTabToMessagesList() {
        return webDriver.findElement(tabToMessagesList);
    }

    public WebElement getShowMessageNote() {
        return webDriver.findElement(showMessageNote);
    }

    public WebElement getMessageHeadline() {
        return webDriver.findElement(messageHeadline);
    }

    public WebElement getMessageText() {
        return webDriver.findElement(messageText);
    }

    public WebElement getTabToNewMessagePage() {
        return webDriver.findElement(tabToNewMessage);
    }
}
