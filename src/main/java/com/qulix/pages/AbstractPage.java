package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

    protected final WebDriver webDriver;

    AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebElement findPageElement(By locator) {
        return webDriver.findElement(locator);
    }
}
