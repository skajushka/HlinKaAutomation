package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.*;

public class ListMessagesPage extends BasePage {

    private final By pageTitle = By.tagName("h1");
    private final By userGreeting = By.className("message");
    private final By newMessageTab = By.linkText("New Message");
    private final By allUsersMessagesOption = By.name("allUsers");
    private final By lastTableRow = By.xpath("//table/tbody/tr[last()]");
    private final By beforeLastTableRow = By.xpath("//table/tbody/preceding::tr[last()]");
    private final By viewButton = By.linkText("View");
    private final By editButton = By.linkText("Edit");
    private final By deleteButton = By.linkText("Delete");
    private final By lastRowMessageCreationDate = By.xpath("//table/tbody/tr[last()]/td[5]");
    private final By logoutButton = By.linkText("Logout");
    private final By lastTablePageButton = By.xpath("//div[@class='paginateButtons']/a[last()-1]");
    private final By preLastTablePageButton = By.xpath("//div[@class='paginateButtons']/a[last()]");

    public ListMessagesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getNewMessageTab() {
        return findPageElement(newMessageTab);
    }

    public void clickNewMessageTab() {
        this.getNewMessageTab().click();
    }

    public WebElement getAllUsersMessagesCheckbox() {
        return findPageElement(allUsersMessagesOption);
    }

    public void selectAllUsersMessagesCheckbox() {
        this.getAllUsersMessagesCheckbox().click();
    }

    public WebElement getLastPaginationButton() {
        return findPageElement(lastTablePageButton);
    }

    public void clickLastPaginationButton() {
        this.getLastPaginationButton().click();
    }

    public void goToPreLastPage() {
        findPageElement(preLastTablePageButton).click();
    }

    public WebElement getLastTableRow() {
        return findPageElement(lastTableRow);
    }

    public String getTextOfTheLastTableRow() {
        return this.getLastTableRow().getText();
    }

    public WebElement getBeforeTheLastTableRow() {
        try {
            return findPageElement(beforeLastTableRow);
        } catch(java.util.NoSuchElementException e) {
            this.goToPreLastPage();
            return this.getLastTableRow();
        }
    }

    public String getTextOfBeforeTheLastTableRow() {
        return this.getBeforeTheLastTableRow().getText();
    }

    public WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    public String checkPageTitle() {
        return this.getPageTitle().getText();
    }

    public WebElement getLastRowEditButton() {
        WebElement expectedLastTableRow = findPageElement(lastTableRow);
        return expectedLastTableRow.findElement(editButton);
    }

    public void clickLastRowEditButton() {
        this.getLastRowEditButton().click();
    }

    public WebElement getLastRowViewButton() {
        WebElement expectedLastTableRow = findPageElement(lastTableRow);
        return expectedLastTableRow.findElement(viewButton);
    }

    public void clickLastRowViewButton() {
        this.getLastRowViewButton().click();
    }

    public WebElement getLastRowDeleteButton() {
        WebElement expectedLastTableRow = findPageElement(lastTableRow);
        return expectedLastTableRow.findElement(deleteButton);
    }

    public void clickLastRowDeleteButton() {
        this.getLastRowDeleteButton().click();
    }

    public WebElement getLastMessageCreationDate() {
        return findPageElement(lastTableRow).findElement(lastRowMessageCreationDate);
    }

    public WebElement getLogoutButton() {
        return findPageElement(logoutButton);
    }

    public void clickLogoutButton() {
        this.getLogoutButton().click();
    }

    public WebElement getUserGreeting() {
        return findPageElement(userGreeting);
    }

    public String checkTextOfUserGreeting() {
        return this.getUserGreeting().getText();
    }
}
