package com.practice.tests.duckduckgo;

import com.practice.drivers.Driver;
import com.practice.pages.duckduckgo.LandingPage;
import com.practice.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class DuckGoTest {

    // Variables
    Driver driver;
    JsonReader duckGoData = new JsonReader("duckgo-data");

    // Hooks
    @BeforeMethod
    public void setUp() {
        driver = new Driver();
        new LandingPage(driver.get()).navigate(getProperty("duckGoUrl"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Tests
    @Test
    public void checkDuckGoPageTitle() {
        new LandingPage(driver.get())
                .checkPageTitle("Google");
    }

    @Test
    public void checkDuckGoLogoIsDisplayed() {
        new LandingPage(driver.get())
                .checkLogoIsDisplayed();
    }

    @Test
    public void checkFirstSearchResultURL() {
        new LandingPage(driver.get())
                .search(duckGoData.getJsonData("results[0].searchKey"))
                .checkResultLink(1, duckGoData.getJsonData("results[0].url"));
    }

    @Test()
    public void checkForthSearchResultTitle() {
        new LandingPage(driver.get())
                .search(duckGoData.getJsonData("results[1].searchKey"))
                .checkResultTitle(4, duckGoData.getJsonData("results[1].title"));
    }

    @Test
    public void checkSecondSearchResultURL() {
        new LandingPage(driver.get())
                .search(duckGoData.getJsonData("results[2].searchKey"))
                .checkResultLinkContainsText(2, "https://www.linkedin.com");
    }
}