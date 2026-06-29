package com.practice.pages.duckduckgo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.practice.utils.actions.ElementActions.*;

public class SearchResultsPage {

    WebDriver driver;

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    // Dynamic Locators
    private By resultLink(int index) {
        return By.xpath("(//a[@data-testid='result-extras-url-link'])[" + index + "]");
    }

    private By resultTitle(int index) {
        return By.xpath("(//a[@data-testid='result-title-a'])[" + index + "]/span");
    }

    // Actions

    // Assertions
    public SearchResultsPage checkResultLink(int resultIndex, String expectedLink) {
        String actualLink = getAttributeValue(driver, resultLink(resultIndex), "href");
        Assert.assertEquals(actualLink, expectedLink);
        return this;
    }

    public SearchResultsPage checkResultLinkContainsText(int resultIndex, String expectedLinkText) {
        boolean linkHasText = getAttributeValue(driver, resultLink(resultIndex), "href").contains(expectedLinkText);
        Assert.assertTrue(linkHasText);
        return this;
    }

    public SearchResultsPage checkResultTitle(int resultIndex, String expectedTitle) {
        System.out.println(getText(driver, resultTitle(1)));
        System.out.println(getText(driver, resultTitle(2)));
        System.out.println(getText(driver, resultTitle(3)));
        System.out.println(getText(driver, resultTitle(4)));

        String actualTitle = getText(driver, resultTitle(resultIndex));
        Assert.assertEquals(actualTitle, expectedTitle);
        return this;
    }
}