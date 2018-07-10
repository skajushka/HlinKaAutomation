package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {

    private static final By loginField = By.id("login");
    private static final By passwordField = By.id("password");
    private static final By buttonLogin = By.xpath("//input[@class='save']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getLoginField() {
        return findPageElement(loginField);
    }

    public boolean checkIfLoginFieldIsPresent() {
        return this.getLoginField().isDisplayed();
    }

    public WebElement getPasswordField() {
        return findPageElement(passwordField);
    }

    public boolean checkIfPasswordFieldIsPresent() {
        return this.getPasswordField().isDisplayed();
    }

    public WebElement getButtonLogin() {
        return findPageElement(buttonLogin);
    }

    public boolean checkIfLoginButtonIsPresent() {
        return this.getButtonLogin().isDisplayed();
    }

    public void clickLoginButton() {
        this.getButtonLogin().click();
    }

    public ListMessagesPage login(String login, String password) {
        this.getLoginField().clear();
        this.getLoginField().sendKeys(login);
        this.getPasswordField().clear();
        this.getPasswordField().sendKeys(password);
        clickLoginButton();
        return new ListMessagesPage(webDriver);
    }
}
