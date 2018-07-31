package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateMessagePage extends AbstractMessagePage {

    private static final By pageTitle = By.tagName("h1");
    private static final String CREATE_MESSAGE_PAGE_TITLE = "Create Message";

    CreateMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    private String getTextOfPageTitle() {
        return this.getPageTitle().getText();
    }

    public boolean verifyCreateMessagePageTitle() {
        return getTextOfPageTitle().equals(CREATE_MESSAGE_PAGE_TITLE);
    }

    //TODO Лучше использовать createMessage и оставить тот же populateMessage, не надо вот этот доп. параметр
    public ShowMessagePage createMessage(Message message, Boolean save) {
        populateMessageFields(message);

        if (save) {
            clickSubmitButton();
        }

        return new ShowMessagePage(webDriver);
    }

    public ShowMessagePage createMessage(Message message) {
        return createMessage(message, Boolean.TRUE);
    }
}
