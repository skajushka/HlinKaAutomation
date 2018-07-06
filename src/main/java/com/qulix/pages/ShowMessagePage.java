package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShowMessagePage extends BasePage {
    //TODO Переделать локаторы
    private final By tabToMessagesList = By.linkText("Message List");
    private final By pageTitle = By.tagName("h1");
    private final By messageHeadline = By.xpath("//td[contains(text(),'Headline')]/following-sibling::td");
    private final By messageText = By.xpath("//td[contains(text(),'Text')]/following-sibling::td");
    private final By tabToNewMessage = By.linkText("New Message");

    public ShowMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    public String checkPageTitle() {
        String result = this.getPageTitle().getText();
        return result;
    }

    public WebElement getTabToMessagesList() {
        return findPageElement(tabToMessagesList);
    }

    public void clickTabToMessagesList() {
        this.getTabToMessagesList().click();
    }

    public WebElement getMessageHeadline() {
        return findPageElement(messageHeadline);
    }

    public String getTextOfMessageHeadline() {
        String result = this.getMessageHeadline().getText();
        return result;
    }

    public WebElement getMessageText() {
        return findPageElement(messageText);
    }

    public String getTextOfMessageBody() {
        String result = this.getMessageText().getText();
        return result;
    }

    public WebElement getTabToNewMessagePage() {
        return findPageElement(tabToNewMessage);
    }

    public void clickTabToNewMessagePage() {
        this.getTabToNewMessagePage().click();
    }
}
