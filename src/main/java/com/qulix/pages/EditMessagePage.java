package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditMessagePage extends PageObject {

    private final By tabToMessagesList = By.linkText("Message List");
    private final By messageHeadline = By.id("headline");
    private final By messageText = By.id("text");
    private final By saveButton = By.name("_action_save");

    public EditMessagePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getTabToMessagesList() {
        return webDriver.findElement(tabToMessagesList);
    }

    public WebElement getMessageHeadline() {
        return webDriver.findElement(messageHeadline);
    }

    public WebElement getMessageText() {
        return webDriver.findElement(messageText);
    }

    public WebElement getSaveButton() {
        return webDriver.findElement(saveButton);
    }

    public void changeMessageHeadlineAndText(String headline, String text) { //TODO Edit и Create почти одно и тоже. Создай суперкласс для них
        getMessageHeadline().clear();
        getMessageHeadline().sendKeys(headline);
        getMessageText().clear();
        getMessageText().sendKeys(text);
    }
}
