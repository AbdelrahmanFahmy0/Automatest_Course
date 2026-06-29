package com.practice.pages.duckduckgo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchResultsPage {

    WebDriver driver;

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    // Dynamic Locators
    public By resultLink(int index) {
        return By.xpath("//li[@data-layout='organic'][" + index + "]//a[@data-testid='result-extras-url-link']");
    }

    public By resultTitle(int index) {
        return By.xpath("//li[@data-layout='organic'][" + index + "]//h2//span");
    }

    // Actions

    // Assertions
    public SearchResultsPage checkResultLink(int resultIndex, String expectedLink) {
        String actualLink = driver.findElement(resultLink(resultIndex)).getText();
        Assert.assertEquals(actualLink, expectedLink);
        return this;
    }

    public SearchResultsPage checkResultLinkContainsText(int resultIndex, String expectedLinkText) {
        boolean linkHasText = driver.findElement(resultLink(resultIndex)).getText().contains(expectedLinkText);
        Assert.assertTrue(linkHasText);
        return this;
    }

    public SearchResultsPage checkResultTitle(int resultIndex, String expectedTitle) {
        String actualTitle = driver.findElement(resultTitle(resultIndex)).getText();
        Assert.assertEquals(actualTitle, expectedTitle);
        return this;
    }
}