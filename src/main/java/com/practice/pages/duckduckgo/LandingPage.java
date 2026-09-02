package com.practice.pages.duckduckgo;

import com.practice.drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class LandingPage {

    Driver driver;

    // Constructor
    public LandingPage(Driver driver) {
        this.driver = driver;
    }

    // Locators
    private final By duckLogo = By.xpath("//a[@aria-label='Learn about DuckDuckGo']//img");
    private final By searchBar = By.name("q");

    // Actions
    public LandingPage navigate() {
        driver.browser().navigateTo(getProperty("duckGoUrl"));
        return this;
    }

    public SearchResultsPage search(String searchText) {
        driver.action().fill(searchBar, searchText, Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    // Assertions
    public LandingPage checkPageTitle(String title) {
        driver.check().checkTitle(title);
        return this;
    }

    public LandingPage checkLogoIsDisplayed() {
        driver.check().exists(duckLogo);
        return this;
    }
}