package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage extends BasePage {

    private static final String linkURL = "http://localhost:8080/QulixTeachingSite";
    private static final By userControllerLink = By.linkText("qulixteachingsite.UserController");

    StartPage startPage = new StartPage(webDriver);

    public StartPage(WebDriver webDriver) {
        super(webDriver);
    }

     public String getLinkURL() {
         return linkURL;
    }

    public WebElement getUserControllerLink() {
        return webDriver.findElement(userControllerLink);
    }

    public void clickOnUserControllerLink() {
        startPage.getUserControllerLink().click();
    }

    public boolean checkIfUserControllerLinkIsPresent() {
        boolean result = startPage.getUserControllerLink().isDisplayed();
        return result;
    }
}
