package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By loginField = By.id("login");
    private final By passwordField = By.id("password");
    private final By buttonLogin = By.xpath("//input[@class='save']");

    LoginPage loginPage = new LoginPage(webDriver);

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getLoginField() {
        return webDriver.findElement(loginField);
    }

    public boolean checkIfLoginFieldIsPresent() {
        boolean result = loginPage.getLoginField().isDisplayed();
        return result;
    }

    public WebElement getPasswordField() {
        return webDriver.findElement(passwordField);
    }

    public boolean checkIfPasswordFieldIsPresent() {
        boolean result = loginPage.getPasswordField().isDisplayed();
        return result;
    }

    public WebElement getButtonLogin() {
        return webDriver.findElement(buttonLogin);
    }

    public boolean checkIfLoginButtonIsPresent() {
        boolean result = loginPage.getButtonLogin().isDisplayed();
        return result;
    }

    public void clickLoginButton() {
        loginPage.getButtonLogin().click();
    }

    public void login(String login, String password) {//TODO не void
        this.getLoginField().clear();
        this.getLoginField().sendKeys(login);
        this.getPasswordField().clear();
        this.getPasswordField().sendKeys(password);
        this.getButtonLogin().click();
    }
}
