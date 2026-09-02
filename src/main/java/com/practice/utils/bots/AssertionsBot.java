package com.practice.utils.bots;

import com.practice.utils.WaitManager;
import com.practice.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AssertionsBot {

    // Variables
    private final WaitManager wait;
    private final ActionsBot actions;

    public AssertionsBot(WebDriver driver) {
        this.wait = new WaitManager(driver);
        this.actions = new ActionsBot(driver);
    }

    /**
     * Verifies that the specified element is displayed.
     *
     * @param locator The locator of the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot exists(By locator) {
        LogsManager.info("Verifying that element is displayed for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            Assert.assertTrue(driver.findElement(locator).isDisplayed(), "Element is not displayed: " + locator);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the specified element does not exist.
     *
     * @param locator The locator of the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot notExists(By locator) {
        LogsManager.info("Verifying that element is not displayed for " + locator);
        wait.fluentWait().until(driver -> {
            ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            Assert.assertTrue(driver.findElements(locator).isEmpty(), "Element is displayed: " + locator);
            return true;
        });
        return this;
    }

    /**
     * Verifies the page title.
     *
     * @param expectedTitle The expected page title.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkTitle(String expectedTitle) {
        LogsManager.info("Verifying that page title is: " + expectedTitle);
        wait.fluentWait().until(driver -> {
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match expected title: " + expectedTitle + " but found: " + actualTitle);
            return true;
        });
        return this;
    }

    /**
     * Verifies the page url.
     *
     * @param expectedUrl The expected page url.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkUrl(String expectedUrl) {
        LogsManager.info("Verifying that page URL is: " + expectedUrl);
        wait.fluentWait().until(driver -> {
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, expectedUrl, "Page url does not match expected url: " + expectedUrl + " but found: " + actualUrl);
            return true;
        });
        return this;
    }

    /**
     * Verifies the text of the specified element.
     *
     * @param locator      The locator of the element.
     * @param expectedText The expected text of the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkTextEquals(By locator, String expectedText) {
        LogsManager.info("Verifying that element text is: " + expectedText + " for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            String actualText = driver.findElement(locator).getText();
            Assert.assertEquals(actualText, expectedText, "Element text does not match: " + locator + " expected: " + expectedText + " but found: " + actualText);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the attribute value of the specified element equals the expected value.
     *
     * @param locator       The locator of the element.
     * @param attribute     The attribute to verify.
     * @param expectedValue The expected value of the attribute.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkAttributeEquals(By locator, String attribute, String expectedValue) {
        LogsManager.info("Verifying that attribute " + attribute + " is: " + expectedValue + " for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            String actualValue = driver.findElement(locator).getDomAttribute(attribute);
            Assert.assertEquals(actualValue, expectedValue, "Element attribute does not match: " + locator + " expected: " + expectedValue + " but found: " + actualValue);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the attribute value of the specified element contains the expected value.
     *
     * @param locator       The locator of the element.
     * @param attribute     The attribute to verify.
     * @param expectedValue The expected value that should be contained in the attribute.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkAttributeContains(By locator, String attribute, String expectedValue) {
        LogsManager.info("Verifying that attribute " + attribute + " contains: " + expectedValue + " for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            String actualValue = driver.findElement(locator).getDomAttribute(attribute);
            Assert.assertTrue(actualValue.contains(expectedValue), "Element attribute does not contain expected value: " + locator + " expected to contain: " + expectedValue + " but found: " + actualValue);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the specified checkbox is checked.
     *
     * @param locator The locator of the checkbox element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkCheckboxIsChecked(By locator) {
        LogsManager.info("Verifying that checkbox is checked for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            boolean isChecked = driver.findElement(locator).isSelected();
            Assert.assertTrue(isChecked, "Checkbox is not checked: " + locator);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the specified checkbox is unchecked.
     *
     * @param locator The locator of the checkbox element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkCheckboxIsUnchecked(By locator) {
        LogsManager.info("Verifying that checkbox is unchecked for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            boolean isChecked = driver.findElement(locator).isSelected();
            Assert.assertFalse(isChecked, "Checkbox is checked: " + locator);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the text of the specified element contains the expected text.
     *
     * @param locator      The locator of the element.
     * @param expectedText The text that should be contained in the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkTextContains(By locator, String expectedText) {
        LogsManager.info("Verifying that element text contains: " + expectedText + " for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            String actualText = driver.findElement(locator).getText();
            Assert.assertTrue(actualText.contains(expectedText), "Element text does not contain expected text: " + locator + " expected to contain: " + expectedText + " but found: " + actualText);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the specified element is enabled.
     *
     * @param locator The locator of the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkElementISEnabled(By locator) {
        LogsManager.info("Verifying that element is enabled for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            boolean isEnabled = driver.findElement(locator).isEnabled();
            Assert.assertTrue(isEnabled, "Element is not enabled: " + locator);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the specified element is disabled.
     *
     * @param locator The locator of the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkElementIsDisabled(By locator) {
        LogsManager.info("Verifying that element is disabled for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            boolean isEnabled = driver.findElement(locator).isEnabled();
            Assert.assertFalse(isEnabled, "Element is enabled: " + locator);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the page URL contains the expected string.
     *
     * @param expectedUrlPart The expected URL string that should be contained in the current URL.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkUrlContains(String expectedUrlPart) {
        LogsManager.info("Verifying that page URL contains: " + expectedUrlPart);
        wait.fluentWait().until(driver -> {
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrlPart), "Page URL does not contain expected URL part: " + expectedUrlPart + " but found: " + actualUrl);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the option in a dropdown is selected.
     *
     * @param locator        The locator of the dropdown element.
     * @param expectedOption The text of the expected selected option.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkSelectedOption(By locator, String expectedOption) {
        LogsManager.info("Verifying that selected option is: " + expectedOption + " for " + locator);
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            String selectedText = new Select(driver.findElement(locator)).getFirstSelectedOption().getText();
            Assert.assertEquals(selectedText, expectedOption, "Selected option does not match: " + locator + " expected: " + expectedOption + " but found: " + selectedText);
            return true;
        });
        return this;
    }
}