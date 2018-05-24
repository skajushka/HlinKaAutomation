package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShowMessagePage extends PageObject {
    private final By _tabToMessagesList = By.linkText("Message List");
    private final By _showMessageNote = By.xpath("/html/body/div[5]/h1");
    private final By _messageHeadline = By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[1]/td[2]");
    private final By _messageText = By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[3]/td[2]");

    public ShowMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findTabToMessagesList() {
        return webDriver.findElement(_tabToMessagesList);
    }

    public WebElement findShowMessageNote() {
        return webDriver.findElement(_showMessageNote);
    }

    public WebElement findMessageHeadline() {
        return webDriver.findElement(_messageHeadline);
    }

    public WebElement findMessageText() {
        return webDriver.findElement(_messageText);
    }
}
