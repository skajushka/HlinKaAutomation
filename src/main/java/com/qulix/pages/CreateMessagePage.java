package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateMessagePage extends PageObject {

    private final By editHeadline = By.id("headline");
    private final By editText = By.id("text");
    private final By buttonCreate = By.id("create");

    private String MESSAGE_HEADLINE = "New Message by Kate";
    private String MESSAGE_TEXT = "This is the text of a new message";

    public CreateMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement editHeadline() {
        return webDriver.findElement(editHeadline);
    }

    public WebElement editText() {
        return webDriver.findElement(editText);
    }

    public WebElement findButtonCreate() {
        return webDriver.findElement(buttonCreate);
    }

    public void sentMessage() {
        editHeadline().clear();
        editHeadline().sendKeys(MESSAGE_HEADLINE);
        editText().clear();
        editText().sendKeys(MESSAGE_TEXT);
        findButtonCreate().click();
    }
}
