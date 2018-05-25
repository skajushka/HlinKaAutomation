package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditMessagePage extends PageObject {

    private final By _tabToMessagesList = By.linkText("Message List");
    private final By _newMessageTab = By.linkText("New Message");
    private final By _editMessagePageTitle = By.xpath("/html/body/div[5]/h1");
    private final By _messageHeadline = By.name("headline");
    private final By _messageText = By.name("text");
    private final By _saveButton = By.name("_action_save");
    private final By _deleteButton = By.name("_action_delete");

    public EditMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findTabToMessagesList() {
        return webDriver.findElement(_tabToMessagesList);
    }

    public WebElement findNewMessageTab() {
        return webDriver.findElement(_newMessageTab);
    }

    public WebElement findEditMessagePageTitle() {
        return webDriver.findElement(_editMessagePageTitle);
    }

    public WebElement findMessageHeadline() {
        return webDriver.findElement(_messageHeadline);
    }

    public WebElement findMessageText() {
        return webDriver.findElement(_messageText);
    }

    public WebElement findSaveButton() {
        return webDriver.findElement(_saveButton);
    }

    public WebElement findDeleteButton() {
        return webDriver.findElement(_deleteButton);
    }
}
