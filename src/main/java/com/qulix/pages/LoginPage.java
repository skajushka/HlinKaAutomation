package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By loginField = By.id("login");
    private final By passwordField = By.id("password");
    private final By buttonLogin = By.xpath("//input[@class='save']");

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

    public void login(String login, String password) { //TODO: не void - почему?

        this.getLoginField().clear();
        this.getLoginField().sendKeys(login);
        this.getPasswordField().clear();
        this.getPasswordField().sendKeys(password);
        clickLoginButton();
    }
}
