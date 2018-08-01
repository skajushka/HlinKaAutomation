package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {

    private static final By loginField = By.id("login");
    private static final By passwordField = By.id("password");
    private static final By buttonLogin = By.xpath("//input[@class='save']");
    private static final By pageTitle = By.tagName("h1");
    private static final String LOGIN_PAGE_TITLE = "Login";

    LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getLoginField() {
        return findPageElement(loginField);
    }

    public boolean checkIfLoginFieldIsPresent() {
        return this.getLoginField().isDisplayed();
    }

    private WebElement getPasswordField() {
        return findPageElement(passwordField);
    }

    public boolean checkIfPasswordFieldIsPresent() {
        return this.getPasswordField().isDisplayed();
    }

    private WebElement getButtonLogin() {
        return findPageElement(buttonLogin);
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    private String getTextOfPageTitle() {
        return this.getPageTitle().getText();
    }

    public void verifyLoginPageTitle() {

        if (!this.getTextOfPageTitle().equals(LOGIN_PAGE_TITLE)) {

            throw new RuntimeException("Actual page title is '" + getTextOfPageTitle() + "' instead of expected '" + LOGIN_PAGE_TITLE + "'" );
        }
    }

    private void clickLoginButton() {
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
