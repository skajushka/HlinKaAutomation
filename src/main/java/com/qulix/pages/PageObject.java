package com.qulix.pages;

import org.openqa.selenium.WebDriver;

public class PageObject {//TODO Name BasePage

    protected WebDriver webDriver; //TODO private.

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
