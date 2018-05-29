package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShowMessagePage extends PageObject {
    private final By tabToMessagesList = By.linkText("Message List");
    private final By showMessageNote = By.xpath("/html/body/div[5]/h1");
    private final By messageHeadline = By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[1]/td[2]");
    private final By messageText = By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[3]/td[2]");

    public ShowMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findTabToMessagesList() {
        return webDriver.findElement(tabToMessagesList);
    }

    public WebElement findShowMessageNote() {
        return webDriver.findElement(showMessageNote);
    }

    public WebElement findMessageHeadline() {
        return webDriver.findElement(messageHeadline);
    }

    public WebElement findMessageText() {
        return webDriver.findElement(messageText);
    }
}
