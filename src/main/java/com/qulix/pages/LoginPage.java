package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {

    private final By editLogin = By.id("login");
    private final By editPassword = By.id("password");
    private final By buttonLogin = By.xpath("//input[@class='save']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement editLogin() {
        return webDriver.findElement(editLogin);
    }

    public WebElement editPassword() {
        return webDriver.findElement(editPassword);
    }

    public WebElement buttonLogin() {
        return webDriver.findElement(buttonLogin);
    }

    public void enterLogin(String login) {
        this.editLogin().clear();
        this.editLogin().sendKeys(login);
    }

    public void enterPassword(String password) {
        this.editPassword().clear();
        this.editPassword().sendKeys(password);
    }

    public void submit() {
        this.buttonLogin().click();
    }
}
