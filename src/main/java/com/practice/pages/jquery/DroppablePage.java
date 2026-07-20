package com.practice.pages.jquery;

import com.practice.drivers.Driver;
import org.openqa.selenium.By;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class DroppablePage {

    Driver driver;

    // Constructor
    public DroppablePage(Driver driver) {
        this.driver = driver;
    }

    // Locators
    private final By draggableBox = By.id("draggable");
    private final By droppableBox = By.id("droppable");
    private final By droppableBoxText = By.cssSelector("#droppable > p");

    // Actions
    public DroppablePage navigate() {
        driver.browser().navigateTo(getProperty("jqueryUrl"));
        return this;
    }

    public DroppablePage dragBoxAndDrop() {
        driver.action().dragAndDrop(draggableBox, droppableBox);
        return this;
    }

    // Assertions
    public DroppablePage checkBoxIsDragged() {
        driver.check().checkTextEquals(droppableBoxText, "Dropped!");
        return this;
    }
}