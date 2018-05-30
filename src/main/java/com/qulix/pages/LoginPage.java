package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {

    private final By loginField = By.id("login");
    private final By passwordField = By.id("password");
    private final By buttonLogin = By.xpath("//input[@class='save']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findLoginField() {
        return webDriver.findElement(loginField);
    }

    public WebElement findPasswordField() {
        return webDriver.findElement(passwordField);
    }

    public WebElement findButtonLogin() {
        return webDriver.findElement(buttonLogin);
    }

    public void login(String login, String password) {
        this.findLoginField().clear();
        this.findLoginField().sendKeys(login);
        this.findPasswordField().clear();
        this.findPasswordField().sendKeys(password);
        this.findButtonLogin().click();
    }
}
