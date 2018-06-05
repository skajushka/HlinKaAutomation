package com.qulix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.*;

public class ListMessagesPage extends BasePage {
//TODO Переделать локаторы. Никаких индексов
    private final By pageTitle = By.xpath("/html/body/div[5]/h1");
    private final By userGreeting = By.xpath("/html/body/div[5]/div[1]");
    private final By newMessageTab = By.linkText("New Message");
    private final By allUsersMessagesOption = By.name("allUsers");
    private final By lastTableRow = By.xpath("//table/tbody/tr[last()]");
    private final By beforeLastTableRow = By.xpath("//table/tbody/tr[last()-1]");
    private final By viewButton = By.linkText("View");
    private final By editButton = By.linkText("Edit");
    private final By deleteButton = By.linkText("Delete");
    private final By paginationDiv = By.className("paginateButtons");
    private final By lastRowMessageCreationDate = By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[last()]/td[5]");
    private final By logoutButton = By.linkText("Logout");
    private final By currentTableStep = By.className("currentStep");
    private final By tableStep = By.className("step");


    public ListMessagesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getNewMessageTab() {
        return webDriver.findElement(newMessageTab);
    }

    public WebElement getAllUsersMessagesCheckbox() {
        return webDriver.findElement(allUsersMessagesOption);
    }

    private Integer getMaxAvailablePageNumber(WebElement pagination) {

        //TODO Зачем так сложно? Почему просто не получить последнюю страницу
        WebElement currentStep = pagination.findElement(currentTableStep);

        List<WebElement> steps = pagination.findElements(tableStep);
        List<Integer> pageNumbers = new ArrayList<>();

        pageNumbers.add(Integer.parseInt(currentStep.getText()));

        steps.forEach(step -> {
                try {
                    pageNumbers.add(Integer.parseInt(step.getText()));
                } catch (NumberFormatException e) {
                    //".." value found. Do nothing.
                }
            }
        );

        Integer maxPageIndex = Collections.max(pageNumbers);
        return maxPageIndex;
    }

    public WebElement getLastPage() {
        try {
            WebElement pagination = webDriver.findElement(paginationDiv);
            return pagination.findElement(By.linkText(getMaxAvailablePageNumber(pagination).toString()));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public WebElement getPreLastPage() {
        WebElement pagination = webDriver.findElement(paginationDiv);
        Integer maxPageIndex = getMaxAvailablePageNumber(pagination);
        Integer preLastPageIndex;

        if (maxPageIndex > 1) {
            preLastPageIndex = maxPageIndex -1;
            return pagination.findElement(By.linkText(preLastPageIndex.toString()));
        } else {
            return null;
        }
    }

    public WebElement getLastTableRow() {
        return webDriver.findElement(lastTableRow);
    }

    public WebElement getBeforeTheLastTableRow() {
        return webDriver.findElement(beforeLastTableRow);
    }

    public WebElement getPageTitle() {
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

    public WebElement getLogoutButton() {
        return webDriver.findElement(logoutButton);
    }

    public WebElement getUserGreeting() {
        return webDriver.findElement(userGreeting);
    }
}
