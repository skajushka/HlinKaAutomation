package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateMessagePage extends PageObject {

    private final By _editHeadline = By.id("headline");
    private final By _editText = By.xpath("//input[@id='text']");
    private final By _buttonCreate = By.id("create");

    public CreateMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement editHeadline() {
        return webDriver.findElement(_editHeadline);
    }

    public WebElement editText() {
        return webDriver.findElement(_editText);
    }

    public WebElement findButtonCreate() {
        return webDriver.findElement(_buttonCreate);
    }

    public void sendMessage() {
        editHeadline().clear();
        editHeadline().sendKeys("New Message by Kate");
        editText().sendKeys("This is the text of a new message");
        findButtonCreate().click();
    }
}
