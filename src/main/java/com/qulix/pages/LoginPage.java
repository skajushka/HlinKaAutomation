package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final By _editLogin =By.id("login");
    private final By _editPassword =By.id("password");
    private final By _buttonLogin = By.xpath("//input[@class='save']");

    private WebDriver webDriver;

    public LoginPage(WebDriver driver) {
        this.webDriver = driver;
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

    public void signIn(String login, String password) {
        editLogin().clear();
        editLogin().sendKeys(login);
        editPassword().sendKeys(password);
        buttonLogin().click();
    }
}
