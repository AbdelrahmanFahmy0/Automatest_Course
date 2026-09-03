package com.practice.pages.duckduckgo;

import com.practice.drivers.Driver;
import io.qameta.allure.Step;
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
    @Step("Navigate to the DuckDuckGo landing page")
    public LandingPage navigate() {
        driver.browser().navigateTo(getProperty("duckGoUrl"));
        return this;
    }

    @Step("Search DuckDuckGo for '{0}'")
    public SearchResultsPage search(String searchText) {
        driver.action().fill(searchBar, searchText, Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    // Assertions
    @Step("Check DuckDuckGo page title equals '{0}'")
    public LandingPage checkPageTitle(String title) {
        driver.check().checkTitle(title);
        return this;
    }

    @Step("Check DuckDuckGo logo is displayed")
    public LandingPage checkLogoIsDisplayed() {
        driver.check().exists(duckLogo);
        return this;
    }
}