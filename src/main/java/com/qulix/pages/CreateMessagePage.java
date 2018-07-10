package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateMessagePage extends AbstractMessagePage {

    private static final By buttonCreate = By.id("create");
    private static final By pageTitle = By.tagName("h1");

    CreateMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    private WebElement getButtonCreate() {
        return findPageElement(buttonCreate);
    }

    private void clickCreateButton() {
        this.getButtonCreate().click();
    }

    public String checkPageTitle() {
        return this.getPageTitle().getText();
    }

    public ShowMessagePage createMessage(Message message, Boolean save) {
        populateMessageFields(message);

        if (save) {
            clickCreateButton();
        }

        return new ShowMessagePage(webDriver);
    }

    public ShowMessagePage createMessage(Message message) {
        return createMessage(message, Boolean.TRUE);
    }
}
