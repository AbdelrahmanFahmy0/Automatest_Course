package com.practice.drivers;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriver {

    // Method to create WebDriver instance to be implemented by subclasses
    public abstract WebDriver createDriver();
}