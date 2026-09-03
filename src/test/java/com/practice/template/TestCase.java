package com.practice.template;

import com.practice.drivers.Driver;
import com.practice.drivers.UITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@UITest
public abstract class TestCase {

    // Variables
    protected Driver driver;

    // Hooks
    @BeforeMethod
    public void setUp() {
        driver = new Driver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}