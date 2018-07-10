package com.qulix.pages;

import com.qulix.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateMessagePage extends AbstractMessagePage {

    private static final By buttonCreate = By.id("create");
    private static final By pageTitle = By.tagName("h1");

    public CreateMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    public String checkPageTitle() {
        return this.getPageTitle().getText();
    }

    public WebElement getButtonCreate() {
        return findPageElement(buttonCreate);
    }

    public void clickCreateButton() {
        this.getButtonCreate().click();
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
