package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListMessagesPage extends PageObject {

    private final By pageTitle = By.xpath("/html/body/div[5]/h1");
    private final By newMessageTab = By.linkText("New Message");
    private final By allUsersMessagesOption = By.name("allUsers");
    private final By nextButton = By.linkText("Next");
    private final By lastTableRow = By.xpath("//table/tbody/tr[last()]");
    private final By viewButton = By.linkText("View");
    private final By editButton = By.linkText("Edit");

    public ListMessagesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findNewMessageTab() {
        return webDriver.findElement(newMessageTab);
    }

    public WebElement findAllUsersMessagesOption() {
        return webDriver.findElement(allUsersMessagesOption);
    }

    public WebElement findNextButton() {
        return webDriver.findElement(nextButton);
    }

    public WebElement findLastTableRow() {
        return webDriver.findElement(lastTableRow);
    }

    public WebElement findPageTitle() {
        return webDriver.findElement(pageTitle);
    }

    public WebElement findViewButton() {
       return webDriver.findElement(viewButton);
    }

    public WebElement findEditButton() {
        return webDriver.findElement(editButton);
    }

    public void clickNextButton() {
        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);

        do {
            listMessagesPage.findNextButton().click();
        } while (listMessagesPage.findNextButton().isDisplayed());
    }
}
