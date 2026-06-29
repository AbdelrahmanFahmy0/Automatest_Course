package com.practice.drivers;

public enum Browser {

    // Enum constants representing different browsers, each providing its own implementation of the getDriverFactory method
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeFactory();
        }
    },
    FIREFOX {
        @Override
        public AbstractDriver getDriverFactory() {
            return new FirefoxFactory();
        }
    };

    // Abstract method to be implemented by each enum constant
    public abstract AbstractDriver getDriverFactory();
}