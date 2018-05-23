package com.qulix.pages;

import org.openqa.selenium.WebDriver;

public class PageObject {

    protected WebDriver webDriver;

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
