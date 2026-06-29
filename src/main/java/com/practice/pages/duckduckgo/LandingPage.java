package com.practice.pages.duckduckgo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.practice.utils.actions.BrowserActions.getTitle;
import static com.practice.utils.actions.BrowserActions.navigateTo;
import static com.practice.utils.actions.ElementActions.fill;
import static com.practice.utils.actions.ElementActions.isDisplayed;

public class LandingPage {

    WebDriver driver;

    // Constructor
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By duckLogo = By.xpath("(//a[@aria-label='Learn about DuckDuckGo']//img)[2]");
    private final By searchBar = By.id("searchbox_input");

    // Actions
    public LandingPage navigate(String URL) {
        navigateTo(driver, URL);
        return this;
    }

    public SearchResultsPage search(String searchText) {
        fill(driver, searchBar, searchText);
        fill(driver, searchBar, Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    // Assertions
    public LandingPage checkPageTitle(String title) {
        String actualTitle = getTitle(driver);
        Assert.assertEquals(actualTitle, title);
        return this;
    }

    public LandingPage checkLogoIsDisplayed() {
        boolean logoIsDisplayed = isDisplayed(driver, duckLogo);
        Assert.assertTrue(logoIsDisplayed);
        return this;
    }
}