package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MessagePage {
    private final By _editHeadline = By.id("headline");
    private final By _editText = By.xpath("//input[@id='text']");
    private final By _buttonCreate = By.id("create");

    private WebDriver webDriver;

    public MessagePage(WebDriver driver) {
        this.webDriver = driver;
    }

    public WebElement editdHeadline() {
        return webDriver.findElement(_editHeadline);
    }

    public WebElement editText() {
        return webDriver.findElement(_editText);
    }

    public WebElement buttonCreate() {
        return webDriver.findElement(_buttonCreate);
    }

    public void sendMessage() {
        editdHeadline().clear();
        editdHeadline().sendKeys("New Message by Kate");
        editText().sendKeys("This is the text of a new message");
        buttonCreate().click();
    }
}
