package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShowMessagePage extends AbstractPage {

    private static final By tabToMessagesList = By.linkText("Message List");
    private static final By pageTitle = By.tagName("h1");
    private static final By messageHeadline = By.xpath("//td[contains(text(),'Headline')]/following-sibling::td");
    private static final By messageText = By.xpath("//td[contains(text(),'Text')]/following-sibling::td");
    private static final By tabToNewMessage = By.linkText("New Message");

    public ShowMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    public String checkPageTitle() {
        return this.getPageTitle().getText();
    }

    public WebElement getTabToMessagesList() {
        return findPageElement(tabToMessagesList);
    }

    public ListMessagesPage clickTabToMessagesList() {
        getTabToMessagesList().click();
        return new ListMessagesPage(webDriver);
    }

    public WebElement getMessageHeadline() {
        return findPageElement(messageHeadline);
    }

    public String getTextOfMessageHeadline() {
        return this.getMessageHeadline().getText();
    }

    public WebElement getMessageText() {
        return findPageElement(messageText);
    }

    public String getTextOfMessageBody() {
        return this.getMessageText().getText();
    }

    public WebElement getTabToNewMessagePage() {
        return findPageElement(tabToNewMessage);
    }

    public CreateMessagePage clickTabToNewMessagePage() {
        this.getTabToNewMessagePage().click();
        return new CreateMessagePage(webDriver);
    }
}
