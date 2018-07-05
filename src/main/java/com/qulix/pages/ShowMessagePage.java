package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShowMessagePage extends BasePage {
    //TODO Переделать локаторы
    private final By tabToMessagesList = By.linkText("Message List");
    private final By pageTitle = By.tagName("h1");
    private final By messageHeadline = By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[1]/td[2]");
    private final By messageText = By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[3]/td[2]");
    private final By tabToNewMessage = By.linkText("New Message");

    public ShowMessagePage(WebDriver webDriver) {
        super(webDriver);
    }
    public WebElement getPageTitle() {
        return webDriver.findElement(pageTitle);
    }

    public String checkPageTitle() {
        String result = this.getPageTitle().getText();
        return result;
    }

    public WebElement getTabToMessagesList() {
        return webDriver.findElement(tabToMessagesList);
    }

    public void clickTabToMessagesList() {
        this.getTabToMessagesList().click();
    }

    public WebElement getMessageHeadline() {
        return webDriver.findElement(messageHeadline);
    }

    public String getTextOfMessageHeadline() {
        String result = this.getMessageHeadline().getText();
        return result;
    }

    public WebElement getMessageText() {
        return webDriver.findElement(messageText);
    }

    public String getTextOfMessageBody() {
        String result = this.getMessageText().getText();
        return result;
    }

    public WebElement getTabToNewMessagePage() {
        return webDriver.findElement(tabToNewMessage);
    }

    public void clickTabToNewMessagePage() {
        this.getTabToNewMessagePage().click();
    }
}
