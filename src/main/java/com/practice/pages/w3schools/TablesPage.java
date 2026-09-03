package com.practice.pages.w3schools;

import com.practice.drivers.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class TablesPage {

    Driver driver;

    // Constructor
    public TablesPage(Driver driver) {
        this.driver = driver;
    }

    // Dynamic Locators
    private By country(String company) {
        return By.xpath("//td[text() = '" + company + "']//following-sibling::td[2]");
    }

    // Actions
    @Step("Navigate to the W3Schools tables page")
    public TablesPage navigate() {
        driver.browser().navigateTo(getProperty("w3schoolsUrl"));
        return this;
    }

    // Assertions
    @Step("Check that company '{0}' has country '{1}'")
    public TablesPage checkCountryOfCompany(String company, String expectedCountry) {
        driver.check().checkTextEquals(country(company), expectedCountry);
        return this;
    }
}