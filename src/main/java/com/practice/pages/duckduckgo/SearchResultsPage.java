package com.practice.pages.duckduckgo;

import com.practice.drivers.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SearchResultsPage {

    Driver driver;

    // Constructor
    public SearchResultsPage(Driver driver) {
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
    @Step("Check search result {0} link equals '{1}'")
    public SearchResultsPage checkResultLink(int resultIndex, String expectedLink) {
        driver.check().checkAttributeEquals(resultLink(resultIndex), "href", expectedLink);
        return this;
    }

    @Step("Check search result {0} link contains '{1}'")
    public SearchResultsPage checkResultLinkContainsText(int resultIndex, String expectedLinkText) {
        driver.check().checkAttributeContains(resultLink(resultIndex), "href", expectedLinkText);
        return this;
    }

    @Step("Check search result {0} title equals '{1}'")
    public SearchResultsPage checkResultTitle(int resultIndex, String expectedTitle) {
        driver.check().checkTextEquals(resultTitle(resultIndex), expectedTitle);
        return this;
    }
}