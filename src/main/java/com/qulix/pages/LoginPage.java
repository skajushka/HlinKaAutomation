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

    public WebElement getLoginField() {
        return webDriver.findElement(loginField);
    }

    public WebElement getPasswordField() {
        return webDriver.findElement(passwordField);
    }

    public WebElement getButtonLogin() {
        return webDriver.findElement(buttonLogin);
    }

    public void login(String login, String password) {//TODO не void
        this.getLoginField().clear();
        this.getLoginField().sendKeys(login);
        this.getPasswordField().clear();
        this.getPasswordField().sendKeys(password);
        this.getButtonLogin().click();
    }
}
