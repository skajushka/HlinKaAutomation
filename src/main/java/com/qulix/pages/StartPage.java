package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage extends PageObject {

    private final String linkURL = "http://localhost:8080/QulixTeachingSite"; //TODO static
    private final By userControllerLink = By.linkText("qulixteachingsite.UserController");

    public StartPage(WebDriver webDriver) {
        super(webDriver);
    }

     public String getLinkURL() {
         return linkURL;
    }

    public WebElement getUserControllerLink() {
        System.out.println(webDriver);
        return webDriver.findElement(userControllerLink);
    }
}
