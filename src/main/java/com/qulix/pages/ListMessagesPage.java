package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListMessagesPage extends PageObject {

    private final By pageTitle = By.xpath("/html/body/div[5]/h1");
    private final By newMessageTab = By.linkText("New Message");
    private final By allUsersMessagesOption = By.name("allUsers");
    private final By nextButton = By.linkText("Next");
    private final By lastTableRow = By.xpath("//table/tbody/tr[last()]");
    private final By beforeLastTableRow = By.xpath("//table/tbody/tr[last()-1]");
    private final By viewButton = By.linkText("View");
    private final By editButton = By.linkText("Edit");
    private final By deleteButton = By.linkText("Delete");
    private final By lastRowMessageCreationDate = By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[last()]/td[5]");

    public ListMessagesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findNewMessageTab() {
        return webDriver.findElement(newMessageTab);
    }

    public WebElement findAllUsersMessagesOption() {
        return webDriver.findElement(allUsersMessagesOption);
    }

    public WebElement findNextButton() {
        return webDriver.findElement(nextButton);
    }

    public WebElement findLastTableRow() {
        return webDriver.findElement(lastTableRow);
    }

    public WebElement findBeforeTheLastTableRow() {
        return webDriver.findElement(beforeLastTableRow);
    }

    public WebElement findPageTitle() {
        return webDriver.findElement(pageTitle);
    }

    public WebElement getLastRowEditButton() {
        WebElement expectedLastTableRow = webDriver.findElement(lastTableRow);
        WebElement expectedEditButton = expectedLastTableRow.findElement(editButton);
        return expectedEditButton;
    }

    public WebElement getLastRowViewButton() {
        WebElement expectedLastTableRow = webDriver.findElement(lastTableRow);
        WebElement expectedViewButton = expectedLastTableRow.findElement(viewButton);
        return expectedViewButton;
    }

    public WebElement getLastRowDeleteButton() {
        WebElement expectedLastTableRow = webDriver.findElement(lastTableRow);
        WebElement expectedDeletedButton = expectedLastTableRow.findElement(deleteButton);
        return expectedDeletedButton;
    }

    public WebElement getLastMessageCreationDate() {
        WebElement lastMessageCreationDate = webDriver.findElement(lastTableRow).findElement(lastRowMessageCreationDate);
        return lastMessageCreationDate;
    }

    public void clickNextButton() {
        ListMessagesPage listMessagesPage = new ListMessagesPage(webDriver);

        do {
            listMessagesPage.findNextButton().click();
        } while (listMessagesPage.findNextButton().isDisplayed());
    }

 /*   public void getLastPage() {
        List<WebElement> pagination = webDriver.findElements(By.xpath("/html/body/div[5]/div[4]/a"));
        WebElement lastPage = pagination.get(pagination.size());
        System.out.println(lastPage);
    }*/
}
