package com.practice.pages.heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.practice.utils.actions.BrowserActions.navigateTo;
import static com.practice.utils.actions.ElementActions.*;
import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class CheckboxesPage {

    WebDriver driver;

    // Constructor
    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    // Dynamic Locators
    private By checkbox(int index) {
        return By.xpath("//input[@type='checkbox'][" + index + "]");
    }

    // Actions
    public CheckboxesPage navigate() {
        navigateTo(driver, getProperty("herokuCheckboxesUrl"));
        return this;
    }

    public CheckboxesPage checkCheckbox(int index) {
        if (!isChecked(driver, checkbox(index))) {
            click(driver, checkbox(index));
        }
        return this;
    }

    public CheckboxesPage uncheckCheckbox(int index) {
        if (isChecked(driver, checkbox(index))) {
            click(driver, checkbox(index));
        }
        return this;
    }

    // Assertions
    public CheckboxesPage assertCheckboxIsChecked(int index) {
        boolean isChecked = isChecked(driver, checkbox(index));
        Assert.assertTrue(isChecked);
        return this;
    }

    public CheckboxesPage assertCheckboxIsNotChecked(int index) {
        boolean isChecked = isChecked(driver, checkbox(index));
        Assert.assertFalse(isChecked);
        return this;
    }
}