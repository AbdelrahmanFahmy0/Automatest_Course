package com.practice.pages.jquery;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.practice.utils.actions.BrowserActions.navigateTo;
import static com.practice.utils.actions.ElementActions.dragAndDrop;
import static com.practice.utils.actions.ElementActions.getText;
import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class DroppablePage {

    WebDriver driver;

    // Constructor
    public DroppablePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By draggableBox = By.id("draggable");
    private final By droppableBox = By.id("droppable");
    private final By droppableBoxText = By.cssSelector("#droppable > p");

    // Actions
    public DroppablePage navigate() {
        navigateTo(driver, getProperty("jqueryUrl"));
        return this;
    }

    public DroppablePage dragBoxAndDrop() {
        dragAndDrop(driver, draggableBox, droppableBox);
        return this;
    }

    // Assertions
    public DroppablePage checkBoxIsDragged() {
        String actualText = getText(driver, droppableBoxText);
        Assert.assertEquals(actualText, "Dropped!");
        return this;
    }
}