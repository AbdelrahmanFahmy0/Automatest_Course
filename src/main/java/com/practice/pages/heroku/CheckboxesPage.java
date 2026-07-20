package com.practice.pages.heroku;

import com.practice.drivers.Driver;
import org.openqa.selenium.By;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class CheckboxesPage {

    Driver driver;

    // Constructor
    public CheckboxesPage(Driver driver) {
        this.driver = driver;
    }

    // Dynamic Locators
    private By checkbox(int index) {
        return By.xpath("//input[@type='checkbox'][" + index + "]");
    }

    // Actions
    public CheckboxesPage navigate() {
        driver.browser().navigateTo(getProperty("herokuCheckboxesUrl"));
        return this;
    }

    public CheckboxesPage checkCheckbox(int index) {
        driver.action().check(checkbox(index), true);
        return this;
    }

    // Assertions
    public CheckboxesPage assertCheckboxIsChecked(int index) {
        driver.check().checkCheckboxIsChecked(checkbox(index));
        return this;
    }
}