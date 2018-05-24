package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListMessagesPage extends PageObject {
    private final By _pageTitle = By.xpath("/html/body/div[5]/h1");
    private final By _newMessageTab = By.linkText("New Message");
    private final By _allUsersMessagesOption = By.name("allUsers");
    private final By _nextButton = By.linkText("Next");
    private final By _lastTableRow = By.xpath("//table/tbody/tr[last()]");
    private final By _viewButton = By.linkText("View");

    public ListMessagesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findNewMessageTab() {
        return webDriver.findElement(_newMessageTab);
    }

    public WebElement findAllUsersMessagesOption() {
        return webDriver.findElement(_allUsersMessagesOption);
    }

    public WebElement findNextButton() {
        return webDriver.findElement(_nextButton);
    }

    public WebElement findLastTableRow() {
        return webDriver.findElement(_lastTableRow);
    }

    public WebElement findPageTitle() {
        return webDriver.findElement(_pageTitle);
    }

    public WebElement findViewButton() {
       return webDriver.findElement(_viewButton);

    }
}
