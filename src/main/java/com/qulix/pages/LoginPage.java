package com.qulix.pages;

import com.qulix.user.User;
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
        boolean result = this.getLoginField().isDisplayed();
        return result;
    }

    public WebElement getPasswordField() {
        return findPageElement(passwordField);
    }

    public boolean checkIfPasswordFieldIsPresent() {
        boolean result = this.getPasswordField().isDisplayed();
        return result;
    }

    public WebElement getButtonLogin() {
        return findPageElement(buttonLogin);
    }

    public boolean checkIfLoginButtonIsPresent() {
        boolean result = this.getButtonLogin().isDisplayed();
        return result;
    }

    public void clickLoginButton() {
        this.getButtonLogin().click();
    }

    public User login(String login, String password) {
        User user = new User(login, password);
        this.getLoginField().clear();
        this.getLoginField().sendKeys(user.getLogin());
        this.getPasswordField().clear();
        this.getPasswordField().sendKeys(user.getPassword());
        clickLoginButton();
        return user;
    }
}
