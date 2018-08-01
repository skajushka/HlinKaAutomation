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

    public void verifyCreateMessagePageTitle() {

        if (!this.getTextOfPageTitle().equals(CREATE_MESSAGE_PAGE_TITLE)) {

            throw new RuntimeException("Actual page title is '" + getTextOfPageTitle() + "' instead of expected '" + CREATE_MESSAGE_PAGE_TITLE + "'" );
        }
    }

    public ShowMessagePage createMessage(Message message) {
        populateMessageFields(message);
        clickSubmitButton();
        return new ShowMessagePage(webDriver);
    }
}
