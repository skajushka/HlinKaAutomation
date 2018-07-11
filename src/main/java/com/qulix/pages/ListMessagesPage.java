package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListMessagesPage extends AbstractPage {

    private static final By pageTitle = By.tagName("h1");
    private static final By userGreeting = By.className("message");
    private static final By newMessageTab = By.linkText("New Message");
    private static final By allUsersMessagesOption = By.name("allUsers");
    private static final By lastTableRow = By.xpath("//table/tbody/tr[last()]");
    private static final By beforeLastTableRow = By.xpath("//table/tbody/preceding::tr[last()]");
    private static final By viewButton = By.linkText("View");
    private static final By editButton = By.linkText("Edit");
    private static final By deleteButton = By.linkText("Delete");

    private static final By lastRowMessageCreationDate = By.xpath("//table/tbody/tr[last()]/td[5]");
    private static final By logoutButton = By.linkText("Logout");
    //todo last и pre перепутаны по-моему
    private static final By lastTablePageButton = By.xpath("//div[@class='paginateButtons']/a[last()-1]");
    private static final By preLastTablePageButton = By.xpath("//div[@class='paginateButtons']/a[last()]");

    ListMessagesPage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getNewMessageTab() {
        return findPageElement(newMessageTab);
    }

    public CreateMessagePage clickNewMessageTab() {
        getNewMessageTab().click();
        return new CreateMessagePage(webDriver);
    }

    private WebElement getAllUsersMessagesCheckbox() {
        return findPageElement(allUsersMessagesOption);
    }

    public void selectAllUsersMessagesCheckbox() {
        this.getAllUsersMessagesCheckbox().click();
    }

    private WebElement getLastPaginationButton() {
        return findPageElement(lastTablePageButton);
    }

    public void clickLastPaginationButton() {
        getLastPaginationButton().click();
    }

    private void goToPreLastPage() {
        findPageElement(preLastTablePageButton).click();
    }

    private WebElement getLastTableRow() {
        return findPageElement(lastTableRow);
    }

    public String getTextOfTheLastTableRow() {
        return this.getLastTableRow().getText();
    }

    private WebElement getBeforeTheLastTableRow() {
        try {
            return findPageElement(beforeLastTableRow);
        } catch(java.util.NoSuchElementException e) {//todo не то исключение перехватываешь
            this.goToPreLastPage();
            return this.getLastTableRow();
        }
    }

    public String getTextOfBeforeTheLastTableRow() {
        return this.getBeforeTheLastTableRow().getText();
    }

    private WebElement getPageTitle() {
        return findPageElement(pageTitle);
    }

    public String checkPageTitle() {
        return this.getPageTitle().getText();
    }

    private WebElement getLastRowEditButton() {
        WebElement expectedLastTableRow = findPageElement(lastTableRow);
        return expectedLastTableRow.findElement(editButton);
    }

    public EditMessagePage clickLastRowEditButton() {
        getLastRowEditButton().click();
        return new EditMessagePage(webDriver);
    }

    private WebElement getLastRowViewButton() {
        WebElement expectedLastTableRow = findPageElement(lastTableRow);
        return expectedLastTableRow.findElement(viewButton);
    }

    public ShowMessagePage clickLastRowViewButton() {
        getLastRowViewButton().click();
        return new ShowMessagePage(webDriver);
    }

    private WebElement getLastRowDeleteButton() {
        WebElement expectedLastTableRow = findPageElement(lastTableRow);
        return expectedLastTableRow.findElement(deleteButton);
    }

    public void clickLastRowDeleteButton() {
        this.getLastRowDeleteButton().click();
    }

    public WebElement getLastMessageCreationDate() {
        return findPageElement(lastTableRow).findElement(lastRowMessageCreationDate);
    }

    private WebElement getLogoutButton() {
        return findPageElement(logoutButton);
    }

    public LoginPage clickLogoutButton() {
        getLogoutButton().click();
        return new LoginPage(webDriver);
    }

    private WebElement getUserGreeting() {
        return findPageElement(userGreeting);
    }

    public String checkTextOfUserGreeting() {
        return this.getUserGreeting().getText();
    }
}
