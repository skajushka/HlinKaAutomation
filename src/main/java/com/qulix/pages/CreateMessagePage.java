package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateMessagePage extends BasePage {

    private final By editHeadline = By.id("headline");
    private final By editText = By.id("text");
    private final By buttonCreate = By.id("create");
    private final By tabToMessagesList = By.linkText("Message List");

    public CreateMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement editHeadline() {
        return webDriver.findElement(editHeadline);
    }

    public WebElement editText() {
        return webDriver.findElement(editText);
    }

    public WebElement getButtonCreate() {
        return webDriver.findElement(buttonCreate);
    }

    public WebElement getTabToMessagesList() {
        return webDriver.findElement(tabToMessagesList);
    }

    public void createMessage(String headline, String text) {//TODO не void
        editHeadline().clear();
        editHeadline().sendKeys(headline);
        editText().clear();
        editText().sendKeys(text);
        getButtonCreate().click();
    }

    public void fillInMessageFields(String headline, String text) {//TODO Дублирование с методом выше
        editHeadline().clear();
        editHeadline().sendKeys(headline);
        editText().clear();
        editText().sendKeys(text);
    }
}
