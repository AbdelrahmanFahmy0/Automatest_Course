package com.practice.utils.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.practice.utils.WaitManager.waitUntilClickable;
import static com.practice.utils.WaitManager.waitUntilVisible;

public class ElementActions {

    private ElementActions() {
    }

    public static void click(WebDriver driver, By locator) {
        scrollToElementJS(driver, locator);
        waitUntilClickable(driver, locator);
        findElement(driver, locator).click();
    }

    public static void fill(WebDriver driver, By locator, String text) {
        scrollToElementJS(driver, locator);
        waitUntilVisible(driver, locator);
        findElement(driver, locator).sendKeys(text);
    }

    public static void fill(WebDriver driver, By locator, Keys key) {
        scrollToElementJS(driver, locator);
        waitUntilVisible(driver, locator);
        findElement(driver, locator).sendKeys(key);
    }

    public static String getText(WebDriver driver, By locator) {
        scrollToElementJS(driver, locator);
        waitUntilVisible(driver, locator);
        return findElement(driver, locator).getText();
    }

    public static boolean isDisplayed(WebDriver driver, By locator) {
        return findElement(driver, locator).isDisplayed();
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void scrollToElementJS(WebDriver driver, By locator) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(""" 
                        arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", findElement(driver, locator));
    }
}