package com.qulix.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver webDriver; //TODO private.

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
