package com.practice.pages.w3schools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.practice.utils.actions.BrowserActions.navigateTo;
import static com.practice.utils.actions.ElementActions.getText;
import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class TablesPage {

    WebDriver driver;

    // Constructor
    public TablesPage(WebDriver driver) {
        this.driver = driver;
    }

    // Dynamic Locators
    private By country(String company) {
        return By.xpath("//td[text() = '" + company + "']//following-sibling::td[2]");
    }

    // Actions
    public TablesPage navigate() {
        navigateTo(driver, getProperty("w3schoolsUrl"));
        return this;
    }

    // Assertions
    public TablesPage checkCountryOfCompany(String company, String expectedCountry) {
        String actualCountry = getText(driver, country(company));
        Assert.assertEquals(actualCountry, expectedCountry);
        return this;
    }
}