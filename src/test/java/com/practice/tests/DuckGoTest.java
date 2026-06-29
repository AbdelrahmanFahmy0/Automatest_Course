package com.practice.tests;

import com.practice.drivers.Driver;
import com.practice.pages.duckduckgo.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class DuckGoTest {

    // Variables
    Driver driver;

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
                .search("Selenium WebDriver")
                .checkResultLink(1, "https://www.selenium.dev/documentation/webdriver/");
    }

    @Test
    public void checkForthSearchResultTitle() {
        new LandingPage(driver.get())
                .search("TestNG")
                .checkResultTitle(4, "TestNG Tutorial");
    }

    @Test
    public void checkSecondSearchResultURL() {
        new LandingPage(driver.get())
                .search("Cucumber IO")
                .checkResultLinkContainsText(2, "https://www.linkedin.com");
    }
}