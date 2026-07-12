package com.practice.template;

import com.practice.drivers.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class TestScenario {

    // Variables
    Driver driver;

    // Hooks
    @BeforeClass
    public void setUp() {
        driver = new Driver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}