package com.practice.pages.duckduckgo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LandingPage {

    WebDriver driver;

    // Constructor
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By duckLogo = By.xpath("(//a[@aria-label='Learn about DuckDuckGo']//img)[1]");
    private final By searchBar = By.id("searchbox_input");

    // Actions
    private SearchResultsPage search(String searchText) {
        driver.findElement(searchBar).sendKeys(searchText);
        driver.findElement(searchBar).sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    // Assertions
    public LandingPage checkPageTitle(String title) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, title);
        return this;
    }

    public LandingPage checkLogoIsDisplayed() {
        boolean isDisplayed = driver.findElement(duckLogo).isDisplayed();
        Assert.assertTrue(isDisplayed);
        return this;
    }
}
