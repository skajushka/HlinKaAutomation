package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {

    private final By _editLogin = By.id("login");
    private final By _editPassword = By.id("password");
    private final By _buttonLogin = By.xpath("//input[@class='save']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement editLogin() {
        return webDriver.findElement(_editLogin);
    }

    private WebElement editPassword() {
        return webDriver.findElement(_editPassword);
    }

    private WebElement buttonLogin() {
        return webDriver.findElement(_buttonLogin);
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
