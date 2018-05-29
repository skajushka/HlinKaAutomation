package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage extends PageObject {

    private final String linkURL = "http://localhost:8080/QulixTeachingSite";
    private final By userControllerLink = By.linkText("qulixteachingsite.UserController");

    public StartPage(WebDriver webDriver) {
        super(webDriver);
    }

     public String getLinkURL() {
         return linkURL;
    }

    public WebElement findUserControllerLink() {
        return webDriver.findElement(userControllerLink);
    }
}
