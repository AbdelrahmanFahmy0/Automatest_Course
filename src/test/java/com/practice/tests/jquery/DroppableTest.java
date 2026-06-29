package com.practice.tests.jquery;

import com.practice.drivers.Driver;
import com.practice.pages.jquery.DroppablePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DroppableTest {

    // Variables
    Driver driver;

    // Hooks
    @BeforeMethod
    public void setUp() {
        driver = new Driver();
        new DroppablePage(driver.get()).navigate();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Tests
    @Test
    public void checkDraggingAndDropping() {
        new DroppablePage(driver.get())
                .dragBoxAndDrop()
                .checkBoxIsDragged();
    }
}