package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateMessagePage extends AbstractMessagePage {

    private final By buttonCreate = By.id("create");
    private final By pageTitle = By.tagName("h1");

    public CreateMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    public String checkPageTitle() {
        String result = this.getPageTitle().getText();
        return result;
    }

    public WebElement getButtonCreate() {
        return findPageElement(buttonCreate);
    }

    public void clickCreateButton() {
        this.getButtonCreate().click();
    }
}
