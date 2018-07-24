package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class CreateMessagePage extends AbstractMessagePage {

    private static final By pageTitle = By.tagName("h1");
    private static final String CREATE_MESSAGE_PAGE_TITLE = "create.message.page.title";

    CreateMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    private String checkPageTitle() {
        return this.getPageTitle().getText();
    }

    public Boolean verifyCreateMessagePageTitle() {
        return checkPageTitle().equals(CREATE_MESSAGE_PAGE_TITLE);
    }

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
