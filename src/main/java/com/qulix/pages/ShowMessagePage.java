package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShowMessagePage extends AbstractPage {

    private static final By tabToMessagesList = By.linkText("Message List");
    private static final By pageTitle = By.tagName("h1");
    private static final By messageHeadline = By.xpath("//td[contains(text(),'Headline')]/following-sibling::td");
    private static final By messageText = By.xpath("//td[contains(text(),'Text')]/following-sibling::td");
    private static final By tabToNewMessage = By.linkText("New Message");

    ShowMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    private WebElement getTabToMessagesList() {
        return findPageElement(tabToMessagesList);
    }

    private WebElement getTabToNewMessagePage() {
        return findPageElement(tabToNewMessage);
    }

    public ListMessagesPage clickTabToMessagesList() {
        getTabToMessagesList().click();
        return new ListMessagesPage(webDriver);
    }

    public String checkPageTitle() {
        return this.getPageTitle().getText();
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

    public CreateMessagePage clickTabToNewMessagePage() {
        this.getTabToNewMessagePage().click();
        return new CreateMessagePage(webDriver);
    }

    public Boolean checkTheMessageViewed(Message message) {

        Boolean result = false;

        if ((webDriver.getPageSource().contains(message.getHeadline())) && (webDriver.getPageSource().contains(message.getText()))) {
            result = true;
        }

        return result;
    }
}
