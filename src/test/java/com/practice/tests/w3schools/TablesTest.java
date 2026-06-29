package com.practice.tests.w3schools;

import com.practice.drivers.Driver;
import com.practice.pages.w3schools.TablesPage;
import com.practice.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class TablesTest {

    // Variables
    Driver driver;
    JsonReader w3Data = new JsonReader("w3schools-data");

    // Hooks
    @BeforeMethod
    public void setUp() {
        driver = new Driver();
        new TablesPage(driver.get()).navigate();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Tests
    @Test
    public void checkCountryOfCompany() {
        new TablesPage(driver.get())
                .checkCountryOfCompany(w3Data.getJsonData("company"), w3Data.getJsonData("country"));
    }
}