package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditMessagePage extends PageObject {

    private final By tabToMessagesList = By.linkText("Message List");
    private final By newMessageTab = By.linkText("New Message");
    private final By editMessagePageTitle = By.xpath("/html/body/div[5]/h1");
    private final By messageHeadline = By.name("headline");
    private final By messageText = By.name("text");
    private final By saveButton = By.name("_action_save");
    private final By deleteButton = By.name("_action_delete");

    public EditMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findTabToMessagesList() {
        return webDriver.findElement(tabToMessagesList);
    }

    public WebElement findNewMessageTab() {
        return webDriver.findElement(newMessageTab);
    }

    public WebElement findEditMessagePageTitle() {
        return webDriver.findElement(editMessagePageTitle);
    }

    public WebElement findMessageHeadline() {
        return webDriver.findElement(messageHeadline);
    }

    public WebElement findMessageText() {
        return webDriver.findElement(messageText);
    }

    public WebElement findSaveButton() {
        return webDriver.findElement(saveButton);
    }

    public WebElement findDeleteButton() {
        return webDriver.findElement(deleteButton);
    }
}
