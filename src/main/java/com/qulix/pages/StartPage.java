package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage extends AbstractPage {

    private static final By userControllerLink = By.linkText("qulixteachingsite.UserController");

    public StartPage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getUserControllerLink() {
        return findPageElement(userControllerLink);
    }

    public LoginPage clickOnUserControllerLink() {
        getUserControllerLink().click();
        return new LoginPage(webDriver);
    }

    public boolean checkIfUserControllerLinkIsPresent() {
        return getUserControllerLink().isDisplayed();
    }
}
